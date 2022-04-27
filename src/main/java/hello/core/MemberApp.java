package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 이렇게 하면 스프링 AppConfig에 있는 환경 설정 정보를 가지고
        // 스프링 컨테이너에다 객체 생성한걸 집어넣어서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "Henry", Grade.VIP);
        memberService.join(member);
        // 얘도 다형성에 의해서 memberService에 있는 join이 아니라
        // MemberServiceImpl 에 있는 join가 호출 되는 듯 하다

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember : "+ findMember.getName());
    }
}
