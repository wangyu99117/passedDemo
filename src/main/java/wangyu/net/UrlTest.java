package wangyu.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wangyu21 on 2017/2/11.
 */
public class UrlTest {

    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = null;
        InputStream in = null;

        try {
            URL url = new URL("http://storage.jd.com/form-data-file/20170213_e85f015da1e446828172cfa7621c26d8.csv");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            int length = conn.getContentLength();
            System.out.println("长度 length = " + length);
            in = conn.getInputStream();
            BufferedInputStream inend = new BufferedInputStream(in);

            File outFile = new File("d:/temp-wangyu-url-access-1111.csv");
            /*
            //下面的这段代码，在有outputStream = new FileOutputStream(outFile); 时完全可以没有
            if(!outFile.exists()){
                outFile.createNewFile();
            }*/

            outputStream = new FileOutputStream(outFile);
            int hasRead = 0;
            byte[] temp = new byte[64];
            while((hasRead = inend.read(temp)) > 0){
                //System.out.println(temp);
                String info = new String(temp, 0, hasRead);
                outputStream.write(temp, 0, hasRead);
                System.out.println(info);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                outputStream.close();
            }
            if(in != null){
                in.close();
            }
        }

    }
}
