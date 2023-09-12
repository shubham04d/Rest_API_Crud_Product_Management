package com.product.models;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Manufacture {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int m_id;
	
	@Column(nullable = false)
	private String manufacturer_name;
	
	@Column(nullable = false)
	private String manufacture_batch;
	
	@Column(nullable = false)
	private String manufacture_date;
	
	@CreatedDate   
	private Instant createdAt;
	
	@LastModifiedDate
	private Instant updatedAt;
	
	@OneToOne(mappedBy = "manufacture")
	private Product product;

	public Manufacture() {	}

	public Manufacture(int m_id, String manufacturer_name, String manufacture_batch, String manufacture_date,
			Instant createdAt, Instant updatedAt, Product product) {
		super();
		this.m_id = m_id;
		this.manufacturer_name = manufacturer_name;
		this.manufacture_batch = manufacture_batch;
		this.manufacture_date = manufacture_date;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.product = product;

	}


	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public String getManufacture_batch() {
		return manufacture_batch;
	}

	public void setManufacture_batch(String manufacture_batch) {
		this.manufacture_batch = manufacture_batch;
	}

	public String getManufacture_date() {
		return manufacture_date;
	}

	public void setManufacture_date(String manufacture_date) {
		this.manufacture_date = manufacture_date;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
