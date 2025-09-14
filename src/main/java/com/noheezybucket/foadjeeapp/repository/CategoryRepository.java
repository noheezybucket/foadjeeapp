package com.noheezybucket.foadjeeapp.repository;

import com.noheezybucket.foadjeeapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
