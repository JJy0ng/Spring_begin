package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    //디폴트 생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }
    //의존관계주입 후 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될 때 호출
    @PreDestroy
    public void close(){
        System.out.println("NetWorkClient.close");
        disconnect();
    }
    //스프링에서 가장 권장하는 방법, 자바 표준
    //외부 라이브러리에는 적용하지 못한다, 외부 라이브러리를 초기화, 종료해야 하면 @Bean을 이용하자
}
