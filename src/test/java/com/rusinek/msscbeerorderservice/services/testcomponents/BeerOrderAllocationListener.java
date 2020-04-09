package com.rusinek.msscbeerorderservice.services.testcomponents;

import com.rusinek.brewery.model.events.AllocateOrderRequest;
import com.rusinek.brewery.model.events.AllocateOrderResult;
import com.rusinek.msscbeerorderservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(Message message) {
        AllocateOrderRequest request = (AllocateOrderRequest) message.getPayload();
        boolean pendingInventory = false;
        boolean allocationError = false;

        if (request.getBeerOrderDto().getCustomerRef() != null && request.getBeerOrderDto().getCustomerRef().equals("partial-allocation")) {
            pendingInventory = true;
        }

        if (request.getBeerOrderDto().getCustomerRef() != null && request.getBeerOrderDto().getCustomerRef().equals("fail-allocation")) {
            allocationError = true;
        }

        boolean finalPendingInventory = pendingInventory;
        request.getBeerOrderDto().getBeerOrderLines().forEach(beerOrderLineDto -> {
           if (finalPendingInventory) {
               beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity() - 1);
           } else {
               beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity());
           }

        });

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                AllocateOrderResult.builder()
                        .beerOrderDto(request.getBeerOrderDto())
                        .pendingInventory(pendingInventory)
                        .allocationError(allocationError)
                        .build());
    }
}