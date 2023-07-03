package com.inditex.prices.adapters;

import com.inditex.prices.mappers.PriceMapper;
import com.inditex.prices.ports.in.PriceServicePort;
import lombok.AllArgsConstructor;

import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@RestController
@AllArgsConstructor
public class PriceControllerAdapter implements PricesApi {

  private final PriceServicePort service;
  private final PriceMapper mapper;

  @Override
  public ResponseEntity<PriceResponse> getPrices( @Valid  OffsetDateTime date, @NotNull @Valid Integer productId, @NotNull @Valid Integer brandId) {
    var prices = service.getPrices(date, productId, brandId);
    var priceResponse = mapper.toPriceResponse(prices);

    return ResponseEntity.ok(priceResponse);
  }

}

