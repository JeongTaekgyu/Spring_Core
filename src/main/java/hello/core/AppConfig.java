package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // 역할
    public MemberService memberService(){
        // 이렇게하면 생정자 주입을 통해서 DIP원칙을 위반하지 않음
        return new MemberServiceImpl(memberRepository());
    }

    // 역할
    public OrderService orderService() {
        // 이렇게하면 생정자 주입을 통해서 DIP원칙을 위반하지 않음
        return new OrderServiceImpl(
                memberRepository(), discountPolicy());
    }

    // 구현
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 구현
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
