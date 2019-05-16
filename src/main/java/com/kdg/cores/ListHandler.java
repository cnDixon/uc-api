package com.kdg.cores;

import com.kdg.cores.connections.RedisConnPool;
import com.kdg.cores.entity.Developer;
import com.kdg.cores.entity.Task;
import com.kdg.cores.entity.argOptions;
import com.kdg.cores.pubCores.ArgsParser;
import com.kdg.cores.pubCores.Finished;
import com.kdg.cores.pubCores.Resource;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class ListHandler extends Resource {

    private static Logger logger = Logger.getLogger(ListHandler.class.getSimpleName());

    private argOptions argOptions;
    private String logName;
    private String apiPath;
    private Developer developer;
    private String hbaseTableName;
    private int threadNum = 5;
    private Map params = null;
    private boolean isNeedAccounts = true;
    private int catchSize = 2000;
    private int batchSize = 200;
    private int keysReserveDays = 3;

    private RedisConnPool redisConnPool;


    public ListHandler(String[] args, String logName, String apiPath, Developer developer, String hbaseTableName) {
        this.argOptions = ArgsParser.argsParser(args);
        this.logName = logName;
        this.apiPath = apiPath;
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
        ArrayList<Task> resource;
        try {
            resource = this.getResource(argOptions.getAccountKey(), redisConnPool.getConn(), params, argOptions.getInputAccounts(), argOptions.getInputAgents(), argOptions
                    .getDate(), isNeedAccounts);
        } catch (SQLException e) {
            logger.error("get agent error.", e);
            Finished.finished(1);
        } catch (ClassNotFoundException e) {
            logger.error("get agent error.", e);
            Finished.finished(1);
        }
    }
}


class ApiExecutor implements Runnable {
    public void run() {

    }
}