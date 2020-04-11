package com.rusinek.msscbeerorderservice.web.mappers;

import com.rusinek.brewery.model.BeerOrderLineDto;
import com.rusinek.msscbeerorderservice.domain.BeerOrderLine;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-11T03:21:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
@Primary
public class BeerOrderLineMapperImpl extends BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    @Autowired
    @Qualifier("delegate")
    private BeerOrderLineMapper delegate;

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto)  {
        return delegate.dtoToBeerOrderLine( dto );
    }
}
