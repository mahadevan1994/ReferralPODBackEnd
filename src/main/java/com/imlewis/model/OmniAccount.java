package com.imlewis.model;


import java.io.Serializable;

public class OmniAccount implements Serializable{

    private static final long serialVersionUID = 4110828160339396501L;

    private String omniAccountId;

    private double omniAccountTotal;
    
    private String emailId;
    
	public String getOmniAccountId() {
		return omniAccountId;
	}

	public void setOmniAccountId(String omniAccountId) {
		this.omniAccountId = omniAccountId;
	}

	public double getOmniAccountTotal() {
		return omniAccountTotal;
	}

	public void setOmniAccountTotal(double omniAccountTotal) {
		this.omniAccountTotal = omniAccountTotal;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
