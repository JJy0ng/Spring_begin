package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.OrderComparator;

@Configuration
//@Component 애노테이션이 붙은 클래스를 찾아 자동으로 스프링 빈에 등록을 시켜줌, 등록될 때 앞 클래스 명 소문자로 등록된다
//애노테이션에는 상속관계가 없으며 스프링이 지원하는 기능이다
@ComponentScan(
//        basePackages = "hello.core.member", //멤버에 관한 클래스만 등록이 된다, 탐색할 패키지의 시작 위치를 지정한다. 이를 포함해서 하위 패키지 탐색
//        basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 시작 위치로 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 기존 AppConfig를 뺌 -> 충돌 방지, @Configuration에 들어가면 @Component가 있는 것을 알 수 있음

//자동 빈과 자동 빈 경우에는 오류가 나옴
public class AutoAppConfig { //수동 등록 빈이 우선권을 가짐, 로그가 나옴

    //스프링 설정을 목적으로 하는 곳에서만 필드 주입 사용 가능
    /*
    @Autowired MemberRepository memberRepository;
    @Autowired DiscountPolicy discountPolicy;
    @Bean
    OrderService orderService(){
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    } //빈 수동 등록

     */
}
