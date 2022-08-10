// 정말 중요한 부분
package hello.core.singleton;

public class StatefulService {
    //private int price;//상태를 유지하는 필드 // 값이



    public int order(String name, int price){
        System.out.println("name = " + name+ price);
        return price;// 문제가 발생한다
    }

//    public int getPrice(){
//        return price;
//    }

}
