package com.example.demo.ws.endpoints.rest;

import com.example.demo.service.QuoteService;
import com.example.demo.ws.obj.Quote;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoEndPoint {

    private QuoteService quoteService;

    public DemoEndPoint(QuoteService quoteService){
        this.quoteService = quoteService;
    }


    @RequestMapping("/hello")
    public String sayHelloWord(){
        return "Hello, Spring Boot! Welcome !";
    }

    @RequestMapping("/quote")
    public Quote getQuote(){
        return this.quoteService.getQuote();
    }

    @RequestMapping("/other")
    public String other(){
        return "Other is working";
    }


    @RequestMapping(value = "/quote", method = RequestMethod.POST)
    public void addQuote(@RequestBody Quote quote){
        this.quoteService.addQuote(quote);
    }


/*
    private RestTemplate template;

    public DemoEndPoint(RestTemplate template){
        this.template = template;
    }

    @RequestMapping("/template") // Não funcionou
    public String template(){
        String message = this.template.getForObject("http://localhost:8070", String.class); //String.class -> Tipo de resposta
        return message;
    }


*/

}
