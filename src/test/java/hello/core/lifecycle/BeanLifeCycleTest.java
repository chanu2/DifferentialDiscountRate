package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean //(initMethod = "init",destroyMethod = "close") //destroyMethod 의 defualt는 inferred=추론
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();  // 빈등록할 때 생성자로 가기 때문에 URL이 없다.
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;

        }
    }

}
