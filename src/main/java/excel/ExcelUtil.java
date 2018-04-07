package excel;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public interface ExcelUtil {

    <T> void export(Field[] fields, Class target, Collection<T> dataset, OutputStream out, boolean pager);
}
