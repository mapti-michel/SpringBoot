package com.portal.api.service;

import com.portal.api.dto.OwnerPostDto;
import org.springframework.stereotype.Service;

@Service
public interface OwnerPostService {

    void createOwnerCar(OwnerPostDto ownerPostDto);

}
