package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("Vip는 할인률 10% 적용되야한다")
    void vip_o(){
         //given
        Member member = new Member(1L,"memberVip", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Vip가 아니면 할인률 10% 적용되면 안된다")
    void vip_x(){
        Member member = new Member(1L,"memberVip", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);


    }

}