package com.inditex.prices.repository.adapters;

import com.inditex.prices.entities.PriceEntity;
import com.inditex.prices.ports.out.PriceRepositoryPort;
import com.inditex.prices.repository.PriceJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

  private final PriceJpaRepository repository;

  @Override
  public List<PriceEntity> findPrice(OffsetDateTime date, Integer productId, Integer brandId) {
    return repository.findAllByBrandIdAndProductIdAndEndDate(date, productId, brandId);
  }

  @Override
  public boolean findProductId(Integer productId) {
    return repository.existsByProductId(productId);
  }

  @Override
  public boolean findBrandId(Integer brandId) {
    return repository.existsByBrandId(brandId);
  }


}
