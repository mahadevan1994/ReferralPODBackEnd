package com.imlewis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imlewis.model.Code;
import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingCustomerVoucherConfig;
import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.service.ReferralMarketingCustomerVoucherConfigService;
import com.imlewis.referral.service.ReferralMarketingDualIncentiveService;
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;
import com.imlewis.referral.service.ReferralMarketingUserCommunicationConfigService;
import com.imlewis.repository.CodeRepository;
import com.imlewis.service.CustomerService;
import com.imlewis.service.EmailSenderService;

/*
 *	Login/Logout/Register/Retrieve password 
 */
@Controller
public class UserController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private CodeRepository codeRepository;
	@Autowired
	ReferralMarketingUserCommunicationConfigService userCommunicationConfigService;
	@Autowired
	ReferralMarketingGenericReferralAddConfigService referralAddConfigService;
	@Autowired
	ReferralMarketingCustomerVoucherConfigService referralMarketingCustomerVoucherConfigService;
	@Autowired
	private ReferralMarketingDualIncentiveService referralMarketingDualIncentiveService;

	public void setToSession(HttpServletRequest request, Customer customer) {
		request.getSession().setAttribute("customerName_", customer.getCustomerName());
		request.getSession().setAttribute("customerId_", customer.getCustomerId());
		request.getSession().setAttribute("cartId_", customer.getCart().getCartId());
	}

	@RequestMapping(value = "/rp", method = RequestMethod.POST)
	public String resetPasswordPost(@Valid @ModelAttribute("user") Customer user, HttpServletRequest request,
			Model model) {

		Customer customer = customerService.findByEmail(user.getEmail());
		customer.setPassword(user.getPassword());

		Authentication authentication = new UsernamePasswordAuthenticationToken(customer.getEmail(),
				customer.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		setToSession(request, customer);

		customerService.save(customer);
		// delete reset password code in DB
		List<Code> codes = codeRepository.findByCodeTypeAndCustomer(1, customer);
		codeRepository.delete(codes);

		model.addAttribute("title", "Password Reset");
		model.addAttribute("msg", "Your password has been reset!");
		return "processSuccess";
	}

	@RequestMapping(value = "/rp/{codeStr}", method = RequestMethod.GET)
	public String resetPassword(@PathVariable String codeStr, Model model) {
		Code code = codeRepository.findByCodeStr(codeStr);
		if (code != null) {
			Customer customer = code.getCustomer();
			customer.setPassword("");
			model.addAttribute("user", customer);

			return "resetPW";
		}
		return "404";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomerPost(@Valid @ModelAttribute("user") Customer user, BindingResult result,
			HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		// if user already login, then redirect to home page.
		if (session.getAttribute("customerName_") != null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "registerCustomer";
		}
		if (customerService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("email_exists", "Email already exist!");
			return "registerCustomer";
		}
		user.setReferralId(0);
		if (!StringUtils.isEmpty(request.getSession().getAttribute("communicationId_"))) {
			long communicationId = (long) request.getSession().getAttribute("communicationId_");
			ReferralMarketingUserCommunicationConfig userCommunicationConfigItem = userCommunicationConfigService
					.getReferralMarketingUserCommunicationConfig(communicationId);
			if (null != userCommunicationConfigItem) {
				long referralConfigId = userCommunicationConfigItem.getReferralConfigurationId();
				ReferralMarketingGenericReferralAddConfigItem addConfigItem = referralAddConfigService
						.getAddConfigItem(referralConfigId);
				if (null != addConfigItem && "loyalty".equalsIgnoreCase(addConfigItem.getBenefitType())) {
					user.setLoyaltyPoints(addConfigItem.getReferralAmount());
					referralMarketingDualIncentiveService.checkAndApplyLoyalty(Long.valueOf(communicationId));
				} else if(null != addConfigItem && "voucher".equalsIgnoreCase(addConfigItem.getBenefitType())) {
					//send voucher email
					String voucherCode = RandomStringUtils.randomAlphanumeric(10);
					ReferralMarketingCustomerVoucherConfig referralMarketingCustomerVoucherConfig = new ReferralMarketingCustomerVoucherConfig();
					referralMarketingCustomerVoucherConfig.setVoucherCode(voucherCode);
					referralMarketingCustomerVoucherConfig.setRedeemStatus("NO");
					referralMarketingCustomerVoucherConfig.setReferralAmount(addConfigItem.getReferralAmount());
					referralMarketingCustomerVoucherConfigService.save(referralMarketingCustomerVoucherConfig);
					String emailBody = "Hello there,\r\n" + 
							"\r\n" + 
							"Voucher amount " + addConfigItem.getReferralAmount() + " can be redeemed with us.\r\n" + 
							"\r\n" + "Vocuher Code:" + voucherCode + "\r\n" + 
							"\r\n" +
							"Regards,\r\n" + 
							"Nancy's Business Team";
					String subject = "Your Voucher is here!!";
					emailSenderService.sendMail(user.getEmail(), subject, emailBody);
					referralMarketingDualIncentiveService.checkAndApplyLoyalty(Long.valueOf(communicationId));
				} else if(null != addConfigItem && ("discount".equalsIgnoreCase(addConfigItem.getBenefitType()) || "giftItem".equalsIgnoreCase(addConfigItem.getBenefitType()))){
					user.setReferralId(communicationId);
				}
			}
			request.getSession().removeAttribute("communicationId_");
		}

		customerService.save(user);
		Customer customer = customerService.findByEmail(user.getEmail());

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		setToSession(request, customer);

		model.addAttribute("title", "Registration Successful!");
		model.addAttribute("msg", "Your Account has been activated!<strong>Please Logout and Login again!</strong>");
		return "processSuccess";
	}

	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		// if user already login, then redirect to home page.
		if (session.getAttribute("customerName_") != null) {
			return "redirect:/";
		}

		Customer user = new Customer();
		model.addAttribute("user", user);

		return "registerCustomer";
	}

	@RequestMapping("/all")
	public Iterable<Customer> all(HttpServletRequest request, Model model) {

		return customerService.findAll();
	}

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request,
			Model model) {
		HttpSession session = request.getSession();
		// if user already login, then redirect to home page.
		if (session.getAttribute("customerName_") != null) {
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("error", "* Invalid username or password");
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}
