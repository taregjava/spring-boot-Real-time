package com.halfacode.spring_real_time_learning.controller;

import com.halfacode.spring_real_time_learning.dto.CustomPage;
import com.halfacode.spring_real_time_learning.dto.ProductDTO;
import com.halfacode.spring_real_time_learning.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

   /* @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductDTO> result = productService.getPaginatedProducts(keyword, pageable);
        return ResponseEntity.ok(result);
    }
}*/

   /* @GetMapping()
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<ProductDTO> page = productService.getPaginatedProducts(keyword, pageable);

        CustomPage<ProductDTO> customPage = new CustomPage<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );

        return ResponseEntity.ok(customPage);
    }*/

   /* @GetMapping()
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 5) Pageable pageable) {

        CustomPage<ProductDTO> customPage = productService.getPaginatedProducts(keyword, pageable);
        return ResponseEntity.ok(customPage);
    }
*/

    @GetMapping()
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 5) Pageable pageable) {

        CustomPage<ProductDTO> customPage = productService.getPaginatedProducts(keyword, pageable);
        return ResponseEntity.ok(customPage);
    }

}