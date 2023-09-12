package com.product.controllers;

import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.product.helper.ResponseWrapper;
import com.product.models.Brand;
import com.product.services.BrandService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/brands")
public class BrandController {

	@Autowired
	BrandService brandService;

	@GetMapping("")
	public ResponseEntity<?> getAllBrands() {
		Iterable<Brand> brand = brandService.getAllbrands();
		Iterator<Brand> all_brand = brand.iterator();
		if (all_brand.hasNext()) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setMessage("Following Brand found");
			rw.setData(all_brand);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no brand, please add brands ");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBrandByID(@PathVariable int id) {
		Brand found_brand = brandService.getBrandByID(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Brand found by Id");
		rw.setData(found_brand);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createBrand(@RequestBody @Valid Brand brand) {
		Brand created_brand = brandService.createBrand(brand);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("New Brand Added successfully.");
		rw.setData(created_brand);
		return new ResponseEntity<>(rw, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBrandByID(@PathVariable int id, @RequestBody Brand brand) {
		Brand updated_brand = brandService.updateBrand(id, brand);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Brand updated by Id successfully.");
		rw.setData(updated_brand);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delteBrandByID(@PathVariable int id) {
		brandService.deleteBrand(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Brand deleted by Id successfully.");
		rw.setData(null);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}
	
	// One To Many
	@PutMapping("/{brand_id}/product/{product_id}")
    public ResponseEntity<?> assignBrandToProduct(@PathVariable int product_id, @PathVariable int brand_id) 
     {
         Brand add_product =  brandService.BrandToProduct(product_id, brand_id);
         ResponseWrapper srw= new ResponseWrapper();
		 srw.setMessage("Product added to Brand");
		 srw.setData(add_product);
        return new ResponseEntity<>(srw,HttpStatus.OK);
     }

}
