package wangyu.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangyu21 on 2017/2/23.
 */
public class JsfTestInvocationHandler implements InvocationHandler {

    TestService testService;

    public Object invoke(Object proxy, Method method, Object[] args) throws JsfHDProxyException {
        try{
            return method.invoke(testService,args);
        }catch (Exception e){
            throw new JsfHDProxyException();
        }
    }
}
