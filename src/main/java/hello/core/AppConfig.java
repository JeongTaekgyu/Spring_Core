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
        // FixDiscountPolicy -> RateDiscountPolicy 로 변경해도 구성 영역만 영향을 받고,
        // 사용 영역은 전혀 영향을 받지 않는다.
        // 하지만 새로 개발한 정률 할인 정책을 적용하려고 하니 클라이언트 코드인 주문 서비스 구현체도 함께 변경해야함
        // 주문 서비스 클라이언트가 인터페이스인 DiscountPolicy 뿐만 아니라, 구체 클래스인
        // FixDiscountPolicy 도 함께 의존 -> DIP 위반
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
