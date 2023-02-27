package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    //싱글톤 객체는 상태를 유지하게 설계하면 안된다 -> 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문
    //무상태로 설계해야한다 -> 의존적 필드, 값을 변결할 수 있는 필드X (공유필드는 항상 조심)
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int userA = statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userB = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userA);

        Assertions.assertThat(userA).isNotSameAs(userB);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}