package com.rusinek.msscbeerorderservice.services;

import com.rusinek.brewery.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

/**
 * Created by Adrian Rusinek on 10.04.2020
 **/
public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);
}
