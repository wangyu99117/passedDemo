package wangyu.test;

import java.util.ArrayList;

/**
 * Created by wangyu21 on 2018/4/9.
 * 局部变量置为null .数组引用
 */
public class TestCollection {

    public static void main(String[] args){
        ArrayList<Long> target = new ArrayList<Long>();
        for(int i = 0; i<1000 ;i++){
            Long sub = new Long(i);
            target.add(sub);
            sub = null;
        }
        for(int j=0 ; j<target.size() ; j++){
            System.out.println(target.get(j) + "----");
        }
    }
}
