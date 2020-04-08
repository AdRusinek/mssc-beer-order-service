package com.rusinek.brewery.model.events;

import com.rusinek.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderResult {

    private BeerOrderDto beerOrderDto;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
}
