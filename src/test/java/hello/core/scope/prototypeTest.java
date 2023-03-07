package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class prototypeTest {
    //프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고 초기화 메서드도 생성된다
    //완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행된 것을 확인할 수 있음 -> 생성과 의존관계 주입 그리고 초기화까지만 관여
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
//스프링 컨터이너에 요청할 때 마다 새로 생성
//종료 메서드 호출X, 프로토타입 빈은 조회한 클라이언트가 관리해야함 -> 종료 메서드에 대한 호출도 직점 해줘야함
