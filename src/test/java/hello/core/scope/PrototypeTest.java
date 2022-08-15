package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find protoypeBean1");
        PrototypeBean protoypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find protoypeBean2");
        PrototypeBean protoypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("protoypeBean2 = " + protoypeBean2);
        System.out.println("protoypeBean1 = " + protoypeBean1);

        Assertions.assertThat(protoypeBean1).isNotSameAs(protoypeBean2);
        ac.close();  //프로토타입 스코프는 초기화까지만 하고 버린다

    }
    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");

        }
        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy");
        }

    }
}
