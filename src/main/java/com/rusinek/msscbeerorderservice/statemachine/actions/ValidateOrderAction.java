package com.rusinek.msscbeerorderservice.statemachine.actions;

import com.rusinek.brewery.model.events.ValidateOrderRequest;
import com.rusinek.msscbeerorderservice.domain.BeerOrder;
import com.rusinek.msscbeerorderservice.domain.BeerOrderEventEnum;
import com.rusinek.msscbeerorderservice.domain.BeerOrderStatusEnum;
import com.rusinek.msscbeerorderservice.repositories.BeerOrderRepository;
import com.rusinek.msscbeerorderservice.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.rusinek.msscbeerorderservice.config.JmsConfig.VALIDATE_ORDER_QUEUE;
import static com.rusinek.msscbeerorderservice.services.BeerOrderManagerImpl.ORDER_ID_HEADER;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                    .build());

        log.debug("Sent Validation request to queue for order id " + beerOrderId);
    }
}
