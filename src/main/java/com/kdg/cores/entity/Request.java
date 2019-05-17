package com.kdg.cores.entity;

import com.alibaba.fastjson.JSONObject;

public class Request {

    private JSONObject headers;
    private JSONObject body;
    private String agent;
    private String account;

    public Request(JSONObject headers, JSONObject body, String agent, String account) {
        this.headers = headers;
        this.body = body;
        this.agent = agent;
        this.account = account;
    }

    public JSONObject getHeaders() {
        return headers;
    }

    public JSONObject getBody() {
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
