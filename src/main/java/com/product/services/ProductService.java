package com.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.product.models.Manufacture;
import com.product.models.Product;
import com.product.models.Supplier;
import com.product.repositories.ManufactureRepository;
import com.product.repositories.ProductRepository;
import com.product.repositories.SupplierRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	SupplierRepository supplierRepository;
	ManufactureRepository manufactureRepository;
	Product product;

	public Iterable<Product> getAllproducts() {
		return productRepository.findAll();
	}

	public Product getProductByID(int id) {
		Product found_product = productRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no product , with this id ");
		});
		return found_product;
	}


	public Product createProduct(Product product) {
		Product inserted_product = productRepository.save(product);
		product.setCreatedAt(inserted_product.getCreatedAt());
		return inserted_product;
	}

	public Product updateProduct(int id, Product product) {
		Product found_product = getProductByID(id);
		product.setProduct_id(found_product.getProduct_id());
		product.setCreatedAt(found_product.getCreatedAt());
		product.setUpdatedAt(found_product.getUpdatedAt());
		Product update_product = productRepository.save(product);
		return update_product;
	}

	public void deleteProduct(int id) {
		getProductByID(id);
		productRepository.deleteById(id);
	}

	// Mapping Service
	
	 // One to One
    public Product ManufactureToProduct(int product_id, int m_id)
    {
    	Product product = getProductByID(product_id);
    	Manufacture manufacture = manufactureRepository.findById(m_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manufacture id does not exist"));
    	product.setManufacture(manufacture);
        return productRepository.save(product);
    }
    
	// Many to many
    public void addSupplierToProduct(int product_id, int supplier_id)
    {
        Product product = productRepository.findById(product_id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        Supplier supplier = supplierRepository.findById(supplier_id).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        product.getSupplier().add(supplier);
        productRepository.save(product);
    }
    
}
