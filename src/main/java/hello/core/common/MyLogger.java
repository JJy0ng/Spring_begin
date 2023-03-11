package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;
//로그를 출력하기 위해서 만듬
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 빈은 HTTP 요청 당 하나씩 생성, 요청이 끝나면 소멸
public class MyLogger { //적용대상이 클래스인 경우는 TARGET_CLASS, 인터페이스면 INTERFACES 선택 -> 가짜 프록시 클래스를 만들어줌 -> 가짜 프록시 객체가 등록된다
    private String uuid;
    private String requestURL;

    //빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력 받음
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + "[" + message + "]");
    }

    @PostConstruct //초기화 메서드를 사용해서 uuid를 생성해서 저장
    public void init(){
        uuid = UUID.randomUUID().toString(); //절대로 아이디가 겹치지가 않음, 겹칠 확률이 극히 낮다
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
