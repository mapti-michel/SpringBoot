package acc.br.hello.ws.endpoints.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndPoint {

    @RequestMapping("/")

    public String hello(){
        return "Hello Word!";
    }


}
