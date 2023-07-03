package com.inditex.prices.ports.out;

import com.inditex.prices.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceRepositoryPort {

  List<PriceEntity> findPrice(OffsetDateTime date,  Integer productId,  Integer brandId);

}
