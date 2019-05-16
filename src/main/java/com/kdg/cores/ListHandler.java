package com.kdg.cores;

import com.kdg.cores.connections.JedisConn;
import com.kdg.cores.entity.Developer;
import com.kdg.cores.pubCores.Resource;
import org.apache.log4j.Logger;

import java.util.Map;


public class ListHandler extends Resource {

    private static Logger logger = Logger.getLogger(ListHandler.class.getSimpleName());

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

    private JedisConn jedisConn = new JedisConn(threadNum);


    public ListHandler(String logName, String apiPath, Developer developer, String hbaseTableName) {
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


    public void run() {

        try {

            threadsExecute();
        } catch (Exception e) {
            logger.error("Get unknown exception.", e);
        }
    }

    private void threadsExecute() {

//        this.getResource()
    }
}


class ApiExecutor implements Runnable {
    public void run() {

    }
}