package com.imlewis.referral.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class ReferredUser implements Serializable{
	
	private static final long serialVersionUID = -6922461044823943143L;

    @NotEmpty(message = "Please enter email")
	private String email;
    private Long communicationId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCommunicationId() {
		return communicationId;
	}

	public void setCommunicationId(Long communicationId) {
		this.communicationId = communicationId;
	}
  
}
