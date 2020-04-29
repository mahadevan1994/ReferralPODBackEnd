package com.imlewis.model;

import java.io.Serializable;

public class RecentlyPurchasedForFashion implements Serializable{

   private static final long serialVersionUID = -6050058936968302003L;

    private Long id;

    private String ProductName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}
}
