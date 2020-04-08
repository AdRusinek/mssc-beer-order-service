package com.rusinek.msscbeerorderservice.services;

import com.rusinek.msscbeerorderservice.domain.BeerOrder;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);
}
