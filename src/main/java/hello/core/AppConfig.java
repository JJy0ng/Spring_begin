package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //환경설정 정보
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //여기서 싱글톤이 깨지는가? -> 같은 것이 호출되기에 깨지지 않는다
    @Bean //스프링 빈, 이름 부여도 가능(항상 이름이 달라야 함)
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //객체를 생성하는 값을 연결(주입)해주는 역할, 의존관계 주입
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        //return new OrderServiceImpl(memberRepository(),discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
