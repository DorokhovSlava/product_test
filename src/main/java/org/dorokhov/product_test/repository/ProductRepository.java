package org.dorokhov.product_test.repository;

import org.dorokhov.product_test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

     Product getProductByTitle(String title);
}
