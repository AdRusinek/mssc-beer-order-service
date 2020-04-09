package com.rusinek.msscbeerorderservice.services.listeners;

import com.rusinek.brewery.model.events.AllocateOrderResult;
import com.rusinek.msscbeerorderservice.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.rusinek.msscbeerorderservice.config.JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void listen(AllocateOrderResult result) {
        if (!result.getAllocationError() && !result.getPendingInventory()) {
            //allocated normally
            beerOrderManager.beerOrderAllocationPassed(result.getBeerOrderDto());
        } else if (!result.getAllocationError() && result.getPendingInventory()) {
            //pending inventory
            beerOrderManager.beerOrderAllocationPendingInventory(result.getBeerOrderDto());
        } else if (result.getAllocationError()) {
            //allocation error
            beerOrderManager.beerOrderAllocationFailed(result.getBeerOrderDto());
        }
    }
}
