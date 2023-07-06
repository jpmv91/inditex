package com.inditex.prices.services;

import com.inditex.prices.entities.PriceEntity;
import com.inditex.prices.ports.in.PriceServicePort;
import com.inditex.prices.ports.out.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PriceServiceUseCase implements PriceServicePort {

  private final PriceRepositoryPort priceRepository;

  @Override
  public List<PriceEntity> getPrices(OffsetDateTime date, Integer productId, Integer brandId) {
    return priceRepository.findPrice(date, productId, brandId).stream().findFirst().stream().collect(Collectors.toList());
  }

  @Override
  public boolean getProductId(Integer productId) {
    return priceRepository.findProductId(productId);
  }

  @Override
  public boolean getBrandId(Integer brandId) {
    return priceRepository.findBrandId(brandId);
  }


}
