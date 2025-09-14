package com.noheezybucket.foadjeeapp.repository;

import com.noheezybucket.foadjeeapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
