package com.jepepper.sellingApp.dtos;

import lombok.Data;

@Data
public class PurchasePaymentDTO {
  private Long id;
  private String reference;
  private String paymentMethod;
}

