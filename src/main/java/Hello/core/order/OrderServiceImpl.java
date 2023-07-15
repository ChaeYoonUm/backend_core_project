package Hello.core.order;

import Hello.core.discount.DiscountPolicy;
import Hello.core.member.Member;
import Hello.core.member.MemberRepository;
import Hello.core.member.MemoryMemberRepository;

/*
- OrderServiceImpl은 FixDiscountPolicy를 의존하지 않음
- DiscountPolicy 인터페이스만 의존
- OrderServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)알 수 없음
- OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정
- OrderServiceImpl은 이제부터 실행에만 집중

- OrderServiceImpl에는 MemoryMemberRepository, FixDiscountPolicy 객체의 의존관계가 주입됨
 */
public class OrderServiceImpl implements OrderService {

    //인터페이스에만 의존, Class가 뭔지 모름 --> 철저하게 DI를 지키고 있는 것
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
