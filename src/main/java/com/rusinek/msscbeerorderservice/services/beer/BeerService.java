package com.rusinek.msscbeerorderservice.services.beer;

import com.rusinek.brewery.model.BeerDto;

import java.util.UUID;
import java.util.Optional;

/**
 * Created by Adrian Rusinek on 05.04.2020
 **/
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}
