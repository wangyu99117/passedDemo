package excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangyu21 on 2016/7/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {

    //列名
    String name() default "col";

    //列宽
    int width() default 15;

    //对齐方式 r,R 右，l，L左，c,C 居中， 默认居中
    char style() default 'c';

    //底色
    String color() default "000";

    String dateFormat() default "yyyy-MM-dd";

    String correct() default "是";

    String nagative() default "否";



}
