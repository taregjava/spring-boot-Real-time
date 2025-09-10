package com.halfacode.spring_real_time_learning.mapper;

import com.halfacode.spring_real_time_learning.dto.ProductDTO;
import com.halfacode.spring_real_time_learning.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    List<ProductDTO> toDtoList(List<Product> products);
}

