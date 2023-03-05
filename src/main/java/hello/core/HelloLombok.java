package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//Lombok이 게터 세터를 자동으로 만들어줌
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("aaaa");
        
        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok= " + helloLombok);
    }
            
}
