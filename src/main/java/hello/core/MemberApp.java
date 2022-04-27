package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "Henry", Grade.VIP);
        memberService.join(member);
        // 얘도 다형성에 의해서 memberService에 있는 join이 아니라
        // MemberServiceImpl 에 있는 join가 호출 되는 듯 하다

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember : "+ findMember.getName());
    }
}
