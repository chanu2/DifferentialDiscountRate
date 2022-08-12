package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter   // GET,SET을 자동으로 만들어 준다 LOMBOK이 있으면 가능하다 많이 사용한다
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("Asd");

        String name1 = helloLombok.getName();
        System.out.println("name1 = " + name1);


    }
}
