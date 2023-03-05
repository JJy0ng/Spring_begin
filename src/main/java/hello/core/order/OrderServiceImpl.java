package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 필드들을 모아서 생성자를 만듬
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository; //앞에 @Autowired을 붙이면 필드 주입 -> 안티 패턴(사용하지 말자), 테스트 코드에 사용
    private final DiscountPolicy discountPolicy;

    //수정자(setter) 주입, 필드에 final이 없어야 함, 선택, 변경 가능성이 있는 의존관계에 사용

    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }

    @Autowired // 일반 메서드 주입, 한 번에 여러 필드를 주입 받을 수 있음 -> 일반적으로 사용하진 않음, final x
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
    */

    //@Autowired x -> 생성자가 1개 있으면 @Autowired 생략 가능 불변, 필수 의존 관계에서 사용

    //조회 빈이 2개 이상일 때 - 문제점 해결 방안
    //discountPolicy -> rateDiscountPolicy 로 바꾸어서 필드명, 파라미터명으로 빈 이름이 매칭 가능 -> @Autowired 매칭
    //@Qualifier 는 @Qualifier 를 찾는 용도로만 사용하는 것이 좋다
    //@Primary 는 여러 빈이 매칭되면 우선순위를 갖는다
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { //생성자 주입 방식
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}