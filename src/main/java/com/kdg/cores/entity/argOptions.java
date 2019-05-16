package com.kdg.cores.entity;

import java.util.List;

public class argOptions {

    private String date;
    private String accountKey;
    private List<String> redisKeys;
    private List<String> inputAccounts;
    private List<String> inputAgents;

    public argOptions(String date, String accountKey, List<String> redisKeys, List<String> inputAccounts, List<String> inputAgents) {
        this.date = date;
        this.accountKey = accountKey;
        this.redisKeys = redisKeys;
        this.inputAccounts = inputAccounts;
        this.inputAgents = inputAgents;
    }

    public String getDate() {
        return date;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public List<String> getRedisKeys() {
        return redisKeys;
    }

    public List<String> getInputAccounts() {
        return inputAccounts;
    }

    public List<String> getInputAgents() {
        return inputAgents;
    }

    @Override
    public String toString() {
        return "argOptions{" +
                "date='" + date + '\'' +
                ", accountKey='" + accountKey + '\'' +
                ", redisKeys=" + redisKeys +
                ", inputAccounts=" + inputAccounts +
                ", inputAgents=" + inputAgents +
                '}';
    }
}
