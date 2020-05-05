package com.imlewis.referral.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;

@Controller
@RequestMapping("/admin/urf")
public class ReferralMarketingUserReferralConfigController {

	@RequestMapping("/m")
	public String userReferralManagement(Model model){
		ReferralMarketingUserReferralConfigItem userReferralConfigItem = new ReferralMarketingUserReferralConfigItem();
    	model.addAttribute("userReferral",userReferralConfigItem);
		return "admin/referralMarketingUserReferralConfig";
	}
	
}
