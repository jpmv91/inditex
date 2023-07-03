package com.inditex.prices.mappers;

import com.inditex.prices.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.PriceResource;
import org.openapitools.model.PriceResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {


  PriceResource toPriceResource(PriceEntity priceEntity);

  List<PriceResource> toPriceResourceList(List<PriceEntity> priceEntityList);

  default PriceResponse toPriceResponse(List<PriceEntity> prices) {
    return PriceResponse.builder()
            .prices(toPriceResourceList(prices))
            .build();
  }
}
