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
import com.product.models.Supplier;
import com.product.services.SupplierService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@GetMapping("")
	public ResponseEntity<?> getAllSuppliers() {
		Iterable<Supplier> supplier = supplierService.getAllsuppliers();
		Iterator<Supplier> all_supplier = supplier.iterator();
		if (all_supplier.hasNext()) {
			ResponseWrapper rw = new ResponseWrapper();
			rw.setMessage("Following Supplier found");
			rw.setData(all_supplier);
			return new ResponseEntity<>(rw, HttpStatus.FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no supplier, please add suppliers ");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getSupplierByID(@PathVariable int id) {
		Supplier found_supplier = supplierService.getSupplierByID(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Supplier found by Id");
		rw.setData(found_supplier);
		return new ResponseEntity<>(rw, HttpStatus.FOUND);
	}
	
	@PostMapping("")
	public ResponseEntity<?> createSupplier(@RequestBody @Valid Supplier supplier) {
		Supplier created_supplier = supplierService.createSupplier(supplier);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("New Supplier Added successfully.");
		rw.setData(created_supplier);
		return new ResponseEntity<>(rw, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplierByID(@PathVariable int id, @RequestBody Supplier supplier) {
		Supplier updated_supplier = supplierService.updateSupplier(id, supplier);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Supplier updated by Id successfully.");
		rw.setData(updated_supplier);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delteSupplierByID(@PathVariable int id) {
		supplierService.deleteSupplier(id);
		ResponseWrapper rw = new ResponseWrapper();
		rw.setMessage("Following Supplier deleted by Id successfully.");
		rw.setData(null);
		return new ResponseEntity<>(rw, HttpStatus.OK);
	}
}
