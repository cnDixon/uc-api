package com.kdg.cores.entity;

import java.util.HashMap;
import java.util.Map;

public class Task {

    private int taskId;
    private String agent;
    private String account;
    private Map reqHeaders;
    private Map reqBody;

    public Task(int taskId, String agent, String account, Map reqHeaders, Map reqBody) {
        this.taskId = taskId;
        this.agent = agent;
        this.account = account;
        this.reqHeaders = reqHeaders;
        this.reqBody = null != reqBody ? reqBody : new HashMap();
    }

    public int getTaskId() {
        return taskId;
    }

    public String getAgent() {
        return agent;
    }

    public String getAccount() {
        return account;
    }

    public Map getReqHeaders() {
        return reqHeaders;
    }

    public Map getReqBody() {
        return reqBody;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", agent='" + agent + '\'' +
                ", account='" + account + '\'' +
                ", reqHeaders=" + reqHeaders +
                ", reqBody=" + reqBody +
                '}';
    }
}
