package com.rusinek.msscbeerorderservice.services;

import com.rusinek.brewery.model.BeerOrderDto;
import com.rusinek.msscbeerorderservice.domain.BeerOrder;

import java.util.UUID;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDto beerOrderDto);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrderDto);

    void beerOrderAllocationFailed(BeerOrderDto beerOrderDto);

    void beerOrderPickedUp(UUID id);
}
