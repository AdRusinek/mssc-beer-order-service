package com.rusinek.msscbeerorderservice.statemachine.actions;

import com.rusinek.brewery.model.events.AllocationFailureEvent;
import com.rusinek.msscbeerorderservice.domain.BeerOrderEventEnum;
import com.rusinek.msscbeerorderservice.domain.BeerOrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.rusinek.msscbeerorderservice.config.JmsConfig.ALLOCATE_FAILURE_QUEUE;
import static com.rusinek.msscbeerorderservice.services.BeerOrderManagerImpl.ORDER_ID_HEADER;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@RequiredArgsConstructor
@Component
@Slf4j
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(ORDER_ID_HEADER);

        jmsTemplate.convertAndSend(ALLOCATE_FAILURE_QUEUE, AllocationFailureEvent.builder()
                .orderId(UUID.fromString(beerOrderId))
                .build());

        log.debug("Sent Allocation Failure message to queue for order id " + beerOrderId);
    }
}
