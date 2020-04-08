package com.rusinek.msscbeerorderservice.services.listeners;

import com.rusinek.brewery.model.events.ValidateOrderResult;
import com.rusinek.msscbeerorderservice.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.rusinek.msscbeerorderservice.config.JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result) {
        final UUID beerOrderId = result.getOrderId();

        log.debug("Validation Result for Order Id: " + beerOrderId);

        beerOrderManager.processValidationResult(beerOrderId, result.getIsValid());

    }
}
