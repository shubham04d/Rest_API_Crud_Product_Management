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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int product_id;

	@Column(nullable = false)
	@Size(min = 3, max = 20, message = " Product_name must be 3 to 20 charcaters ")
	private String product_name;

	@Column(nullable = false)
	@Min(value = 1000, message = " Minimum price is 1000 ")
	@Max(value = 1000000, message = " Maximum price is 1000000")
	private int price;

	@Column(nullable = false)
	@Size(min = 1, max = 20, message = "  Category_name must be 1 to 20 charcaters ")
	private String category;

	@Column(nullable = false)
	@Size(min = 1, max = 20, message = "   Company_name must be 1 to 20 charcaters ")
	private String company_name;

	@CreatedDate
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

	@OneToOne
	@JoinColumn(name = "m_id")
	private Manufacture manufacture;

	@OneToMany(mappedBy = "product")
	private List<Brand> brand;

	@ManyToMany
	@JoinTable(name = "product_supplier", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id"))
	private List<Supplier> supplier;

	public Product() {
	}

	public Product(int product_id, String product_name, int price, String category, String company_name,
			Instant createdAt, Instant updatedAt, Manufacture manufacture, List<Brand> brand, List<Supplier> supplier) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.category = category;
		this.company_name = company_name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.manufacture = manufacture;
		this.brand = brand;
		this.supplier = supplier;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public Manufacture getManufacture() {
		return manufacture;
	}

	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}

	public List<Brand> getBrand() {
		return brand;
	}

	public void setBrand(List<Brand> brand) {
		this.brand = brand;
	}

	public List<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Supplier> supplier) {
		this.supplier = supplier;
	}

}
