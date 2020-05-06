package com.imlewis.referral.controller.admin;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imlewis.referral.model.ReferralMarketingUserReferralConfigItem;
import com.imlewis.referral.repository.ReferralMarketingUserReferralConfigRepository;

@Controller
@RequestMapping("/admin/urf")
public class ReferralMarketingUserReferralConfigController {

	@Autowired
    private ReferralMarketingUserReferralConfigRepository referralMarketingUserReferralConfigRepository;
	
	@RequestMapping("/m")
	public String userReferralManagement(Model model){
		ReferralMarketingUserReferralConfigItem userReferralConfigItem = new ReferralMarketingUserReferralConfigItem();
    	model.addAttribute("userReferral",userReferralConfigItem);
		return "admin/referralMarketingUserReferralConfig";
	}
	
	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public String addUserReferralConfig(@Valid @ModelAttribute("userReferral") ReferralMarketingUserReferralConfigItem referralMarketingUserReferralConfigItem, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "admin/referralMarketingUserReferralConfig";
		}
		referralMarketingUserReferralConfigRepository.save(referralMarketingUserReferralConfigItem);
		model.addAttribute("userReferral", new ReferralMarketingUserReferralConfigItem());
		model.addAttribute("msg", "Success! User Referral Configuration details have been added");
		return "admin/referralMarketingUserReferralConfig";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
