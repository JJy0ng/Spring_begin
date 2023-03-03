package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //여기에 @ComponentScan이 있다.
public class CoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
	//자동 빈과 수동 빈 이름이 같을 때 여기서 오류가 나도록 한다
}
