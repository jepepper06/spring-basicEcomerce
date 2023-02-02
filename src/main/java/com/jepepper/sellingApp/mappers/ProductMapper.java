package com.jepepper.sellingApp.mappers;

import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import com.jepepper.sellingApp.dtos.ProductDTO;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.service.impl.ProductService;
import com.jepepper.sellingApp.service.impl.StorageProperties;
import com.jepepper.sellingApp.service.impl.StorageService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductMapper {
    private static StorageService storageService = new StorageService(new StorageProperties());
    private static ProductService productService;

    public static ProductDTO toProductDTO(Product product) throws IOException {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        URL url = storageService.loadAsResource(product.getImage()).getURL();
        productDTO.setImage(url);
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory());

        return productDTO;
    }
    public static List<ProductDTO> toProductDtoList(List<Product> products)throws IOException{
        List<ProductDTO> productDTOList = new ArrayList<>();
        products.stream().forEach(i -> {
            try {
                productDTOList.add(ProductMapper.toProductDTO(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return productDTOList;
    }
    // THAT'S NOT NECESSARY BUT BETTER CLEARER THAN CLEVER
    public static Product toProduct(ProductDTO productDTO) throws Exception {
        Product product = new Product();
        product = productService.getById(productDTO.getId());
        return product;
    }

    public static List<Product> toProductList(List<ProductDTO> productDtoList){
        List<Product> productList = new ArrayList<>();
        productDtoList.stream().forEach(item -> {
            try {
                productList.add(ProductMapper.toProduct(item));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return productList;
    }
    public static Product toProduct(ProductCreationDTO productCreationDTO){
        Product product = new Product(
                null,
                productCreationDTO.getImage().getOriginalFilename(),
                productCreationDTO.getName(),
                productCreationDTO.getDescription(),
                null,
                productCreationDTO.getPrice(),
                productCreationDTO.getStock(),
                null
        );
        return product;
    }
}
