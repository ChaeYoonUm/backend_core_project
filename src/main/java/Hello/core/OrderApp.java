package Hello.core;

import Hello.core.member.Grade;
import Hello.core.member.Member;
import Hello.core.member.MemberService;
import Hello.core.order.Order;
import Hello.core.order.OrderService;


public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);

    }
}
