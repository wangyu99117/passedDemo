package wangyu.test;

/**
 * Created by wangyu21 on 2017/3/2.
 */
public class Test004 {

    public static void main(String args[]){
        long t0 = System.nanoTime();
        long cur = System.currentTimeMillis();
        System.out.println(t0);
        System.out.println(cur);
        long t1 = System.nanoTime();
        System.out.println(t1);
        long estimatedTime = System.nanoTime() - t0;
        System.out.println(estimatedTime);
        long at = System.currentTimeMillis() - cur;
        System.out.println(System.currentTimeMillis());
        System.out.println(at);
    }
}
