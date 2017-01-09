package br.com.zup.xy_inc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCT")
@SequenceGenerator(name="SQ_PRODUCT", sequenceName="SQ_PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "SQ_PRODUCT")
	@Column(name = "P_ID")
	private Long id;
	
	@NotNull
	@Column(name = "P_NAME", length=100)
	private String name;
	
	@NotNull
	@Column(name = "P_DESCRIPTION", length = 4000)
	private String description;
	
	@NotNull
	@Column(name = "P_PRICE", length=8, scale=2)
	private BigDecimal price;
	
	@NotNull
	@Column(name = "P_CATEGORY", length=100)
	private String category;
	
	public Product() {

	}

	public Product(Long id, String name, String description, BigDecimal price, String category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
