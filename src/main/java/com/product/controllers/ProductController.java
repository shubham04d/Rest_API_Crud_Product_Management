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
import com.product.models.Manufacture;
import com.product.models.Product;
import com.product.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	Manufacture manufacture;

	@GetMapping("")
	public ResponseEntity<?> getAllProducts() {
		Iterable<Product> product = productService.getAllproducts();
		Iterator<Product> all_product = product.iterator();
		if (all_product.hasNext()) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setMessage("Following Product found");
			rw.setData(all_product);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product, please add products ");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable int id) {
		Product found_product = productService.getProductByID(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Product found by Id");
		rw.setData(found_product);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createProduct(@RequestBody @Valid Product product) {
		Product created_product = productService.createProduct(product);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("New Product Added successfully.");
		rw.setData(created_product);
		return new ResponseEntity<>(rw, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProductByID(@PathVariable int id, @RequestBody Product product) {
		Product updated_product = productService.updateProduct(id, product);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Product updated by Id successfully.");
		rw.setData(updated_product);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delteProductByID(@PathVariable int id) {
		productService.deleteProduct(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Product deleted by Id successfully.");
		rw.setData(null);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}
	
	// Mapping  methods
  
  // One to One
  @PutMapping("/{product_id}/manufactures/{m_id}")
    public ResponseEntity<?> assignManufactureToProduct(@PathVariable int product_id, @PathVariable int m_id)
     {
         Product add_manufacture= productService.ManufactureToProduct(product_id, m_id);
         ResponseWrapper srw= new ResponseWrapper();
         srw.setMessage("Manufacture add to Product");
         srw.setData(add_manufacture);
         return new ResponseEntity<>( srw,HttpStatus.OK);
    }
	
  	//Many to Many 
	@PutMapping("/{product_id}/suppliers/{supplier_id}")
   public ResponseEntity<?> addSupplierToProduct(@PathVariable int product_id, @PathVariable int supplier_id) 
   {
	     productService.addSupplierToProduct(product_id, supplier_id);
        ResponseWrapper srw= new ResponseWrapper();
		 srw.setMessage("Suppliers added to Product");
        return new ResponseEntity<>(srw,HttpStatus.OK);
   }
}
