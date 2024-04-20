package com.portal.api.controller;


import com.portal.api.dto.CarPostDto;
import com.portal.api.message.KafkaProducerMessage;
import com.portal.api.service.CarPostStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarPostController {

    @Autowired
    private CarPostStoreService carPostStoreService;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @PostMapping("/post")
    public ResponseEntity postCarForSale(@RequestBody CarPostDto carPostDto){
        kafkaProducerMessage.sendMessage(carPostDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<List<CarPostDto>> getCarSales(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carPostStoreService.getCarForSales());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeCarSale(@RequestBody CarPostDto carPostDto, @PathVariable("id") String id){
        carPostStoreService.changeCarForSale(carPostDto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteCarForSale(@PathVariable("id") String id){
        carPostStoreService.removeCarForSale(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }


}
