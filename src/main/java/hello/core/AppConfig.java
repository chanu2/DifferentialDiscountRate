package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정정보
public class AppConfig {

    @Bean
    public MemberService memberService (){
        return new MemberServiceImpl(MemberRepository());

    }

    @Bean
    public MemberRepository MemberRepository() {  // MemberRepository가 무엇을 하는지 잘 보이도록 매서드로 구현한다
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(),discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();  // 이거 하나만 고치면 된다

    }
}
