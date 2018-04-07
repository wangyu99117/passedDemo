package cglibtest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyu21 on 2017/3/29.
 */
public class MyInterceptor implements MethodInterceptor {


    private Object bei;

    public Object getProxy(Object tar){
        //设置需要创建子类的类
        bei = tar;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bei.getClass());
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");

        Object result = methodProxy.invoke(bei, objects);
        //通过代理类调用父类中的方法
        //Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理");

        System.out.println("前置代理004");

        //Object result = methodProxy.invoke(bei, objects);
        //通过代理类调用父类中的方法
        Object result11 = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理004");

        return result11;
    }

}
