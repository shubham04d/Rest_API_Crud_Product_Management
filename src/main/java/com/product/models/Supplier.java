package com.product.models;

import java.time.Instant;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int supplier_id;
	
	@Column(nullable = false)
	private String supplier_name;
	
	@Column(nullable = false)
	private String supplier_branch;
	
	@CreatedDate
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;
	
	@ManyToMany(mappedBy = "supplier")
	private List<Product> product;

	public Supplier() {	}

	public Supplier(int supplier_id, String supplier_name, String supplier_branch, Instant createdAt,
			Instant updatedAt, List<Product> product) {
		super();
		this.supplier_id = supplier_id;
		this.supplier_name = supplier_name;
		this.supplier_branch = supplier_branch;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.product = product;

	}


	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_branch() {
		return supplier_branch;
	}

	public void setSupplier_branch(String supplier_branch) {
		this.supplier_branch = supplier_branch;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
