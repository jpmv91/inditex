package com.inditex.prices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRICES")
public class PriceEntity {

  @Id
  @Column(name = "ID")
  private Integer id;

  @Column(name = "BRAND_ID")
  private Integer brandId;

  @Column(name = "START_DATE")
  private OffsetDateTime startDate;

  @Column(name = "END_DATE")
  private OffsetDateTime endDate;

  @Column(name = "PRICE_LIST")
  private Integer priceList;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Column(name = "PRIORITY")
  private Short priority;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "CURR")
  private String curr;

}
