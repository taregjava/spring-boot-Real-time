package com.halfacode.spring_real_time_learning;

import com.halfacode.spring_real_time_learning.entity.Product;
import com.halfacode.spring_real_time_learning.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringRealTimeLearningApplication {

	public static void main(String[] args)

	{
		SpringApplication.run(SpringRealTimeLearningApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ProductRepository productRepository) {
		return args -> {
			List<Product> products = new ArrayList<>();
			for (int i = 1; i <= 100; i++) {
				Product product = new Product();
                    // i = 5
				if (i % 5 == 0) {
					product.setName("Laptop " + i);
				} else {
					product.setName("Product " + i);
				}

				product.setPrice(Math.round((Math.random() * 1000) * 100.0) / 100.0);
				products.add(product);
			}
			productRepository.saveAll(products);
			System.out.println("✅ Inserted 100 products (including laptops)");
		};
	}


}
