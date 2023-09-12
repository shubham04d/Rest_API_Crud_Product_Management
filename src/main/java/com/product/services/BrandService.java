package com.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.product.models.Brand;
import com.product.models.Product;
import com.product.repositories.BrandRepository;
import com.product.repositories.ProductRepository;

@Service
public class BrandService {

	@Autowired
	BrandRepository brandRepository;
	ProductRepository productRepository;
	

	public Iterable<Brand> getAllbrands() {
		return brandRepository.findAll();
	}

	public Brand getBrandByID(int id) {
		Brand found_brand = brandRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no brand , with this id ");
		});
		return found_brand;
	}

	public Brand createBrand(Brand brand) {
		Brand inserted_brand = brandRepository.save(brand);
		brand.setCreatedAt(inserted_brand.getCreatedAt());
		return inserted_brand;
	}

	public Brand updateBrand(int id, Brand brand) {
		Brand found_brand = getBrandByID(id);
		brand.setBrand_id(found_brand.getBrand_id());
		brand.setCreatedAt(found_brand.getCreatedAt());
		brand.setUpdatedAt(found_brand.getUpdatedAt());
		Brand update_brand = brandRepository.save(brand);
		return update_brand;
	}

	public void deleteBrand(int id) {
		getBrandByID(id);
		brandRepository.deleteById(id);
	}
	
	// One To Many
	public Brand BrandToProduct(int product_id, int brand_id) 
    {
        Product product = productRepository.findById(product_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        
        Brand brand = brandRepository.findById(brand_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
        
        brand.setProduct(product);
        return  brandRepository.save(brand);
    
    }
}
