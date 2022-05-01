package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있다..
    // 지금 이 코드는 추상화에도 의존하고 구체화에도 의존하는 코드이다.
                                                    // 실제 할당하는 부분이 구현체를 의존하게 된다..
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 위에 코드를 아래 인터페이스에만 의존하고 생성자를 주입해준다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // 다형성에 의해서 MemberRepository 에 있는 save가 아니라
        // MemoryMemberRepository 에 있는 save가 호출 된다.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
