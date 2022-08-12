package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; // 추상화에도 의존하고 구체화에도 의존하고 있다 DIP위반
    @Autowired // 의존관계를 자동으로 주입해주는 에노테이션  // 기본적으로 type으로 조회를 한다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository=memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    //test

    //test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
