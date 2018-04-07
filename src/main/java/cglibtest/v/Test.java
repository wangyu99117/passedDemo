package cglibtest.v;

import java.util.List;

/**
 * Created by wangyu21 on 2017/3/29.
 */
public class Test {

    public static void main(String[] args){
        CGlibProxyFactory factory = new CGlibProxyFactory();
        StuService bean = (StuService)factory.createProxyInstance(
                new StuService());

        List stuList = bean.findAll();

    }

}
