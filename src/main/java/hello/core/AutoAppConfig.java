package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component가 붙은 클래스를 찾아서 자동으로 빈 등록해준다
        //basePackages = "hello.core.member", //  이 패키지부터 하위 까지만  그러므로 member만 //없으면 모든 자바 코드를 다 뒤진다 // 지정하지 않으면 @ComponentScan이 붙은 AutoAppConfig클래스 의 hello.core부터 시작해서 하위까지
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)// 빼는 이유 자동으로 등록 되버리기 때문에
)
public class AutoAppConfig {

}
