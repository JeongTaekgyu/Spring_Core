package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach // 테스트 실행하기 전에 실행되는 것
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        // given - 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when - 구체화하고자 하는 그 행동
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then - 어떤 특정한 행동 때문에 발생할거라고 예상되는 변화에 대한 설명
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
