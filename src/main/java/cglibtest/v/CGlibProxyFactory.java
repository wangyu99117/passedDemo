package cglibtest.v;

/**
 * Created by wangyu21 on 2017/3/29.
 */
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGlibProxyFactory implements MethodInterceptor {
    private Object targetObject;


    public Object createProxyInstance(Object targetObject){
        this.targetObject = targetObject;    //传入用户类

        Enhancer enhancer = new Enhancer();          //Enhancer是cglib的核心类

        // 将用户类设为 Enhancer对象的superclass属性,,即设为 Enhancer对象的父类
        enhancer.setSuperclass(this.targetObject.getClass());
        // 设 Enhancer对象的Callbacks属性,要求必须是Callback接口类型
        enhancer.setCallback(this);

        return enhancer.create();  //生成代理对象
    }


    public Object intercept(Object arg0, Method arg1, Object[] arg2,
                            MethodProxy arg3) throws Throwable {
        Object result = null;
        /*StuService bean = (StuService)this.targetObject;
        Object result = null;
        if(!arg1.getName().equals("save")&&(bean.findAll().size()==0))
        {  // 如果方法名不是save(即是get,update,delete方法),而同时实例的列表为空,就不执行方法,而是告警
            System.out.println("list is null,method is stoped");
        }else{*/
            //执行代理方法,传入实例和方法参数
            result = arg3.invoke(targetObject, arg2);
            System.out.println("list is excute");
        //}
        return result;
    }
}
