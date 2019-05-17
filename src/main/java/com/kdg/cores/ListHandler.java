package com.kdg.cores;

import com.alibaba.fastjson.JSONObject;
import com.kdg.cores.connections.RedisConnPool;
import com.kdg.cores.entity.Developer;
import com.kdg.cores.entity.Request;
import com.kdg.cores.entity.Task;
import com.kdg.cores.entity.ArgOptions;
import com.kdg.cores.pubCores.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ListHandler extends Resource {

    private static Logger logger = Logger.getLogger(ListHandler.class.getSimpleName());

    private ArgOptions argOptions;
    private String logName;
//    private String apiPath;
    private Developer developer;
    private String hbaseTableName;
    private int threadNum = 5;
    private Map params = null;
    private boolean isNeedAccounts = true;
    private int catchSize = 2000;
    private int batchSize = 200;
    private int keysReserveDays = 3;

    private RedisConnPool redisConnPool;
    private ExecutorService fixedThreadPool;


    public ListHandler(String[] args, String logName, String apiPath, Developer developer, String hbaseTableName) {
        this.argOptions = ArgsParser.argsParser(args);
        this.logName = logName;
        Settings.setApiPath(apiPath);
        this.developer = developer;
        this.hbaseTableName = hbaseTableName;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public void setNeedAccounts(boolean needAccounts) {
        isNeedAccounts = needAccounts;
    }

    public void setCatchSize(int catchSize) {
        this.catchSize = catchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setKeysReserveDays(int keysReserveDays) {
        this.keysReserveDays = keysReserveDays;
    }

    private void createResource() {
        redisConnPool = new RedisConnPool(threadNum);
        fixedThreadPool = Executors.newFixedThreadPool(threadNum);
    }

    public void run() {

        try {

            createResource();

            threadsExecute();
        } catch (Exception e) {
            logger.error("Get unknown exception.", e);
        }
    }

    private void threadsExecute() {
        // 获取数据源
        ArrayList<Request> resource = new ArrayList<Request>();
        try {
            resource = this.getResource(argOptions.getAccountKey(), redisConnPool.getConn(), params, argOptions.getInputAccounts(), argOptions.getInputAgents(), argOptions
                    .getDate(), isNeedAccounts);
        } catch (Exception e) {
            logger.error("get agent error.", e);
            Finished.finished(1);
        }

        // 乱序
        Collections.shuffle(resource);

        if (resource.isEmpty()) {
            logger.error("resource is empty.");
            Finished.finished(1);
        }

        logger.info(String.format("resource data total: %d", resource.size()));

        // 启动线程池执行任务
        for (Request request : resource) {
            fixedThreadPool.execute(new ApiExecutor(request));
        }

        fixedThreadPool.shutdown();
    }
}


class ApiExecutor implements Runnable {

    Request request;

    ApiExecutor(Request request) {
        this.request = request;
    }

    public void run() {
        try {
            requestHandler(request, 1);
        } catch (IOException e) {
            e.printStackTrace();
            Finished.finished(1);
        }
    }

//    private void requestHandler(Request request) {
//        requestHandler(request, 1);
//    }

    private void requestHandler(Request request, int retry) throws IOException {

        JSONObject response = ApiRequest.httpPost(request, Settings.getApiPath(), 0);
    }
}