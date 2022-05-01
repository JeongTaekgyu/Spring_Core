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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // AppConfig에 설정을 구성한다는 뜻의 @Configuration 을 붙여준다.
public class AppConfig {

    // 역할
    @Bean   // 각 메서드에  @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록한다.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        // 이렇게하면 생정자 주입을 통해서 DIP원칙을 위반하지 않음
        return new MemberServiceImpl(memberRepository());
    }

    // 역할
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // 이렇게하면 생정자 주입을 통해서 DIP원칙을 위반하지 않음
        return new OrderServiceImpl(
                memberRepository(), discountPolicy());
    }

    // 구현
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // 구현
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
