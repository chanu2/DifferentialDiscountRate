package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


//@RequiredArgsConstructor // final 이 붙어있는걸 가지고 생성자를 만들어 준다  // lombok
@Component
public class OrderServiceImpl implements OrderService{

    private  final MemberRepository memberRepository;  // final이 있으면 기본으로 할당을 하던 생성자로 꼭 할당을 해야한다 바꿀수 없다
    private  final DiscountPolicy discountPolicy;   // set 하려면 final 빼야한다

    //@Autowired private  MemberRepository memberRepository;  // 필드 주입방법
    //@Autowired private DiscountPolicy discountPolicy;   // set을 생성하여 값을 넣어줘야 하기 때문에 좋은 방법이 아니다  and  자바스프링이 아니면 test하기가 어렵다 안쓴다 잘

//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {  // 수정자
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) { //OrderServiceImplTest 확인
//        this.memberRepository = memberRepository;
//    }

    @Autowired//생성자가 하나있으면  Autowired 안적어도 된다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 생성자 주입은 스프링이  OrderServiceImpl를 생성해야하는데 어쩔수 없이 생성자를 불러야한다 bean을 등록하면서 의존관계 등록도 같이 돼버린다..//set get은 그다음에 ㅇ 의존관계에 등록  // /*@Qualifier("mainDiscountPolicy"), */ /*@MainDiscountPolicy*/DiscountPolicy disCountPOlicy
        //System.out.println("1,호출 OrderServiceImpl.OrderServiceImpl");
        //System.out.println("memberRepository = " + memberRepository);
        //System.out.println("discountPolicy = " + discountPolicy);

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }


    //test를 위한 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
