package com.inditex.prices.mappers;

import com.inditex.prices.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.PriceResource;
import org.openapitools.model.PriceResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  default PriceResource toPriceResource(PriceEntity priceEntity) {
    return PriceResource.builder()
        .productId(priceEntity.getProductId())
        .brandId(priceEntity.getBrandId())
        .priceList(priceEntity.getPriceList())
        .startDate(priceEntity.getStartDate())
        .endDate(priceEntity.getEndDate())
        .price(priceEntity.getPrice())
        .build();
  }
  List<PriceResource> toPriceResourceList(List<PriceEntity> priceEntityList);

  default PriceResponse toPriceResponse(List<PriceEntity> prices) {
    return PriceResponse.builder()
            .prices(toPriceResourceList(prices))
            .build();
  }
}
