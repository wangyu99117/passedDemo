package cglibtest;

/**
 * Created by wangyu21 on 2017/3/29.
 */
public class Test {

    public static void main(String[] args) {
        MyInterceptor proxy = new MyInterceptor();

        HelloCglib proxyImp = (HelloCglib)proxy.getProxy(new HelloCglib());
        proxyImp.say();

        //通过生成子类的方式创建代理类
       /* CglibProxy001 proxy01 = new CglibProxy001();
        HelloCglib proxyImp01 = (HelloCglib)proxy01.getProxy(HelloCglib.class);
        proxyImp01.say();*/

        /*HelloCglib test = new HelloCglib();
        test.say();*/
    }
}
