package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.dto.CustomPage;
import com.halfacode.spring_real_time_learning.dto.ProductDTO;
import com.halfacode.spring_real_time_learning.entity.Product;
import com.halfacode.spring_real_time_learning.mapper.ProductMapper;
import com.halfacode.spring_real_time_learning.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    // Change RedisTemplate to be type-safe with your CustomPage<ProductDTO>
  //  private final RedisTemplate<String, Object> redisTemplate;

    public CustomPage<ProductDTO> getPaginatedProducts(String keyword, Pageable pageable) {
        /*String redisKey = buildRedisKey(keyword, pageable);

        CustomPage<ProductDTO> cached = (CustomPage<ProductDTO>) redisTemplate.opsForValue().get(redisKey);
        if (cached != null) {
            System.out.println("🔁 Returning from Redis: " + redisKey);
            return cached;
        }*/

        Page<Product> page = productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        Page<ProductDTO> dtoPage = page.map(productMapper::toDto);

        CustomPage<ProductDTO> customPage = new CustomPage<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
               dtoPage.getTotalPages(),
              dtoPage.isFirst(),
               dtoPage.isLast()
        );
      //  redisTemplate.opsForValue().set(redisKey, customPage, 10, TimeUnit.MINUTES);

        return customPage;
    }

    // Helper to build Redis key with pagination and sorting info
    public String buildRedisKey(String keyword, Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        Sort.Order order = pageable.getSort().stream()
                .findFirst()
                .orElse(Sort.Order.asc("id"));

        String direction = order.getDirection().isAscending() ? "asc" : "desc";
        String property = order.getProperty();

        return String.format("products:%s:%d:%d:%s:%s", keyword, page, size, property, direction);
    }
}
