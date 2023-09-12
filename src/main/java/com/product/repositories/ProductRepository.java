package com.product.repositories;

import org.springframework.data.repository.CrudRepository;
import com.product.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	

}
