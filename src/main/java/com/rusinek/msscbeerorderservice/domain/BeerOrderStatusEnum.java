package com.rusinek.msscbeerorderservice.domain;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
public enum BeerOrderStatusEnum {
    NEW, VALIDATED, VALIDATION_EXCEPTION,  ALLOCATED, ALLOCATION_EXCEPTION,
    PENDING_INVENTORY, PICKED_UP, DELIVERED, DELIVERY_EXCEPTION
}
