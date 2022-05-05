package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 지금 클래스 의존관계를 분석하면 추상(인터페이스)뿐만 아니라 구체(구현)클래스에도 의존하고 있다.
    // 추상 (인터페이스) 의존 : DiscountPolicy
    // 구체(구현)클래스 : FixDiscountPolicy, RateDiscountPolicy
    // 즉, 이건 DIP 위반하고 있다.
    // 또한 FixDiscountPolicy 를 RateDiscountPolicy 로 변경해야하는 경우가 있는데 이도 OCP 원칙을 위반한다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // ★ 이렇게 하면 구체화에 의존하지 않고 인터페이스에만 의존한다. 주입은 밑에 처럼 생성자 주입으로 한다.★
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // OrderService에서 discount를 관리하지 않고 discountPolicy 에서 discount를 관리했기 때문에
        // 단일 책임 원칙을 잘 지킨거다.
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에다 회원을 넘긴다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
