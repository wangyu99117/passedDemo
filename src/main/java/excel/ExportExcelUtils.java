package excel;

import excel.annotations.ExcelAPT;
import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public class ExportExcelUtils {
    private final static Logger LOG = Logger.getLogger(ExportExcelUtils.class);

    public static <T> void export(List<T> sourceList, OutputStream out, boolean pager){
        if(sourceList == null || out == null){
            return ;
        }
        if(sourceList.size() == 0 ){
            return ;
        }
        export(sourceList,  out,  new POIExcel(), pager);
    }

    public static <T> void export(List<T> sourceList, OutputStream out, ExcelUtil tool, boolean pager){
        if(sourceList == null || out == null){
            return ;
        }

        T  obj_1= sourceList.get(0);
        Class member_class = obj_1.getClass();

        Field[] fields = ExcelAPT.getFields(member_class);

        if(fields.length == 0){
            LOG.error("逻辑错误！要导出的类型没有配置导出字段！");
            return;
        }

        tool.export(fields, member_class, sourceList ,out, pager);
    }
}
