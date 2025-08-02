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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

  /*  @GetMapping
    public Page<ProductDTO> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return productService.getPaginatedProducts(keyword, pageRequest);
    }*/

    @GetMapping()
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @PageableDefault(size = 5) Pageable pageable) {

        CustomPage<ProductDTO> customPage = productService.getPaginatedProducts(keyword, pageable);
        return ResponseEntity.ok(customPage);
    }
/*@GetMapping()
public ResponseEntity<CustomPage<ProductDTO>> getProducts(
        @RequestParam(defaultValue = "") String keyword,
        @PageableDefault(size = 5)
        @SortDefault.SortDefaults({
            @SortDefault(sort = "price", direction = Sort.Direction.ASC)
        }) Pageable pageable) {

    CustomPage<ProductDTO> customPage = productService.getPaginatedProducts(keyword, pageable);
    return ResponseEntity.ok(customPage);
}
*/
}
