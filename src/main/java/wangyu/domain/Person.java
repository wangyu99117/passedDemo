package wangyu.domain;

/**
 * Created by wangyu21 on 2017/2/16.
 */
public class Person {
    public Long idCard = 12l;

//    public Person(Long idCard) {
//        this.idCard = idCard;
//    }

    public Long getIdCard() {
        return this.idCard;
    }

    public Person getRoot(){
        return this;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }
}
