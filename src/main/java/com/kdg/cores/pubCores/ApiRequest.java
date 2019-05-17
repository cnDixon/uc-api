package com.kdg.cores.pubCores;

import com.alibaba.fastjson.JSONObject;
import com.kdg.cores.constants.ReqParams;
import com.kdg.cores.entity.Request;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ApiRequest {

    private static Logger logger = Logger.getLogger(ApiRequest.class.getSimpleName());

    public static JSONObject httpPost(Request request, String apiPath, int retry) throws IOException {

        String url = ReqParams.BASE_URL + apiPath;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("header", request.getHeaders());
        jsonObject.put("body", request.getBody());
        String jsonStr = jsonObject.toJSONString();

        logger.info(String.format("account: %s, agent: %s, request: %s.", request.getAccount(), request.getAgent(), jsonStr));

        StringRequestEntity entity = new StringRequestEntity(jsonStr, "application/json", "utf8");
        PostMethod postMethod = new PostMethod(url);
        HttpClient httpClient = new HttpClient();

        postMethod.setRequestEntity(entity);
        postMethod.setRequestHeader("Content-Type", "application/json");


        int code = httpClient.executeMethod(postMethod);

        if (code != 200) {
            if (retry < 10) {
                return httpPost(request, apiPath, retry + 1);
            } else {
                logger.error(String.format("http code %d, error request.", code));
                Finished.finished(1);
            }
        }

        return JSONObject.parseObject(postMethod.getResponseBodyAsString());
    }

    public static JSONObject httpPost(Request request, int retry) throws IOException {
        return httpPost(request, null, 0);
    }
}
