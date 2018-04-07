package wangyu.ProxyTest.baseProxy;

import java.lang.reflect.Proxy;

/**
 * Created by wangyu21 on 2017/2/23.
 */
public class TestDog {
    public static void main(String[] args){
        MyInvocationHandler h = new MyInvocationHandler();
        Dog target = new SmallDog();

        System.out.println(Dog.class.getInterfaces());
        System.out.println(target.getClass().getInterfaces());

        h.setTarget(target);
        Dog dog = (Dog)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), target.getClass().getInterfaces(), h);
        dog.run();
        dog.say("hello");
        System.out.println(target.getClass().getSimpleName());
        System.out.println(dog.getClass());
        System.out.println(dog.getClass().getInterfaces());

        Dog dog1 = (Dog)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), dog.getClass().getInterfaces(), h);
        dog1.run();
        dog1.say("hello");
    }
}
