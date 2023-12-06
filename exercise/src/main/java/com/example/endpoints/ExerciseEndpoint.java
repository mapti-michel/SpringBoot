package com.example.endpoints;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseEndpoint {

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello Word !";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(@RequestBody String body){
        return "Olá " + body;
    }


}
