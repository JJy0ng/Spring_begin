package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    //스프링 빈은 객체 생성 -> 의존관계 주입과 같은 라이프사이클을 가진다
    //스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    //초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완로된 후 허출
    //소멸전 콜백 : 빈이 소멸되기 직전에 호출
    @Test
    public void lifeCycleTest(){
        //닫아주기 위해서 하위 인터페이스까지 내려감
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return  networkClient;
        }
    }
}
