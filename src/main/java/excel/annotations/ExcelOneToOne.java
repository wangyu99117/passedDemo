package excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangyu21 on 2016/7/22.
 * 解决枚举映射
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelOneToOne {

    //列名
    String name() default "col";

    //列宽
    int width() default 15;

    //对齐方式 r,R 右，l，L左，c,C 居中， 默认居中
    char style() default 'c';

    String[] printValues();

    int[]   realValues();
}
