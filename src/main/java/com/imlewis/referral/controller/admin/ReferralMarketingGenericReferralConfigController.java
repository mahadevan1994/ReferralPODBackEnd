package com.imlewis.referral.controller.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.model.ReferralMarketingGenericReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralAddConfigRepository;
import com.imlewis.referral.repository.ReferralMarketingGenericReferralConfigRepository;

@Controller
@RequestMapping("/admin/grf")
public class ReferralMarketingGenericReferralConfigController {
	
	Logger logger = LoggerFactory.getLogger(ReferralMarketingGenericReferralConfigController.class);
	
	@Autowired
    private ReferralMarketingGenericReferralConfigRepository referralMarketingGenericReferralConfigRepository;
	
	@Autowired
    private ReferralMarketingGenericReferralAddConfigRepository referralMarketingGenericReferralAddConfigRepository;

	@RequestMapping("/m")
	public String genericReferralManagement(Model model){

		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
		ReferralMarketingGenericReferralAddConfigItem addConfigItem = new ReferralMarketingGenericReferralAddConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",addConfigItem);
    	
		return "admin/referralMarketingGenericReferralConfig";
		
	}
	
	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public String addGenericReferralConfig(@Valid @ModelAttribute("genericReferral") ReferralMarketingGenericReferralConfigItem referralMarketingGenericReferralConfigItem, BindingResult result,Model model){
		if (result.hasErrors()) {
			return "admin/referralMarketingGenericReferralConfig";
		}
		referralMarketingGenericReferralConfigRepository.save(referralMarketingGenericReferralConfigItem);
		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
		ReferralMarketingGenericReferralAddConfigItem addConfigItem = new ReferralMarketingGenericReferralAddConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",addConfigItem);
		model.addAttribute("msg", "Success! Generic Referral Configuration details have been added");
		return "admin/referralMarketingGenericReferralConfig";
		
	}
	
	@RequestMapping(value = "/a", method = RequestMethod.POST)
	public String addConfiguration(@ModelAttribute("addConfiguration") ReferralMarketingGenericReferralAddConfigItem addConfigItem, BindingResult result,Model model){
		if (result.hasErrors()) {
			return "admin/referralMarketingGenericReferralConfig";
		}
		BufferedWriter writer = null;
		referralMarketingGenericReferralAddConfigRepository.save(addConfigItem);
		
		String str = addConfigItem.getReferralMessage();
		str = str.replace("<", "${");
		str = str.replace(">", "}");
		str = str.replaceFirst("Hello", "<!DOCTYPE html><html><head></head><body><p>Hello");
		str = str.replace(",", ",</p>");
		str = str.concat("<p>Regards,<br/>Nancy's Business Team</p></body></html>");
		try {
			switch(addConfigItem.getBenefitType()) {
				case "loyalty": 
					writer = new BufferedWriter(new FileWriter("src/main/resources/loyaltyPointsReferralMessage.vm"));
					break;
				case "voucher": 
					writer = new BufferedWriter(new FileWriter("src/main/resources/voucherReferralMessage.vm"));
					break;
				case "discount": 
					writer = new BufferedWriter(new FileWriter("src/main/resources/discountReferralMessage.vm"));
					break;
				case "giftItem": 
					writer = new BufferedWriter(new FileWriter("src/main/resources/giftItemReferralMessage.vm"));
					break;
			}
			if(null != writer) {
				writer.write(str);
				writer.close();
			}
		} catch (IOException e) {
			logger.error("Error occured while writing " + addConfigItem.getBenefitType() + " VM file", e);
		}
		
		ReferralMarketingGenericReferralConfigItem genericReferralConfigItem = new ReferralMarketingGenericReferralConfigItem();
    	model.addAttribute("genericReferral",genericReferralConfigItem);
    	model.addAttribute("addConfiguration",addConfigItem);
    	
		return "admin/referralMarketingGenericReferralConfig";
		
	}
	
}

