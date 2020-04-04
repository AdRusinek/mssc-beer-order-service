package com.rusinek.msscbeerorderservice.web.mappers;

import com.rusinek.msscbeerorderservice.domain.BeerOrderLine;
import com.rusinek.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
