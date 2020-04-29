package com.imlewis.model;

import java.io.Serializable;

public class RichRelevanceMockForFashion implements Serializable{

   private static final long serialVersionUID = -6989243970039135205L;

    private String omniAccountId;

    private String recentlyAddedList;
    
    private String recommendedListBasedOnRecentlyAdded;

    private String recentlyPurchasedList;
    
    private String recommendedListBasedOnRecentlyPurchased;

	public String getOmniAccountId() {
		return omniAccountId;
	}

	public void setOmniAccountId(String omniAccountId) {
		this.omniAccountId = omniAccountId;
	}

	public String getRecentlyAddedList() {
		return recentlyAddedList;
	}

	public void setRecentlyAddedList(String recentlyAddedList) {
		this.recentlyAddedList = recentlyAddedList;
	}

	public String getRecentlyPurchasedList() {
		return recentlyPurchasedList;
	}

	public void setRecentlyPurchasedList(String recentlyPurchasedList) {
		this.recentlyPurchasedList = recentlyPurchasedList;
	}

	public String getRecommendedListBasedOnRecentlyAdded() {
		return recommendedListBasedOnRecentlyAdded;
	}

	public void setRecommendedListBasedOnRecentlyAdded(String recommendedListBasedOnRecentlyAdded) {
		this.recommendedListBasedOnRecentlyAdded = recommendedListBasedOnRecentlyAdded;
	}

	public String getRecommendedListBasedOnRecentlyPurchased() {
		return recommendedListBasedOnRecentlyPurchased;
	}

	public void setRecommendedListBasedOnRecentlyPurchased(String recommendedListBasedOnRecentlyPurchased) {
		this.recommendedListBasedOnRecentlyPurchased = recommendedListBasedOnRecentlyPurchased;
	}
}
