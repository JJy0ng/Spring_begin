package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final이 붙은 파라미터의 생성자를 만듬
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
    /*
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //생성자 주입 방식
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */

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