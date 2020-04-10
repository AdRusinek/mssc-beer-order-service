package com.rusinek.msscbeerorderservice.web.mappers;

import com.rusinek.brewery.model.CustomerDto;
import com.rusinek.msscbeerorderservice.domain.Customer;
import org.mapstruct.Mapper;

/**
 * Created by Adrian Rusinek on 10.04.2020
 **/
@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}
