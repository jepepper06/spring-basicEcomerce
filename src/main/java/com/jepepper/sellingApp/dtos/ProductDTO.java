package com.jepepper.sellingApp.dtos;

import com.jepepper.sellingApp.domain.Category;
import lombok.Data;

import java.net.URL;
@Data
public class ProductDTO {
    private Long id;
    private URL image;
    private String name;
    private String description;
    private Double price;
    private Category category;
}
