package cglibtest.v;

/**
 * Created by wangyu21 on 2017/4/18.
 */
public class tee {

    public static void main(String[] args){
        ThreadTest run1 = new ThreadTest("1");
        Thread[] t1 = new Thread[200];


        for(int i = 0; i<200; i++){
            t1[i] = new Thread(run1);
        }


        for(int i = 0; i<200; i++){
            if(i%3 == 0){
                run1.setUserInfo("1");
            }else if(i%3 == 1){
                run1.setUserInfo("2");
            }else if(i%3 == 2){
                run1.setUserInfo("3");
            }

            t1[i].start();
        }
    }
}
