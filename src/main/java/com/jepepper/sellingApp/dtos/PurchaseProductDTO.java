package com.jepepper.sellingApp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseProductDTO {
    private Long productId;
    private Integer quantity;
    private Double total;
    private ProductDTO productDTO;
}
