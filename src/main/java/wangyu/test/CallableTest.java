package wangyu.test;

import java.util.LinkedList;

/**
 * Created by wangyu21 on 2017/3/6.
 */
public class CallableTest {

    public void add(){
        add();
    }

    public void call(){
        LinkedList list = new LinkedList();
        while(true){
            list.add(new Object());
            System.out.println("112222222222222222");
        }
    }

    public static void main(String[] args){
        new Thread(){
            @Override
            public void run(){
                LinkedList list = new LinkedList();
                while(true){
                    list.add(new Object());
                    System.out.println("fenghuang!");
                }
            }
        }.start();
        /*System.out.println("11111111111111");*/
        new CallableTest().call();

    }
}
