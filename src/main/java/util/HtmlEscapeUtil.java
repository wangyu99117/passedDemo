package util;


import org.apache.commons.lang.StringUtils;

/**
 * Created by wubo on 2017/3/2.
 */
public class HtmlEscapeUtil {
    public static String escapeHtml(String content){
        if(StringUtils.isEmpty(content)){
            return "";
        }
       return content.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
    }
}
