package wangyu.ProxyTest.baseProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangyu21 on 2017/2/23.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Dog test;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("所有的狗开始");
        method.invoke(test, args);
        System.out.println("所有的狗结束");
        return null;
    }

    public void setTarget(Dog test){
        this.test = test;
    }
}
