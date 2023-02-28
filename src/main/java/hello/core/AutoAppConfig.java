package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component 애노테이션이 붙은 클래스를 찾아 자동으로 스프링 빈에 등록을 시켜줌, 등록될 때 앞 클래스 명 소문자로 등록된다.
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 기존 AppConfig를 뺌 -> 충돌 방지, @Configuration에 들어가면 @Component가 있는 것을 알 수 있음

public class AutoAppConfig {

}
