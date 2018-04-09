package util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by weixing on 2016/12/8.
 */
public class HttpClientUtil {
    private static final Log log = LogFactory.getLog(HttpClientUtil.class);

    /**
     * 发送get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String doGet(String url) {
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        //使用 GET 方法
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        responseMsg = executeMethod(responseMsg, httpClient, getMethod);
        return responseMsg;
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params) {
        String responseMsg = "";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
        Iterator it = params.entrySet().iterator();
        int index = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValuePairs[index] = new NameValuePair(entry.getKey(), entry.getValue());
            index++;
        }
        postMethod.setRequestBody(nameValuePairs);
        responseMsg = executeMethod(responseMsg, httpClient, postMethod);
        return responseMsg;
    }

    /**
     * 执行方法
     *
     * @param responseMsg
     * @param httpClient
     * @param httpMethod
     * @return
     */
    private static String executeMethod(String responseMsg, HttpClient httpClient, HttpMethod httpMethod) {
        try {
            httpClient.executeMethod(httpMethod);
            responseMsg = httpMethod.getResponseBodyAsString();
        } catch (HttpException e) {
            log.error("--executeMethod error--", e);
        } catch (IOException e) {
            log.error("--executeMethod error--", e);
        } finally {
            //释放连接
            httpMethod.releaseConnection();
        }
        return responseMsg;
    }

}
