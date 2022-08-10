package hello.core.singleton;

import jdk.dynalink.beans.StaticClass;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TreadA: A 사용자가 10000원을 주문했을 때
        int userAPrice = statefulService1.order("userA", 10000);

        // TreadB: B 사용자가 20000원을 주문했을 때
        int userBPrice = statefulService1.order("userB", 20000);

        //TreadA: 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();

        System.out.println("price = " +userAPrice);

    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();

        }
    }

}