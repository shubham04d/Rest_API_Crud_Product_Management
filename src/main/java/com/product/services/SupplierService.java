package com.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.product.models.Supplier;
import com.product.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	public Iterable<Supplier> getAllsuppliers() {
		return supplierRepository.findAll();
	}

	public Supplier getSupplierByID(int id) {
		Supplier found_supplier = supplierRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no supplier , with this id ");
		});
		return found_supplier;
	}

	public Supplier createSupplier(Supplier supplier) {
		Supplier inserted_supplier = supplierRepository.save(supplier);
		supplier.setCreatedAt(inserted_supplier.getCreatedAt());
		return inserted_supplier;
	}

	public Supplier updateSupplier(int id, Supplier supplier) {
		Supplier found_supplier = getSupplierByID(id);
		supplier.setSupplier_id(found_supplier.getSupplier_id());
		supplier.setCreatedAt(found_supplier.getCreatedAt());
		supplier.setUpdatedAt(found_supplier.getUpdatedAt());
		Supplier update_supplier = supplierRepository.save(supplier);
		return update_supplier;
	}

	public void deleteSupplier(int id) {
		getSupplierByID(id);
		supplierRepository.deleteById(id);
	}
}
