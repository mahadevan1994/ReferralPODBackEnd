package com.imlewis.referral.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imlewis.model.Product;
import com.imlewis.model.Slider;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.model.ReferredUser;
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;
import com.imlewis.referral.service.ReferralMarketingUserCommunicationConfigService;
import com.imlewis.referral.service.ReferralMarketingUserReferralConfigService;
import com.imlewis.repository.ProductRepository;
import com.imlewis.repository.SliderRepository;
import com.imlewis.service.CategoryService;
import com.imlewis.service.EmailSenderService;

@Controller
public class ReferralMarketingReferralLinkNavController{
	
	Logger logger = LoggerFactory.getLogger(ReferralMarketingReferralLinkNavController.class);
	
	@Autowired
	private SliderRepository sliderRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ReferralMarketingUserCommunicationConfigService communicationConfigService;
	@Autowired
	ReferralMarketingUserReferralConfigService userReferralConfigService;
	@Autowired
	EmailSenderService emailSenderService;
	@Autowired
	ReferralMarketingUserCommunicationConfigService userCommunicationConfigService;
	@Autowired
	ReferralMarketingGenericReferralAddConfigService referralAddConfigService;
	
	
	@RequestMapping(value = "/ambassador/{communicationId}", method = RequestMethod.GET)
	public String benefitAmbassador(@PathVariable("communicationId") long communicationId, Model model) {
		
		int isCommunicationIdPresent = communicationConfigService.isCommunicationIdPresent(communicationId);
		
		if(isCommunicationIdPresent == 0) {
			model.addAttribute("referralLinkError","Invalid referral link");
			List<Slider> sliderList = (List<Slider>) sliderRepository.findAll();
	        List<Product> productList = productRepository.findAll(new PageRequest(0, 16)).getContent();
	        List<String> mainCategoryNameList = categoryService.getAllMainCategory();
	        mainCategoryNameList.remove("Gift");
	        List<Product> productPopularList = productRepository.findAll
	        		(new PageRequest(0, 8, Direction.DESC, "productViews")).getContent();

	        model.addAttribute("productPopular", productPopularList);
	        model.addAttribute("sliders", sliderList);
	        model.addAttribute("products", productList);
	        model.addAttribute("mainCategoryNameList", mainCategoryNameList);
			return "referral/referralLikLandingPage";
		}
		/*
		 * Validate the referral link by using commIdGenerationDate,referralLinkExpiryInDays and current date
		 * commIdGenerationDate + referralLinkExpiryInDays should not exceed current date
		Date commIdGenerationDate = communicationConfigService.getGenerationDate(communicationId);
		int referralLinkExpiryInDays = userReferralConfigService.getReferralLinkExpiryInDays();
		if(condition) {
			model.addAttribute("referralLinkError","The referral link has been expired");
		}*/
		ReferredUser referredUser = new ReferredUser();
		model.addAttribute("communicationId",communicationId);
		referredUser.setCommunicationId(communicationId);
		model.addAttribute("user",referredUser);
		
        return "referral/ambassadorReferral";
	}
	
	@RequestMapping(value = "/referred/{communicationId}", method = RequestMethod.GET)
	public String benefitReferred(@PathVariable("communicationId") long communicationId, Model model,HttpServletRequest request) {
		
		int isCommunicationIdPresent = communicationConfigService.isCommunicationIdPresent(communicationId);
		
		List<Slider> sliderList = (List<Slider>) sliderRepository.findAll();
        List<Product> productList = productRepository.findAll(new PageRequest(0, 16)).getContent();
        List<String> mainCategoryNameList = categoryService.getAllMainCategory();
        mainCategoryNameList.remove("Gift");
        List<Product> productPopularList = productRepository.findAll
        		(new PageRequest(0, 8, Direction.DESC, "productViews")).getContent();

        model.addAttribute("productPopular", productPopularList);
        model.addAttribute("sliders", sliderList);
        model.addAttribute("products", productList);
        model.addAttribute("mainCategoryNameList", mainCategoryNameList);
		
		if(isCommunicationIdPresent == 0) {
			model.addAttribute("referralLinkError","Invalid referral link");
			return "referral/referralLikLandingPage";
		}
		/*
		 * Validate the referral link by using commIdGenerationDate,referralBenefitExpiryInDays and current date
		 * commIdGenerationDate + referralBenefitExpiryInDays should not exceed current date
		Date commIdGenerationDate = communicationConfigService.getGenerationDate(communicationId);
		int referralBenefitExpiryInDays = userReferralConfigService.getReferralBenefitExpiryInDays();
		if(condition) {
			model.addAttribute("referralLinkError","The referral link has been expired");
		}*/
		request.getSession().setAttribute("communicationId_", communicationId);
		
        model.addAttribute("referralLinkSuccess","Please get registered to get the referral benefit");
        return "referral/referralLikLandingPage";
	}
	
	@RequestMapping(value = "/sendReferral", method = RequestMethod.POST)
	public String sendReferral(@Valid @ModelAttribute("user") ReferredUser referredUser, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("ambassadorReferralLinkError","Error Occurred");
			return "referral/ambassadorReferral";
		}
		ReferralMarketingUserCommunicationConfig userCommunicationConfigItem;
		long communicationId = referredUser.getCommunicationId();
		try {
			userCommunicationConfigItem = userCommunicationConfigService.getReferralMarketingUserCommunicationConfig(communicationId);
			emailSenderService.sendEmail(userCommunicationConfigItem, referredUser.getEmail());
		} catch (Exception e) {
			model.addAttribute("ambassadorReferralLinkError","Unable to send mail");
			logger.error("Error while sending mail", e);
			return "referral/ambassadorReferral";
		}
		model.addAttribute("ambassadorReferralLinkSuccess","Mail sent successfully");
        return "referral/ambassadorReferral";
	}
}
