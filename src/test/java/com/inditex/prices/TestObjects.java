package com.inditex.prices;

import com.inditex.prices.entities.PriceEntity;
import org.openapitools.model.PriceResource;
import org.openapitools.model.PriceResponse;

import java.math.BigDecimal;
import java.util.List;

public class TestObjects {

  public static PriceEntity getPriceEntity(){
    return PriceEntity.builder()
        .id(1)
        .brandId(1)
        .productId(35455)
        .priceList(1)
        .price(new BigDecimal(35.50))
        .build();
  }

  public static PriceResource getPriceResource(){
    return PriceResource.builder()
        .brandId(1)
        .productId(35455)
        .priceList(1)
        .price(new BigDecimal(35.50))
        .build();
  }

  public static PriceResponse getPriceResponse(){
    return PriceResponse.builder()
        .prices(
            List.of(getPriceResource())
        ).build();
  }
}
