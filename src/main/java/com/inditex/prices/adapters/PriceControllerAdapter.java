package com.inditex.prices.adapters;

import com.inditex.prices.exceptions.EntityNotFoundException;
import com.inditex.prices.mappers.PriceMapper;
import com.inditex.prices.ports.in.PriceServicePort;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.PricesApi;
import org.openapitools.model.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Slf4j
@RestController
@AllArgsConstructor
public class PriceControllerAdapter implements PricesApi{

  private final PriceServicePort service;
  private final PriceMapper mapper;

  @Override
  public ResponseEntity<PriceResponse> getPrices(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date, @NotNull @Valid Integer productId, @NotNull @Valid Integer brandId) {

    if (productId <= 0 || brandId <= 0) {
      throw new ValidationException("Product and brand identifiers should be positive values.");
    }
    var existProduct = service.getProductId(productId);
    if(!existProduct) throw new EntityNotFoundException("The id product does not exist");

    var existBrandId = service.getBrandId(brandId);
    if(!existBrandId) throw new EntityNotFoundException("The id brand does not exist");

    var prices = service.getPrices(date, productId, brandId);

    if (prices.isEmpty()){
      throw new EntityNotFoundException("There is no price on that date");
    }

    var priceResponse = mapper.toPriceResponse(prices);

    return ResponseEntity.ok(priceResponse);
  }

}

