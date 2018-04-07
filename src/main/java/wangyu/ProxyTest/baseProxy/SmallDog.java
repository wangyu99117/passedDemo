package wangyu.ProxyTest.baseProxy;

/**
 * Created by wangyu21 on 2017/2/23.
 */
public class SmallDog implements Dog{
    public void run() {
        System.out.println("i'm run!");
    }

    public void say(String lag) {
        System.out.println("i'm say" + lag);
    }
}
