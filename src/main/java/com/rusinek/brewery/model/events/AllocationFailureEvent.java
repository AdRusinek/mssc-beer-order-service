package com.rusinek.brewery.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by Adrian Rusinek on 09.04.2020
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationFailureEvent {

    private UUID orderId;
}
