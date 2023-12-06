package acc.br.hello.controller;

//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;

//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String home(){
        return "Hello Word";
    }

//    @RequestMapping(method= RequestMethod.POST, value="/echo", produces = "text/plain")
//    public String echo(@RequestBody String body){

//        return body;
//    }

}


/*

@RestController
public class HelloEndPoint{

    @RequestMapping("/")
    public String home(){
        return "Hello Word";
    }

    @RequestMapping(method=RequestMethod.POST, value = "/echo", produces="text/plain")
    public String echo(@RequestBody String body){
        return body;
    }
}

 */