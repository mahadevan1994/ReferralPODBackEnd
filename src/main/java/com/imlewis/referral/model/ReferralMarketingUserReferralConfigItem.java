package com.imlewis.referral.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ReferralMarketingUserReferralConfigItem implements Serializable{

    private static final long serialVersionUID = -1345626380557589113L;

    @Id
    @GeneratedValue
    private Long userReferralId;

    private boolean referralEnablement;
    private int referralFrequency;
    private Date referralEnablementDate;
    private int referralinkExpiry;
    private int referralBenifitExpiry;

    @Transient
    private MultipartFile sliderImage;

    public Long getUserReferralId() {
        return userReferralId;
    }

    public void setUserReferralId(Long userReferralId) {
        this.userReferralId = userReferralId;
    }

    public boolean getReferralEnablement() {
        return referralEnablement;
    }

    public void setRreferralEnablement(boolean referralEnablement) {
        this.referralEnablement = referralEnablement;
    }

    public int getReferralFrequency() {
        return referralFrequency;
    }

    public void setReferralFrequency(int referralFrequency) {
        this.referralFrequency = referralFrequency;
    }

    public Date getReferralEnablementDate() {
        return referralEnablementDate;
    }

    public void setReferralEnablementDate(Date referralEnablementDate) {
        this.referralEnablementDate = referralEnablementDate;
    }

    public int getReferralinkExpiry() {
        return referralinkExpiry;
    }

    public void setReferralinkExpiry(int referralinkExpiry) {
        this.referralinkExpiry = referralinkExpiry;
    }
    
    public int getReferralBenifitExpiry() {
        return referralBenifitExpiry;
    }

    public void setReferralBenifitExpiry(int referralBenifitExpiry) {
        this.referralBenifitExpiry = referralBenifitExpiry;
    }
}