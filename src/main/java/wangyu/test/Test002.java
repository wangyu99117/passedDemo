package wangyu.test;

import wangyu.domain.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyu21 on 2017/2/16.
 */
public class Test002 {

    public static void main(String[] args){
        /*List<String> res = new ArrayList<String>();
        res.add("1");
        res.add("2");*/
        //数组和 list 一样，这种遍历修改，无法修改集合中的值
        /*String[] res = {"1", "2"};

        int i = 10;
        for(String s : res){
             i++;
             s = i+"";
        }
        for(String s : res){
            System.out.println(s);
        }*/

        List<Person> list = new ArrayList<Person>();
        //list.add(new Person(1l));
        //list.add(new Person(21l));

        long i = 10l;
        for(Person s : list){
            i ++ ;
            s.setIdCard(i);
        }

        for(Person s : list){
            System.out.println(s.getIdCard());
        }
    }
}
