package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.test.context.web.WebAppConfiguration;

@Entity
@Table(name="Product")
@Component
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int product_id;
	@Column
	public String name;
	@Column
	public String description;
	@Column
	public double price;
	@Column
	public int quantity;
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
