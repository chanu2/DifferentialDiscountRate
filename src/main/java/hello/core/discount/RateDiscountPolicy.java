package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


//@Qualifier("mainDiscountPolicy")//  qualifier끼리 서로 매칭한다 , 없다면 빈이름에 매칭해본다, 없으면 예외 잘 사용하지 않는다 명확하지 않기때문에
//@Primary // 빈 2개이상 중복될때 우선수위를 정해준다 오토와일드는 TYPE으로 찾는데 같은 타입을 여러개 일 때 //Qualifier Primary두개 다 있다면 자세한 Qualifier가 우선수위를 갖는다 좁은 선택범위가 더 우선순위가 높다
//@MainDiscountPolicy// 만든 annotation
@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent/100;
        }
        else{
            return 0;
        }
    }
}
