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


    //호출순서 예시 순서는 보장되지는 않지만 순서대로 호출된다고 생각하자

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //memberRepositoryrk 3번 호출되야함
    //configurationTest로 돌려도댐 초기화 할때 호출됨으로 즉 빈등록 될 때 호출되기 떄문에 상관없다



    @Bean
    public MemberService memberService (){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());

    }

    @Bean
    public MemberRepository memberRepository() {  // MemberRepository가 무엇을 하는지 잘 보이도록 매서드로 구현한다
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){

        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();  // 이거 하나만 고치면 된다

    }
}
