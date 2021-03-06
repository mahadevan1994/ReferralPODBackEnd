<%@include file="/WEB-INF/view/template/header.jsp"%>

<!-- Cart view section -->
<section id="cart-view" ng-controller="cartCtrl" ng-init="refreshCart()">
	<div>
		<div class="container cartContainer">
			<div class="row">
				<div class="col-md-12">
					<div class="cart-view-area" style="width: 110%">
						<div class="cart-view-table">
							<div class="row">
								<div class="col-sm-12">
									<form action="">

										<div ng-repeat="item in cart.cartItems" class="col-lg-12 cart_Items">
											<input type="hidden"
												id="product-info-{{item.product.productId}}"
												stock="{{item.product.unitInStock}}"
												quantity="{{item.quantity}}"
												productPrice="{{item.product.productPrice}}"/>
											<div class="col-lg-3">
												<a href="/pd/p?id={{item.product.productId}}"> <img
													src="/images/{{item.product.productId}}/0.png" alt="img"
													class="cart-img">
												</a>
											</div>
											<div class="cart_itemName col-lg-4">
												<a class="aa-cart-title"
													href="/pd/p?id={{item.product.productId}}">{{item.product.productName}}</a>

												<p>$ {{item.product.productPrice}}</p>
											</div>
											<div class="cart_itemQuantity col-lg-2">
												<p style="color: red"
													id="quantity-error-cart-{{item.cartItemId}}"></p>
												<select productId="{{item.product.productId}}"
													ng-model="item.selectedOption"
													id="selector-quantity-{{item.cartItemId}}"
													ng-change="selected_quantity(item.cartItemId, '${_csrf.parameterName}=${_csrf.token}')"
													onmousedown="click_selected_quantity(this); this.onmousedown = false;"
													style="height: 35px; width: 45px">
													<option value="{{item.quantity}}" selected>{{item.quantity}}</option>
												</select>
												<div id="update-quantity-div-{{item.cartItemId}}" hidden>
													<input type='number' min='1' max='999'
														id="update-quantity-{{item.cartItemId}}" />
													<button type="button"
														ng-click="update_quantity(item.product.productId, item.cartItemId, '${_csrf.parameterName}=${_csrf.token}');">Update</button>
												</div>

											</div>

											<div class="product-subtotal col-lg-3">
												<div>
													<p>$ {{converDouble(item.product.productPrice)}}</p>

													<p id="total-{{item.product.productId}}"
														style="display: inline; font-weight:600;">SubTotal $
														{{converDouble(item.totalPriceDouble)}}</p>
												</div>
												<div class="cart-product-remove">
													<a href="#"
														ng-click="removeFromCart(item.cartItemId,'${_csrf.parameterName}=${_csrf.token}')">
														Remove Item</a>
												</div>
											</div>
										</div>
										<div class="row">

											<div class="aa-cart-coupon aa-cart-coupon-btn">
												<div class="col-lg-5 col-lg-offset-2">
													<input id="aa-coupon-code" type="text"
													placeholder="Coupon: 123"
													style="width: 90%; text-align: center"> 
												</div>
												<div class="col-lg-4">
													<a href="#"
														ng-click="applyVoucher('${_csrf.parameterName}=${_csrf.token}')">
														<div
															style="width: 52%; height: 35px; vertical-align: initial; font-size: 19px; background: #e01a2b; border: 1px solid #e01a2b; color: #fff; margin-left: 30px;">
															Apply Voucher</div>
													</a>
												</div>

											</div>
											
											
										</div>
										

									</form>
								</div>
								<!-- Cart Total view -->
								
							</div>
							<h4 class="cart_cartTotal">Cart Totals</h4>
								<div class="cart-view-total">

									<table class="aa-totals-table" style="border: none">
										<tbody>
											<!--
                                    <tr>
                                        <th>Subtotal</th>
                                        <td>$ {{calGrandTotal()}}</td>
                                    </tr>-->
											<tr>
												<th>Discount (Referral)</th>
												<td>$
													<p id="cart-grandTotal" style="display: inline">{{calDiscount()}}</p>
												</td>
											</tr>
											<tr>
												<th>Voucher Amount (Referral)</th>
												<td>$
													<p id="cart-grandTotal" style="display: inline">{{calVoucherAmount()}}</p>
												</td>
											</tr>
											<tr>
												<th>Total Cart Value</th>
												<td>$
													<p id="cart-grandTotal" style="display: inline">{{calGrandTotal()}}</p>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="row">
										<div class="col-lg-6">
											<a href="<spring:url value="/"/>"> <input
											class="aa-cart-view-btn keepShoppingBtn" type="text" value="Keep Shopping">
											</a>
										</div>
										
										<div class="col-lg-6">
											<a href="/checkout" class="aa-cart-view-btn"
											style="background: #e01a2b;"> <span
											class="glyphicon-shopping-cart glyphicon"></span> Proceed to
											Checkout
											</a>
										</div>
									</div>
									
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- / Cart view section -->

<%@include file="/WEB-INF/view/template/footer.jsp"%>