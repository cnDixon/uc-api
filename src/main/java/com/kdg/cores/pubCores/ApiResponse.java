package com.kdg.cores.pubCores;

import com.alibaba.fastjson.JSONObject;
import com.kdg.cores.entity.ExecuteResult;
import com.kdg.cores.entity.Request;

public class ApiResponse {

    public static ExecuteResult responseProcess(Request request, JSONObject response, int retry) {

        Integer status = response.getJSONObject("header").getInteger("status");

        switch (status) {
            case 0:
                break;
            default:
        }

        return new ExecuteResult(true, true);
    }


    private ExecuteResult statusSuccess() {
        return new ExecuteResult(true, true);
    }
}
