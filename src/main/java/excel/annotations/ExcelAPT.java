package excel.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public class ExcelAPT {

    public static Field[] getFields(Class cls){
        Field[]  fields = cls.getDeclaredFields();
        List<Field> fieldList = new ArrayList<Field>();

        for(Field f : fields){
            f.setAccessible(true);
            if(f.isAnnotationPresent(ExcelColumn.class)){
                fieldList.add(f);
            }
        }

        Field[] fieldArray = new Field[fieldList.size()];
        fieldList.toArray(fieldArray);
        return fieldArray;
    }
}
