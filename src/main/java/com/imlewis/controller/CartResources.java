package com.imlewis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imlewis.model.Cart;
import com.imlewis.model.CartItem;
import com.imlewis.model.Customer;
import com.imlewis.model.Product;
import com.imlewis.referral.model.ReferralMarketingCustomerVoucherConfig;
import com.imlewis.referral.model.ReferralMarketingGenericReferralAddConfigItem;
import com.imlewis.referral.model.ReferralMarketingUserCommunicationConfig;
import com.imlewis.referral.service.ReferralMarketingCustomerVoucherConfigService;
import com.imlewis.referral.service.ReferralMarketingDualIncentiveService;
import com.imlewis.referral.service.ReferralMarketingGenericReferralAddConfigService;
import com.imlewis.referral.service.ReferralMarketingUserCommunicationConfigService;
import com.imlewis.repository.CartItemRepository;
import com.imlewis.repository.CartRepository;
import com.imlewis.repository.CustomerRepository;
import com.imlewis.repository.ProductRepository;
import com.imlewis.service.CartService;
import com.imlewis.service.ProductService;

@Controller
@RequestMapping("/rest/cart")
public class CartResources {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	ReferralMarketingUserCommunicationConfigService userCommunicationConfigService;

	@Autowired
	ReferralMarketingGenericReferralAddConfigService referralAddConfigService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;

	@Autowired
	ReferralMarketingCustomerVoucherConfigService referralMarketingCustomerVoucherConfigService;

	@Autowired
	private ReferralMarketingDualIncentiveService referralMarketingDualIncentiveService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String cartId = session.getAttribute("cartId_").toString();
		if (cartId != null) {
			return cartRepository.findOne(Long.parseLong(cartId, 10));
		}
		return null;
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void saveItem(@RequestParam(value = "q", required = false) String quantity,
			@RequestParam(value = "communicationId", required = false) String communicationId,
			@PathVariable(value = "productId") Long productId, @AuthenticationPrincipal User activeUser) {
		if (activeUser != null) {
			if (quantity == null) {
				throw new IllegalArgumentException("Quantity = null");
			}

			int q = Integer.parseInt(quantity);

			Customer customer = customerRepository.findByEmail(activeUser.getUsername());
			Cart cart = customer.getCart();
			Product product = productRepository.findOne(productId);
			List<CartItem> cartItems = cart.getCartItems();

			// The code below is magic! Do not touch!
			for (int i = 0; i < cartItems.size(); i++) {
				if (product.getProductId() == cartItems.get(i).getProduct().getProductId()) {
					CartItem cartItem = cartItems.get(i);
					cartItem.setQuantity(cartItem.getQuantity() + q);
					cartItem.setTotalPriceDouble(product.getProductPrice() * cartItem.getQuantity());
					cartItemRepository.save(cartItem);
					return;
				}
			}

			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(q);
			cartItem.setTotalPriceDouble(product.getProductPrice() * cartItem.getQuantity());
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);

			long customerReferralId = customer.getReferralId();
			if (customerReferralId > 0) {
				ReferralMarketingUserCommunicationConfig userCommunicationConfigItem = userCommunicationConfigService
						.getReferralMarketingUserCommunicationConfig(customerReferralId);
				if (null != userCommunicationConfigItem) {
					long referralConfigId = userCommunicationConfigItem.getReferralConfigurationId();
					ReferralMarketingGenericReferralAddConfigItem addConfigItem = referralAddConfigService
							.getAddConfigItem(referralConfigId);
					if (null != addConfigItem) {
						if ("giftItem".equalsIgnoreCase(addConfigItem.getBenefitType())) {
							product = productService.getProductById(addConfigItem.getGiftSelect());
							cartItem = new CartItem();
							cartItem.setProduct(product);
							cartItem.setQuantity(1);
							cartItem.setCart(cart);
							cartItemRepository.save(cartItem);

							referralMarketingDualIncentiveService.checkAndApplyLoyalty(Long.valueOf(communicationId));
						} else if ("discount".equalsIgnoreCase(addConfigItem.getBenefitType())) {
							cart.setDiscountPrice((double)addConfigItem.getReferralAmount());
							cartService.save(cart);

							referralMarketingDualIncentiveService.checkAndApplyLoyalty(Long.valueOf(communicationId));
						}
					}
				}
			}
		}
	}

	@RequestMapping(value = "/{cartItemId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable(value = "cartItemId") Long cartItemId) {
		cartItemRepository.delete(cartItemId);
	}

	@RequestMapping(value = "/{cartItemId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void editItem(@RequestParam(value = "q") int quantity, @PathVariable(value = "cartItemId") Long cartItemId) {
		CartItem cartItem = cartItemRepository.findOne(cartItemId);
		cartItem.setTotalPriceDouble(cartItem.getProduct().getProductPrice() * quantity);
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request.")
	public void handleClientErrors(Exception e) {
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Server error.")
	public void handleServertErrors(Exception e) {
	}
	
	@RequestMapping(value = "/voucher", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void applyVoucher(@RequestParam(value = "voucherCode", required = false) String voucherCode, @AuthenticationPrincipal User activeUser) {
		if (activeUser != null) {
			Customer customer = customerRepository.findByEmail(activeUser.getUsername());
			Cart cart = customer.getCart();
			ReferralMarketingCustomerVoucherConfig referralMarketingCustomerVoucherConfig = referralMarketingCustomerVoucherConfigService.findByVoucherCode((voucherCode));
			if(null != referralMarketingCustomerVoucherConfig && referralMarketingCustomerVoucherConfig.getRedeemStatus().equalsIgnoreCase("NO")) {
				cart.setVoucherAmount((double)referralMarketingCustomerVoucherConfig.getReferralAmount());
				referralMarketingCustomerVoucherConfig.setRedeemStatus("YES");
				referralMarketingCustomerVoucherConfigService.save(referralMarketingCustomerVoucherConfig);
			}
			cartService.save(cart);
		}
	}
}
