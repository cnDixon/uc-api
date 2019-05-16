package com.kdg.cores.entity;

import java.util.Map;

public class Request {

    private Map headers;
    private Map body;
    private String agent;
    private String account;

    public Request(Map headers, Map body, String agent, String account) {
        this.headers = headers;
        this.body = body;
        this.agent = agent;
        this.account = account;
    }

    public Map getHeaders() {
        return headers;
    }

    public Map getBody() {
        return body;
    }

    public String getAgent() {
        return agent;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Request{" +
                "headers=" + headers +
                ", body=" + body +
                ", agent='" + agent + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
