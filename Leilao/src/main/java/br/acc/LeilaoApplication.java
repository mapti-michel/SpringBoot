package br.acc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class LeilaoApplication {

	@RequestMapping("/resource")
	public Map<String, Object> home(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello Wold");
		return model;
	}

/*	@RequestMapping("/user")
	public Principal user(Principal user){
		return user;
	}*/

	public static void main(String[] args) {

		SpringApplication.run(LeilaoApplication.class, args);
	}



}
