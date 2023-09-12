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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int brand_id;

	@Column(nullable = false)
	private String brand_name;

	@Column(nullable = false)
	private String brand_code;

	@Column(nullable = false)
	private int establish_year;

	@CreatedDate
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Brand() {
	}

	public Brand(int brand_id, String brand_name, String brand_code, int establish_year, Instant createdAt,
			Instant updatedAt, Product product) {
		super();
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.brand_code = brand_code;
		this.establish_year = establish_year;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.product = product;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrand_code() {
		return brand_code;
	}

	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}

	public int getEstablish_year() {
		return establish_year;
	}

	public void setEstablish_year(int establish_year) {
		this.establish_year = establish_year;
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
