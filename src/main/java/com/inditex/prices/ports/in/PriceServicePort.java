package com.inditex.prices.ports.in;

import com.inditex.prices.entities.PriceEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceServicePort {
  List<PriceEntity> getPrices(OffsetDateTime date, Integer productId, Integer brandId);

  boolean getProductId(Integer productId);

  boolean getBrandId(Integer brandId);
}
