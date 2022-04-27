package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;   // 1000원 할인
    
    @Override
    public int discount(Member member, int price) {
        // vip면 1000원 정액할인 vip가 아니면 학인없음
        if(member.getGrade() == Grade.VIP){
            // Enum 타입은 == 쓰는게 맞다
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
