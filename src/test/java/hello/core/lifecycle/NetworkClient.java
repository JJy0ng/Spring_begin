package hello.core.lifecycle;


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
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될 때 호출
    public void close(){
        System.out.println("NetWorkClient.close");
        disconnect();
    }
    //메서드 이름을 자유롭게 줄 수 있음
    //스프링 빈이 스프링 코드에 의존X
    //코드가 아니라 설정 정보를 사용하기에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서더를 적용할 수 있음
}
