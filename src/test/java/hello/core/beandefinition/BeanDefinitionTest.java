package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    //BeanFactory(interface) -> ApplicationContext(interface) -> AnnotationConfigApplicationContext
    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //BeanDefinition(추상화) -> 빈 설정 메타정보 @Bean 당 하나씩 메타 정보가 생김 -> 스프링 컨테이너는 메타정보를 기반으로 스프링 빈 생성

    GenericApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                " beanDefinition = " + beanDefinition);
            }
        }
    }
}
