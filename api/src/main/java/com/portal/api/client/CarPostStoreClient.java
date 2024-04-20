package com.portal.api.client;

import com.portal.api.dto.CarPostDto;
import com.portal.api.dto.OwnerPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CarPostStoreClient {

    private final String USER_STORE_SERVICE_URI = "http://localhost:8080/user";
    private final String POSTS_STORE_SERVICE_URI = "http://localhost:8080/sales";

    @Autowired
    RestTemplate restTemplate;

    public List<CarPostDto> carForSaleClient(){
        ResponseEntity<CarPostDto[]> responseEntity = restTemplate.getForEntity(POSTS_STORE_SERVICE_URI + "/cars", CarPostDto[].class);
        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public void ownerPostsClient(OwnerPostDto newUser){
        restTemplate.postForEntity(USER_STORE_SERVICE_URI, newUser, OwnerPostDto.class);
    }

    public void changeCarForSaleClient(CarPostDto carPostDto, String id){
        restTemplate.put(POSTS_STORE_SERVICE_URI + "/car/" + id, carPostDto, CarPostDto.class);
    }

    public void deleteCarForSaleClient(String id){
        restTemplate.delete(POSTS_STORE_SERVICE_URI + "/car/" + id);
    }



}
