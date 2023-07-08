package Hello.core.member;

//가입, 조회
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
