package com.imlewis.referral.controller.admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;
import com.imlewis.service.ProductService;

@Controller
@RequestMapping("/admin/irf")
public class ReferralMarketingIndividualReferralConfigController {

	Logger logger = LoggerFactory.getLogger(ReferralMarketingGenericReferralConfigController.class);

	@Autowired
	private ReferralMarketingGenericReferralAddConfigService referralMarketingGenericReferralAddConfigService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/m")
	public String individualReferralManagement(Model model) {
		ReferralMarketingGenericReferralAddConfigItem addConfigItem = new ReferralMarketingGenericReferralAddConfigItem();
		model.addAttribute("addConfiguration", addConfigItem);
		model.addAttribute("savedConfiguration",
				referralMarketingGenericReferralAddConfigService.getAllGenericReferralConfigItems());
		model.addAttribute("giftItems", productService.getAllByProductGifts());
		return "admin/referralMarketingIndividualReferralConfig";
	}

	@RequestMapping(value = "/s", method = RequestMethod.POST)
	public String addConfiguration(
			@ModelAttribute("addConfiguration") ReferralMarketingGenericReferralAddConfigItem addConfigItem,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/referralMarketingIndividualReferralConfig";
		}
		BufferedWriter writer = null;
		referralMarketingGenericReferralAddConfigService.save(addConfigItem);

		String str = addConfigItem.getReferralMessage();
		str = str.replace("<", "${");
		str = str.replace(">", "}");
		str = str.replaceFirst("Hello", "<!DOCTYPE html><html><head></head><body><p>Hello");
		str = str.replace(",", ",</p>");
		str = str.concat("<p>Please register using the link ${websiteAddr}</p>");
		str = str.concat("<p>Regards,<br/>Nancy's Business Team</p></body></html>");
		try {
			switch (addConfigItem.getBenefitType()) {
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
			if (null != writer) {
				writer.write(str);
				writer.close();
			}
		} catch (IOException e) {
			logger.error("Error occured while writing " + addConfigItem.getBenefitType() + " VM file", e);
		}

		model.addAttribute("addConfiguration", addConfigItem);
		model.addAttribute("savedConfiguration",
				referralMarketingGenericReferralAddConfigService.getAllGenericReferralConfigItems());
		model.addAttribute("giftItems", productService.getAllByProductGifts());
		return "admin/referralMarketingIndividualReferralConfig";
	}
}
