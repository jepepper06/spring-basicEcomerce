package com.jepepper.sellingApp.dtos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreationDTO {
    private String name;
    private MultipartFile image;
    private String description;
    @Value("${stuff.categoryId:#{null}}")
    private Long categoryId;
    private Long stock;
    private Double price;
}
