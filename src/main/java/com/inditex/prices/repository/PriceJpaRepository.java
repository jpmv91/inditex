package com.inditex.prices.repository;

import com.inditex.prices.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

  @Query(value = "select p " +
      "from PriceEntity p " +
      "where :date <= p.endDate " +
      "and :date >= p.startDate " +
      "and p.productId = :productId " +
      "and p.brandId = :brandId " +
      "order by p.priority desc")
  List<PriceEntity> findAllByBrandIdAndProductIdAndEndDate(@Param("date") OffsetDateTime date, @Param("productId") Integer productId, @Param("brandId") Integer brandId);



  boolean existsByBrandId(Integer brandId);

  boolean existsByProductId(Integer productId);

}
