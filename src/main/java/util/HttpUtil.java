package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by liyue on 16/9/26.
 */
public class HttpUtil {
  public static String getPostData(HttpServletRequest request) throws IOException {
    BufferedReader bufferReader = request.getReader();//获取头部参数信息
    StringBuffer buffer = new StringBuffer();
    String line ;
    while ((line = bufferReader.readLine()) != null) {
      buffer.append(line);
    }
    return buffer.toString();
  }


  public static void writeResponse(HttpServletResponse response, String content) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("content-type", "text/html;charset=UTF-8");
    response.setDateHeader("Expires", 0);
    //OutputStream out = response.getOutputStream();
    //out.write(text.getBytes("UTF-8"));

    response.getWriter().write(content);
  }

  /**
   * 把输入流转化成字符串
   *
   * @param is
   * @return
   */
  public static String convertStreamToString(InputStream is) {
    BufferedReader reader;
    StringBuilder sb = new StringBuilder();

    String line = null;
    try {
      reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }
}
