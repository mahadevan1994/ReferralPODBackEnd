package com.imlewis.referral.controller.admin;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;
import com.imlewis.referral.service.ReferralMarketingGenericReferralConfigService;

@Controller
@RequestMapping("/admin/grf")
public class ReferralMarketingGenericReferralConfigController {

	Logger logger = LoggerFactory.getLogger(ReferralMarketingGenericReferralConfigController.class);

	@Autowired
	private ReferralMarketingGenericReferralConfigService referralMarketingGenericReferralConfigService;

	@RequestMapping("/m")
	public String genericReferralManagement(Model model) {
		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();

		if (referralMarketingGenericReferralConfigService.findFirst() != null) {
			genericReferralConfigItem = referralMarketingGenericReferralConfigService.findFirst();
		}

		model.addAttribute("genericReferral", genericReferralConfigItem);
		return "admin/referralMarketingGenericReferralConfig";
	}

	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public String addGenericReferralConfig(
			@Valid @ModelAttribute("genericReferral") ReferralMarketingGenericReferralConfigItem referralMarketingGenericReferralConfigItem,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/referralMarketingGenericReferralConfig";
		}
		referralMarketingGenericReferralConfigService.save(referralMarketingGenericReferralConfigItem);
		model.addAttribute("genericReferral", referralMarketingGenericReferralConfigItem);
		model.addAttribute("msg", "Success! Generic Referral Configuration details have been added");
		return "admin/referralMarketingGenericReferralConfig";
	}
}
