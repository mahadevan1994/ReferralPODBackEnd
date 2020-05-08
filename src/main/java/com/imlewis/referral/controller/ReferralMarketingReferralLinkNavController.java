package com.imlewis.referral.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.model.ReferredUser;
import com.imlewis.referral.service.ReferralMarketingUserCommunicationConfigService;
import com.imlewis.referral.service.ReferralMarketingUserReferralConfigService;
import com.imlewis.service.EmailSenderService;

@Controller
public class ReferralMarketingReferralLinkNavController{
	
	Logger logger = LoggerFactory.getLogger(ReferralMarketingReferralLinkNavController.class);
	
	@Autowired
	private ReferralMarketingUserCommunicationConfigService communicationConfigService;
	@Autowired
	ReferralMarketingUserReferralConfigService userReferralConfigService;
	@Autowired
	EmailSenderService emailSenderService;
	@Autowired
	ReferralMarketingUserCommunicationConfigService userCommunicationConfigService;
	
	@RequestMapping(value = "/ambassador/{communicationId}", method = RequestMethod.GET)
	public String benefitAmbassador(@PathVariable("communicationId") long communicationId, Model model) {
		
		int isCommunicationIdPresent = communicationConfigService.isCommunicationIdPresent(communicationId);
		
		if(isCommunicationIdPresent == 0) {
			model.addAttribute("error","Invalid referral link");
			return "home";
		}
		/*
		 * Validate the referral link by using commIdGenerationDate,referralLinkExpiryInDays and current date
		 * commIdGenerationDate + referralLinkExpiryInDays should not exceed current date
		Date commIdGenerationDate = communicationConfigService.getGenerationDate(communicationId);
		int referralLinkExpiryInDays = userReferralConfigService.getReferralLinkExpiryInDays();
		if(condition) {
			model.addAttribute("error","The referral link has been expired");
		}*/
		ReferredUser referredUser = new ReferredUser();
		model.addAttribute("communicationId",communicationId);
		referredUser.setCommunicationId(communicationId);
		model.addAttribute("user",referredUser);
		
        return "referral/ambassadorReferral";
	}
	
	@RequestMapping(value = "/referred/{communicationId}", method = RequestMethod.GET)
	public String benefitReferred(@PathVariable("communicationId") long communicationId, Model model) {
		
		int isCommunicationIdPresent = communicationConfigService.isCommunicationIdPresent(communicationId);
		
		if(isCommunicationIdPresent == 0) {
			model.addAttribute("error","Invalid referral link");
		}
		/*
		 * Validate the referral link by using commIdGenerationDate,referralBenefitExpiryInDays and current date
		 * commIdGenerationDate + referralBenefitExpiryInDays should not exceed current date
		Date commIdGenerationDate = communicationConfigService.getGenerationDate(communicationId);
		int referralBenefitExpiryInDays = userReferralConfigService.getReferralBenefitExpiryInDays();
		if(condition) {
			model.addAttribute("error","The referral link has been expired");
		}*/
        return "home";
	}
	
	@RequestMapping(value = "/sendReferral", method = RequestMethod.POST)
	public String sendReferral(@Valid @ModelAttribute("user") ReferredUser referredUser, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("error","Error Occurred");
			return "referral/ambassadorReferral";
		}
		ReferralMarketingUserCommunicationConfig userCommunicationConfigItem;
		try {
			userCommunicationConfigItem = userCommunicationConfigService.getReferralMarketingUserCommunicationConfig(referredUser.getCommunicationId());
			emailSenderService.sendEmail(userCommunicationConfigItem, referredUser.getEmail());
		} catch (Exception e) {
			model.addAttribute("error","Unable to send mail");
			logger.error("Error while sending mail", e);
			return "referral/ambassadorReferral";
		}
		model.addAttribute("success","Mail sent successfully");
        return "referral/ambassadorReferral";
	}
}
