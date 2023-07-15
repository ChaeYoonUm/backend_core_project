package Hello.core;

import Hello.core.discount.DiscountPolicy;
import Hello.core.discount.FixDiscountPolicy;
import Hello.core.discount.RateDiscountPolicy;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemberService;
import Hello.core.member.MemberServiceImpl;
import Hello.core.member.MemoryMemberRepository;
import Hello.core.order.OrderService;
import Hello.core.order.OrderServiceImpl;

/*AppConfig: 앱 전반의 구성을 책임지는 클래스
- 객체의 생성과 연결은 AppConfig가 담당함으로써, DIP가 완성 됨
- DIP 완성 : MemberServiceImpl은 MemberRepository인 추상에만 의존하면 됨, 구체 클레스 몰라도 됨
- 관심사의 분리 : 객체를 생성하고 연결하는 역할(Config)과 실행하는 역할(Impl)이 명확히 분리
- AppConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달
- memberServiceImpl(클라이언트) 입를 통햇에서 보면, 의존관계를 마치 외부에서 주입해주는 것과 같아서
- DI(Dependency Injection) , 의존관계 주입, 의존성 주입
*/

/*
- AppConfig 통해서 관심사 확실하게 분리
- 배역, 배우 생각
- AppConfig = 공연 기획자
- AppConfig는 구체 클래스 선택, 배역에 맞는 담당 배우 선택
- 애플리케이션이 어떻게 동작해야 할지 전체 구성 책임
- Impl 클래스들은 기능 실행만 책임
- OrderServiceImpl은 기능 실행하는 책임만 지면 됨
 */

/*
- 역할과 구현 클래스가 한눈에 들어옴
- 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악 가능
 */
public class AppConfig {

    //생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //OrderServiceImpl에서 콜하도록 설정
        //FixDiscountPolicy -> RateDiscountPolicy 객체로 변경
        //할인 정책을 변경해도, 애플리케이션 구성 역할을 담당하는 AppConfig만 변경하면 됨
        //클라이언트 코드인 OrderServiceImpl를 포함해서 사용 영역의 어떤 코드도 변경 필요 없음
        return new RateDiscountPolicy();
    }

}
