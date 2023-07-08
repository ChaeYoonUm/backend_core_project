package Hello.core.member;

public interface MemberRepository {

    //회원 저장
    void save(Member member);

    //Id로 회원 찾기 기능
    Member findById(Long memberId);
}
