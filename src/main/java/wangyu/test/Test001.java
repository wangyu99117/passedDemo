package wangyu.test;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by wangyu21 on 2017/2/15.
 */
public class Test001 {

    static {
        b = 5;
    }
    static int b ;

    public static void main(String[] args){
        /*URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL cla : urls){
            System.out.println(cla.toExternalForm());
        }*/
        DecimalFormat defaultFormat = new DecimalFormat(",###.##");
        BigDecimal d = new BigDecimal(2000.010222d);
        System.out.println(defaultFormat.format(d));
    }
}
