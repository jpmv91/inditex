package com.inditex.prices.ports.out;

import com.inditex.prices.entities.PriceEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepositoryPort {

  List<PriceEntity> findPrice(OffsetDateTime date,  Integer productId,  Integer brandId);

  boolean findProductId(Integer productId);

  boolean findBrandId(Integer brandId);
}
