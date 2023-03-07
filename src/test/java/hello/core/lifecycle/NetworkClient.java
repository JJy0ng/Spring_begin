package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
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
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될 때 호출
    @Override
    public void destroy() throws Exception {
        disconnect();
    }
    //스프링 전용 인터페이스이므로 코드가 스프링 전용 인터페이스에 의존
    //초기화, 소멸 메서드의 이름을 변경할 수 없음
    //내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없음 -> 초창기 방법(거의 사용x)
}
