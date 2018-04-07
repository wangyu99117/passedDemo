package cglibtest.v;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyu21 on 2017/4/18.
 */
public class ThreadTest implements Runnable{


    private List<String> formats = new ArrayList<String>();//需要一个锁
    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();
    private Integer signal = 1;//信号位，进入同步代码则修改为2，默认为1
    private String userInfo;


    public ThreadTest(String userInfo) {
        this.userInfo = userInfo;
        formats.add("1");
        formats.add("2");
        formats.add("3");
    }

    /**
     * todo wangyu
     */
    private volatile ConcurrentMap<String, String> formatsMap = new ConcurrentHashMap();

    public void run() {

        String format = formatsMap.get(userInfo);
        if(format != null){
            System.out.println(Thread.currentThread().getName() + ":  success! map中取到的值：" +format + ":  key:---"+ userInfo);
        }else{
            System.out.println(Thread.currentThread().getName() + ":  fail! map中取到的值：" +format + ":  key:---"+ userInfo);
            for(String obj : formats){
                if(obj.equals(userInfo)) {
                    lock.lock();
                    try{
                        Boolean isWhile = true;//是否循环
                        while(isWhile){
                            if(signal == 1){
                                signal = 2;
                                System.out.println(Thread.currentThread().getName() + ":  加锁的代码块！");
                                formatsMap.put(userInfo, obj);
                                isWhile = false;
                                signal = 1;
                                cond.signal();
                            }else{
                                System.out.println(Thread.currentThread().getName() + ":  暂停！");
                                cond.await();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }
            }
        }
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
