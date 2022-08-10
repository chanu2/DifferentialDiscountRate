package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;

import static org.assertj.core.api.Assertions.*;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){ // 사용할 수 없도록 막는다

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //같은 객체 인스턴스를 반환한다
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //검증
        assertThat(singletonService1).isSameAs(singletonService2);
        // same ==
        // equal

    }




}
