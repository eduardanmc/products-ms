package br.com.vivo.fasttrack.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdutoDto {
	
	private Long id;
	@NotNull
	@NotEmpty(message = "O campo nome não pode ser vazio")
	private String name;
	@NotNull
	@NotEmpty(message = "O campo descrição não pode ser vazio")
	private String description;
	@NotNull
	@DecimalMin(value = "0.01", message = "O valor não pode ser menor que 0")
	private BigDecimal price;
	
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
	
}
