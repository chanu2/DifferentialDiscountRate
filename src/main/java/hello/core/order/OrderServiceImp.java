package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImp implements OrderService{

    private final MemberRepository memberRepository;  // final이 있으면 기본으로 할당을 하던 생성자로 꼭 할당을 해야한다
    private final DiscountPolicy discountPolicy;

    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
