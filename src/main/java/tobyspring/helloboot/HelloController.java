package tobyspring.helloboot;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping("/hello")
public class HelloController {
    // final 까지 넣으면 에러가 뜸으로 좋음.
    private final HelloService helloService;

    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody  // ResponseBody가 없으면 View라고 읽기 때문에 404에러 뜸.
    public String hello(String name){ // String Type 일 경우 View 쪽임.
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
