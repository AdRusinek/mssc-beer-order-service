package com.rusinek.msscbeerorderservice.statemachine;

import com.rusinek.msscbeerorderservice.domain.BeerOrder;
import com.rusinek.msscbeerorderservice.domain.BeerOrderEventEnum;
import com.rusinek.msscbeerorderservice.domain.BeerOrderStatusEnum;
import com.rusinek.msscbeerorderservice.repositories.BeerOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.rusinek.msscbeerorderservice.services.BeerOrderManagerImpl.ORDER_ID_HEADER;


/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;

    @Override
    public void preStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state,
                               Message<BeerOrderEventEnum> message,
                               Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition,
                               StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine) {

        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(ORDER_ID_HEADER," ")))
                .ifPresent(orderId -> {
                    log.debug("Saving state for order id: " + orderId + "Status: " + state.getId());

                    BeerOrder beerOrder = beerOrderRepository.getOne(UUID.fromString(orderId));
                    beerOrder.setOrderStatus(state.getId());
                    beerOrderRepository.saveAndFlush(beerOrder);
                });
    }
}
