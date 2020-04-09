package com.rusinek.msscbeerorderservice.services.testcomponents;

import com.rusinek.brewery.model.events.ValidateOrderRequest;
import com.rusinek.brewery.model.events.ValidateOrderResult;
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
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(Message message) {
        boolean isValid = true;

        ValidateOrderRequest request = (ValidateOrderRequest) message.getPayload();

        if (request.getBeerOrder().getCustomerRef() != null && request.getBeerOrder().getCustomerRef().equals("fail-validation")) {
            isValid = false;
        }

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                .isValid(isValid)
                .orderId(request.getBeerOrder().getId())
                .build());
    }
}
