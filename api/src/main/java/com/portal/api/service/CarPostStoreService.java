package com.portal.api.service;


import com.portal.api.dto.CarPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarPostStoreService {

    List<CarPostDto> getCarForSales();

    void changeCarForSale(CarPostDto carPostDto, String id);

    void removeCarForSale(String id);

}
