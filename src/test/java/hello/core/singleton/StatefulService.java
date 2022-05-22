package hello.core.singleton;

public class StatefulService {

    /*private int price; // 상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " , price = " + price);
        this.price = price; // 여기가 문제
    }

    public int getPrice() {
        return price;
    }*/

    // 공유 필드를 사용하면 안되기 때문에 스프링 빈은 항상 무상태(stateless)로 설계하자
    public int order(String name, int price){
        System.out.println("name = " + name + "price = " + price);
        return price;
    }
}
