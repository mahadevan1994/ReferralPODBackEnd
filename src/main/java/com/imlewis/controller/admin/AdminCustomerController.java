package com.imlewis.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imlewis.model.Customer;
import com.imlewis.referral.model.ReferralMarketingCustomer;
import com.imlewis.referral.service.ReferralMarketingCustomerOrderService;
import com.imlewis.service.CustomerService;

@Controller
@RequestMapping("/admin/cu")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ReferralMarketingCustomerOrderService referralMarketingCustomerOrderService;

    @RequestMapping("/m")
    public String customerManagement(Model model){
        List<Customer> customerList = customerService.getAllCustomer();
        List<ReferralMarketingCustomer> referralMarketingCustomerList = new ArrayList<ReferralMarketingCustomer>();
        for (Customer customer : customerList) {
        	ReferralMarketingCustomer referralMarketingCustomer = new ReferralMarketingCustomer();
        	referralMarketingCustomer.setCustomerId(customer.getCustomerId());
        	referralMarketingCustomer.setCustomerName(customer.getCustomerName());
        	referralMarketingCustomer.setEmail(customer.getEmail());
        	referralMarketingCustomer.setLoyaltyPoints(customer.getLoyaltyPoints());
        	referralMarketingCustomer.setOrderCounts(referralMarketingCustomerOrderService.getOrderCounts(customer));
        	referralMarketingCustomer.setOrderTotals(referralMarketingCustomerOrderService.getOrderTotals(customer));
        	referralMarketingCustomerList.add(referralMarketingCustomer);
		}
        model.addAttribute("customerList", referralMarketingCustomerList);

        return "admin/customerManagement";
    }
    
    @RequestMapping("/d")
    public String removeCustomer(@RequestParam(value="id", required=true) Long customerId){
    	customerService.delete(customerId);
    	return "redirect:/admin/cu/m";
    }
    
    @RequestMapping("/e")
    public String enableCustomer(@RequestParam(value="id", required=true) Long customerId){
    	Customer customer = customerService.findOne(customerId);
    	customer.setEnabled(true);
    	customerService.save(customer);
    	return "redirect:/admin/cu/m";
    }
    
    @RequestMapping("/ds")
    public String disableCustomer(@RequestParam(value="id", required=true) Long customerId){
    	Customer customer = customerService.findOne(customerId);
    	customer.setEnabled(false);
    	customerService.save(customer);
    	return "redirect:/admin/cu/m";
    }
}
