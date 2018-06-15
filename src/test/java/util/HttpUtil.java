package util;

import mySelenium.PropertiesHandle;

import logger.Log;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import tgssp.TgSsp;

import java.util.Arrays;

public class HttpUtil {

    private static Logger LOGGER = Log.getLogger(HttpUtil.class);

    public static TgSsp.Response request(byte[] content) {
        TgSsp.Response response = null;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(PropertiesHandle.get("tg_api_res_pos_show"));
        postMethod.addRequestHeader("Content-Type", "application/octet-stream;charset=utf-8");
        postMethod.addRequestHeader("Accept", "application/x-protobuf;charset=utf-8");
        postMethod.setRequestEntity(new ByteArrayRequestEntity(content));
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.info(statusCode);
            } else {
                LOGGER.info(Arrays.toString(postMethod.getResponseBody()));
                LOGGER.info(postMethod.getResponseBody().length);
                LOGGER.info(postMethod.getResponseCharSet());
                response = TgSsp.Response
                        .parseFrom(postMethod.getResponseBodyAsStream());
                LOGGER.info(response.toString());
                LOGGER.info(response.getMsg() + " " + response.getStatus());
            }
        } catch (Exception e) {
            LOGGER.error("getContent:", e);
        }
        return response;
    }
}
