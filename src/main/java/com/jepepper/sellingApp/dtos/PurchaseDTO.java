package com.jepepper.sellingApp.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDTO {
  private Long id;
  private Long clientId;
  private Double total;
  private Boolean payed;
  private String reference;
  private String paymentMethod;
  private List<PurchaseProductDTO> products;
}
