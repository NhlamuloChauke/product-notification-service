package com.product.service.repository;

import com.product.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nhlamulo
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
