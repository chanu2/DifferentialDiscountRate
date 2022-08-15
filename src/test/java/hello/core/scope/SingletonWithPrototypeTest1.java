package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean1.getCount()).isEqualTo(1);


    }

    @Test
    void singltonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);


        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);


    }
    @Scope("singleton") //구지 안해도댐
    static class ClientBean{
        //private final PrototypeBean prototypeBean;  //생성점에 주입

        @Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider; //대신 찾아준다.
        private Provider<PrototypeBean> prototypeBeanProvider; //provider를 사용한 코드

//        @Autowired
//        public  ClientBean(PrototypeBean prototypeBean) {// 생성자를 통해서 이때 프로토타입 빈 달라고 요청 이때 스프링 컨테이너가 prototyBean을 만들어서 던저준다
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//provider는 get(),getObject()를 호출하면 그떄야 스프링 컨테이너에서 PrototypeBean을 찾아서 반환한다
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;

        }
    }
    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init"+ this);
        }

        @PreDestroy
        public void destory(){
            System.out.println("PrototypeBean.destory ");
        }
    }
}
