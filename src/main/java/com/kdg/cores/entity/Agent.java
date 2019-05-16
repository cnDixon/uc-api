package com.kdg.cores.entity;

public class Agent {

    private String agentId;
    private String userName;
    private String password;
    private String token;

    public Agent(String agentId, String userName, String password, String token) {
        this.agentId = agentId;
        this.userName = userName;
        this.password = password;
        this.token = token;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId='" + agentId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
