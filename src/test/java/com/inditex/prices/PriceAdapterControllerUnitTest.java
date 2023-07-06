package com.inditex.prices;

import com.inditex.prices.adapters.PriceControllerAdapter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceAdapterControllerUnitTest {

  @Autowired
  private PriceControllerAdapter controller;

  //Test Case when the results are ok
  @ParameterizedTest(name = "Test {index}: petición a las {1}:00 del día {0} del producto 35455 para la brand 1 (ZARA)")
  @CsvSource({
          "14, 10, 35.50",
          "14, 16, 25.45",
          "14, 21, 35.50",
          "15, 10, 35.50",
          "16, 21, 38.95"
  })
  void testInditex(int dayOfMonth, int hour, BigDecimal price) {
   var date = OffsetDateTime.of(2020, 6, dayOfMonth, hour, 0, 0, 0, ZoneOffset.UTC);
    var productId = 35455;
    var brandId = 1;

    var result = controller.getPrices(date, productId, brandId);
    assertNotNull(result);
    assertEquals(200, result.getStatusCodeValue());
    assertNotNull(result.getBody());
    assertEquals(price, result.getBody().getPrices().get(0).getPrice());
  }

}
