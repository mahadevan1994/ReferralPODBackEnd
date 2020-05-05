package com.imlewis.referral.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class ReferralMarketingGenericReferralConfigItem implements Serializable{

    private static final long serialVersionUID = -1345626380557589113L;

    @Id
    @GeneratedValue
    private Long genericReferralId;

    @NotEmpty(message = "Please fill in minimumOrderCount")
    private int minimumOrderCount;
    private int totalBusiness;

    @Transient
    private MultipartFile sliderImage;

    public Long getGenericReferralId() {
        return genericReferralId;
    }

    public void setGenericReferralId(Long genericReferralId) {
        this.genericReferralId = genericReferralId;
    }

    public int getMinimumOrderCount() {
        return minimumOrderCount;
    }

    public void setMinimumOrderCount(int minimumOrderCount) {
        this.minimumOrderCount = minimumOrderCount;
    }


    public int getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(int totalBusiness) {
        this.totalBusiness = totalBusiness;
    }
    
}

