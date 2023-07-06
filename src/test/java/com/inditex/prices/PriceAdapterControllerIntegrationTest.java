package com.inditex.prices;

import com.inditex.prices.adapters.PriceControllerAdapter;
import com.inditex.prices.entities.PriceEntity;
import com.inditex.prices.exceptions.GlobalExceptionHandler;
import com.inditex.prices.mappers.PriceMapper;
import com.inditex.prices.ports.in.PriceServicePort;
import lombok.SneakyThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.inditex.prices.TestObjects.getPriceEntity;
import static com.inditex.prices.TestObjects.getPriceResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {PriceControllerAdapter.class})
public class PriceAdapterControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PriceServicePort pricesService;

  @MockBean
  private PriceMapper priceMapper;

  private final PriceEntity priceEntity = getPriceEntity();

  @Test
  @SneakyThrows
  @DisplayName("Should not get price because of date")
  void testGetPrices_Case1() {
    ArrayList<PriceEntity> list = new ArrayList<>();
    when(pricesService.getProductId(anyInt())).thenReturn(true);
    when(pricesService.getBrandId(anyInt())).thenReturn(true);
    when(pricesService.getPrices(any(), anyInt(), anyInt())).thenReturn(list);

    // Realizar la solicitud GET
    mockMvc.perform(get("/prices?date=2021-06-14T09:00:00.000Z&brandId=1&productId=35455"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @SneakyThrows
  @DisplayName("Should not get price because of product id does not exist ")
  void testGetPrices_Case2()  {

    ArrayList<PriceEntity> list = new ArrayList<>();
    when(pricesService.getProductId(anyInt())).thenReturn(false);
    when(pricesService.getBrandId(anyInt())).thenReturn(true);
    when(pricesService.getPrices(any(), anyInt(), anyInt())).thenReturn(list);
    // Realizar la solicitud GET
    mockMvc.perform(get("/prices?date=2023-06-14T09:00:00.000Z&brandId=1&productId=35456"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @SneakyThrows
  @DisplayName("Should not get price because of brand id does not exist")
  void testGetPrices_Case3() {

    ArrayList<PriceEntity> list = new ArrayList<>();
    when(pricesService.getProductId(anyInt())).thenReturn(true);
    when(pricesService.getBrandId(anyInt())).thenReturn(false);
    when(pricesService.getPrices(any(), anyInt(), anyInt())).thenReturn(list);
    // Realizar la solicitud GET
    mockMvc.perform(get("/prices?date=2022-06-14T09:00:00.000Z&brandId=3&productId=35455"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @SneakyThrows
  @DisplayName("Should not get price because of brand id is not correct value")
  void testGetPrices_Case4() {

    ArrayList<PriceEntity> list = new ArrayList<>();
    when(pricesService.getProductId(anyInt())).thenReturn(true);
    when(pricesService.getBrandId(anyInt())).thenReturn(false);
    when(pricesService.getPrices(any(), anyInt(), anyInt())).thenReturn(list);
    // Realizar la solicitud GET
    mockMvc.perform(get("/prices?date=2022-06-14T09:00:00.000Z&brandId=-1&productId=35455"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @SneakyThrows
  @DisplayName("Should get price")
  void testGetPrices_Case5()  {

  var priceResponse = getPriceResponse();
    when(pricesService.getPrices(any(), anyInt(), anyInt())).thenReturn(List.of(priceEntity));
    when(pricesService.getProductId(anyInt())).thenReturn(true);
    when(pricesService.getBrandId(anyInt())).thenReturn(true);
    when(priceMapper.toPriceResponse(anyList())).thenReturn(priceResponse);

    mockMvc.perform(get("/prices?date=2020-06-14T09:00:00.000Z&brandId=1&productId=35455")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }




}