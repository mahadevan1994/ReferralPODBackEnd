package com.imlewis.referral.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralConfigRepository;

@Controller
@RequestMapping("/admin/grf")
public class ReferralMarketingGenericReferralConfigController {
	
	@Autowired
    private ReferralMarketingGenericReferralConfigRepository referralMarketingGenericReferralConfigRepository;

	@RequestMapping("/m")
	public String genericReferralManagement(Model model){

		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",genericReferralConfigItem);
    	
		return "admin/referralMarketingGenericReferralConfig";
		
	}
	
	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public String addGenericReferralConfig(@Valid @ModelAttribute("genericReferral") ReferralMarketingGenericReferralConfigItem referralMarketingGenericReferralConfigItem, BindingResult result,Model model){
		if (result.hasErrors()) {
			return "admin/referralMarketingGenericReferralConfig";
		}
		referralMarketingGenericReferralConfigRepository.save(referralMarketingGenericReferralConfigItem);
		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",genericReferralConfigItem);
		return "admin/referralMarketingGenericReferralConfig";
		
	}
	
}

