package com.imlewis.referral.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;

@Controller
@RequestMapping("/admin/grf")
public class ReferralMarketingGenericReferralConfigController {

	@RequestMapping("/m")
	public String genericReferralManagement(Model model){

		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",genericReferralConfigItem);
    	
		return "admin/referralMarketingGenericReferralConfig";
		
	}
}

