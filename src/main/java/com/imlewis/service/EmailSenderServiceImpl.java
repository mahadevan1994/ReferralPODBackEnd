package com.imlewis.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{

	Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
	 
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private ReferralMarketingGenericReferralAddConfigService addConfigService;
	
	@Value("${fromAddress}")
	private String fromAddress;
	@Value("${spring.mail.host}")
	private String smtpHost;
	@Value("${spring.mail.port}")
	private int smtpPort;
	@Value("${spring.mail.username}")
	private String mailUserName;
	@Value("${spring.mail.password}")
	private String mailPassword;
	@Value("${mailSubjectReferrer}")
	private String mailSubjectReferrer;
	@Value("${mailSubjectReferred}")
	private String mailSubjectReferred;
	@Value("${ambassadorWebsiteAddr}")
	private String ambassadorWebsiteAddr;
	@Value("${referredWebsiteAddr}")
	private String referredWebsiteAddr;
	
	public void sendMail(String toAddress, String subject, String msgBody){
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);
		mailSender.send(msg);
	}
	
	public void sendActiveCode(Customer customer){
		//sendMail(customer.getEmail(), "Active Your Account", msgBody);
	}
	
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		mailSender.setUsername(mailUserName);
		mailSender.setPassword(mailPassword);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@SuppressWarnings("deprecation")
	public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
		VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		velocityEngineFactory.setVelocityProperties(props);
		return velocityEngineFactory.createVelocityEngine();
	}

	public void sendEmail(Customer customer, int communicationId) {
		MimeMessage mimeMessage = getMailSender().createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(fromAddress);
			mimeMessageHelper.setSubject(mailSubjectReferrer);
			mimeMessageHelper.setTo(customer.getEmail());
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("customerName", customer.getCustomerName());
			model.put("websiteAddr", ambassadorWebsiteAddr + Integer.toString(communicationId));
			mimeMessageHelper.setText(geContentFromTemplate(model,"ambassador"), true);
			getMailSender().send(mimeMessageHelper.getMimeMessage());
		} catch (Exception e) {
			logger.error("Sending email failed!", e);
		}
	}
	
	public void sendEmail(ReferralMarketingUserCommunicationConfig userCommunicationConfigItem, String referredEmail) {
		MimeMessage mimeMessage = getMailSender().createMimeMessage();
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(fromAddress);
			mimeMessageHelper.setSubject(mailSubjectReferred);
			mimeMessageHelper.setTo(referredEmail);
			ReferralMarketingGenericReferralAddConfigItem addConfigItem = addConfigService.getAddConfigItem(userCommunicationConfigItem.getReferralConfigurationId());
			String benefitType = addConfigItem.getBenefitType();
			model.put("websiteAddr", referredWebsiteAddr + Integer.toString(userCommunicationConfigItem.getCommunicationId()));
			switch(benefitType) {
				case "loyalty": 
					model.put("points", addConfigItem.getReferralAmount());
					break;
				case "voucher": 
					model.put("amount", addConfigItem.getReferralAmount());
					break;
				case "discount": 
					model.put("discount", addConfigItem.getReferralAmount());
					break;
				case "giftItem": 
					model.put("itemName", addConfigItem.getReferralAmount());
					break;
			}
			mimeMessageHelper.setText(geContentFromTemplate(model,benefitType), true);
			getMailSender().send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			logger.error("Sending email failed!", e);
		}
	}

	public String geContentFromTemplate(Map<String, Object> model, String benefitType) {
		StringBuffer content = new StringBuffer();
		try {
			switch(benefitType) {
			case "loyalty": 
				content.append(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
						"/loyaltyPointsReferralMessage.vm", model));
				break;
			case "voucher": 
				content.append(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
						"/voucherReferralMessage.vm", model));
				break;
			case "discount": 
				content.append(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
						"/discountReferralMessage.vm", model));
				break;
			case "giftItem": 
				content.append(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
						"/giftItemReferralMessage.vm", model));
				break;
			case "ambassador": 
				content.append(VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(),
						"/sendReferralLinkToCustomer.vm", model));
				break;
			}	
		} catch (Exception e) {
			logger.error("Error occured while getting the content of the template", e);
		}
		return content.toString();
	}
}
