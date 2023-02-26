package hello.core.singleton;
//이 방법은 단순하고 안전하나 DIP 위반 -> 유연성이 떨어지며 안티 패턴이다
public class SingletonService {
    //static 영역에서 객체가 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    //public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회 허용
    public static SingletonService getInstance(){
        return instance;
    }

    //외부에서 객체 생성하는 것을 막음
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
