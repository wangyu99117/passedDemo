package wangyu.domain;

/**
 * Created by wangyu21 on 2017/2/24.
 */
public class Student extends Person {
    public Long idCard = 102l;

    public static void main(String[] args){
        Student one = new Student();
        //System.out.println(one.getIdCard());
        //System.out.println(one.accessId());
        System.out.println(one.accessPId());
        /*Person n = one.getRoot();
        System.out.println(n.getIdCard());*/
        Student two = new Student();
        System.out.println(two.accessPId());
        two.setIdCard(14l);
        System.out.println(two.accessPId());
        System.out.println(one.accessPId());
    }

    public Long getIdCard(){
        return idCard;
    }

    public Long accessPId(){
        return super.idCard;
    }
}
