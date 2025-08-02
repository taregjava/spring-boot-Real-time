package com.halfacode.spring_real_time_learning.mapper;

import com.halfacode.spring_real_time_learning.dto.ProductDTO;
import com.halfacode.spring_real_time_learning.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
