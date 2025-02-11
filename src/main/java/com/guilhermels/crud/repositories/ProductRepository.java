package com.guilhermels.crud.repositories;

import com.guilhermels.crud.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("SELECT p FROM ProductEntity p ORDER BY p.updatedAt DESC")
    Page<ProductEntity> findAllProducts(Pageable pageable);
}
