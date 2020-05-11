<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html ng-app="myApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Web Commerce Shop for - homeware, furniture and fashion |
	Home</title>


<!-- Font awesome -->
<link href="/css/font-awesome.css" rel="stylesheet">
<!-- Bootstrap -->
<link href="/css/bootstrap.css" rel="stylesheet">
<!-- SmartMenus jQuery Bootstrap Addon CSS -->
<link href="/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
<!-- product view slider -->
<link rel="stylesheet" type="text/css" href="/css/jquery.simpleLens.css">
<!-- slick slider -->
<link rel="stylesheet" type="text/css" href="/css/slick.css">
<!-- price picker slider -->
<link rel="stylesheet" type="text/css" href="/css/nouislider.css">
<!-- Theme color -->
<link id="switcher" href="/css/theme-color/default-theme.css"
	rel="stylesheet">
<!-- Top Slider CSS -->
<link href="/css/sequence-theme.modern-slide-in.css" rel="stylesheet"
	media="all">

<!-- Main style sheet -->
<link href="/css/style.css" rel="stylesheet">

<!-- Google Font -->
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>

<link href="/css/custom.css" rel="stylesheet">

<!-- jQuery library -->
<script src="/js/jquery.min.js"></script>
</head>
<body>
	<div class="redesign-header radical">
		<div class="redesign-header-content">
			<section id="media-rail" class="rail media-rail">
				<div>
					<div id="redesign-media-rail" class="redesign-header-media">
						<div class="redesign-header-wrapper">
							<div class="redesign-header-media-col text-left">
								<span class="redesign-header-media-content"> <span
									data-cm-href="cm_sp=navigation-_-topnav-_-left-hand_domestic_4267022">FREE
										SHIPPING WITH $25 PURCHASE.&nbsp; <a title="Exclusions"
										href="https://www.Nancys.com/s/free-shipping/"> <span
											style="text-decoration: underline;">EXCLUSIONS</span>
									</a>
								</span>
								</span>
							</div>
							<div class="redesign-header-media-col text-right">
								<span class="redesign-header-media-content"> <span
									data-cm-href="cm_sp=navigation-_-topnav-_-right-hand_domestic_4265408">OUR
										RESPONSE TO COVID-19. <a title="SEE FAQs"
										href="https://www.Nancys.com/social/covid-19/" target="_blank"
										rel="noopener">SEE FAQs</a>
								</span>
								</span>
							</div>
						</div>
					</div>
				</div>
			</section>
			<div class="redesign-header-wrapper">
				<section id="link-rail" class="rail link-rail">
					<div>
						<div class="redesign-header-links">
							<div
								class="redesign-header-links-col redesign-header-links-col-left text-left clearfix">
								<div id="logo" class="redesign-header-logo" title="Nancy's">
									<a href="/" id="logoFashion"><img
										src="../images/logos/commerce-and-shopping.svg" alt="Logo" /><span>Nancys</span></a>
								</div>
								<nav class="redesign-header-nav">
									<ul class="redesign-header-nav-list">
										<li class="redesign-header-nav-list-item"><a
											title="Stores"
											href="">Stores</a></li>
										<li class="redesign-header-nav-list-item"><a
											title="Deals"
											href="">Deals</a></li>
										<li id="header-wishlist-label"
											class="redesign-header-nav-list-item"><a title="Lists"
											href="">Lists</a></li>
										<li class="redesign-header-nav-list-item dropdown"><span
											id="giftsLink" tabindex="0">GIFTS</span>
											<div class="dropdown-caret icon-ui-carrot-down-bk-small"></div>
											<ul class="dropdown-list dropdown-list-align-left"
												role="group" aria-labelledby="giftsLink">
												<li class="dropdown-item"><a title="Gift Cards"
													href="">Gift
														Cards</a></li>
												<li class="dropdown-item"><a title="Gift Ideas"
													href="">Gift
														Ideas</a></li>
											</ul></li>
										<li class="redesign-header-nav-list-item"><a
											title="Wedding Registry"
											href="">Wedding
												Registry</a></li>
									</ul>
								</nav>
							</div>
							<!-- <div
								class="redesign-header-links-col redesign-header-links-col-center">
								<div id="header-store-info-desktop"></div>
							</div> -->
							<div
								class="redesign-header-links-col redesign-header-links-col-right text-right">
								<nav class="redesign-header-nav">
									<ul class="redesign-header-nav-list">
										<c:if test="${pageContext.request.userPrincipal.name != null}">
											<li class="redesign-header-nav-list-item"><a href="#"
												style="cursor: default">Welcome: ${customerName_}</a></li>
											<li class="redesign-header-nav-list-item"><a
												href="/admin/">Admin</a></li>
											<li class="redesign-header-nav-list-item"><a
												href="/customer/account">My Account</a></li>
											<li class="redesign-header-nav-list-item"><a
												href="/logout">Logout</a></li>
										</c:if>
										<ul class="redesign-header-nav-list">
											<li
												class="redesign-header-nav-list-item dropdown star-rewards-nav-list">

												<div id="myRewardsLabel">
													<div>
														<div id="myRewardsLabel-container">
															<c:if
																test="${pageContext.request.userPrincipal.name == null}">
																<div class="icon-profile-gr-large"></div>
																<a id="myRewardsLabel-status" href=""
																	data-toggle="modal" data-target="#login-modal">
																	Sign In </a>
														</div>
														</c:if>
													</div>
												</div>

											</li>
										</ul>
								</nav>
							</div>
						</div>
					</div>
				</section>

				<section id="search-category-rail" class="rail search-category-rail">
					<div class="redesign-header-core" id="core">
						<div id="shopByDepartmentDropdown" role="navigation"
							aria-label="main">
							<div
								class="redesign-header-radical-category-dropdown animated-hide">
								<div id="shopByDepartmentDropdownLabel"
									class="redesign-header-category-dropdown-label">
									<span id="shopByDepartmentLabelText">SHOP BY DEPARTMENT</span>
									<button id="showByDepartmentCaret"
										class="redesign-header-category-dropdown-caret icon-ui-carrot-down-bk-small"
										tabindex="0" aria-haspopup="true" aria-expanded="false"
										aria-labelledby="shopByDepartmentLabelText"></button>
								</div>
							</div>
						</div>
						<div class="redesign-header-radical-searchbox">
							<div id="search-bag-wrapper">
								<div id="search" class="">
									<form target="_top" name="keywordSearch" method="GET"
										action="/shop/search">
										<div id="clearable_globalSearchInputField"
											style="position: relative; white-space: nowrap;">
											<div id="clearable_globalSearchInputField"
												style="position: relative; white-space: nowrap;">
												<input size="9" maxlength="120" type="text"
													id="globalSearchInputField"
													class="globalSearchInputField right" autocomplete="off"
													placeholder="Search or enter web ID" name="keyword"
													value="" required="" oninvalid="return false"
													style="padding-right: 22px; display: inline-block;">
												<div class="clearlink icon-ui-close-gr-small"
													style="display: none;" title="Click to clear this textbox">&nbsp;</div>
											</div>
											<div class="clearlink icon-ui-close-gr-small"
												style="display: none;" title="Click to clear this textbox">&nbsp;</div>
										</div>
										<button id="searchSubmit" type="submit" value="GO"
											aria-label="Submit product search"></button>

										<ul class="suggestlist" id="autosuggest" style="width: 530px;"></ul>
									</form>

								</div>
							</div>
						</div>
						<div class="aa-cartbox" ng-controller="cartCtrl">
							<a class="aa-cart-link"
								href="<spring:url value="/customer/cart" />"> <span
								class="fa fa-shopping-basket"></span> <span
								class="aa-cart-title">SHOPPING CART</span> <c:if
									test="${pageContext.request.userPrincipal.name != null}">
									<div ng-init="refreshCart()"></div>
									<span class="aa-cart-notify">{{cart.cartItems.length}}</span>
								</c:if>
							</a>
							<c:if test="${pageContext.request.userPrincipal.name != null}">
								<div class="aa-cartbox-summary"
									style="display: none">
									<ul>
										<li ng-repeat="item in cart.cartItems"><a
											class="aa-cartbox-img"><img class="aa-cartbox-img-source"
												src="/images/{{item.product.productId}}/0.png" alt="img"></a>
											<div style="display: inline-flex; margin-left: 40px">
												<div class="aa-cartbox-info">
													<h4>
														<a href="/products/viewProduct/{{item.product.productId}}">{{item.product.productName}}</a>
													</h4>
													<p>{{item.quantity}} x $ {{item.product.productPrice}}</p>
												</div>
												<a class="aa-remove-product" href="#"
													ng-click="removeFromCart(item.cartItemId,'${_csrf.parameterName}=${_csrf.token}')"
													style="margin-left: inherit"> <span
													class="aa-primary-btn" style="background: #fff">Remove</span>
												</a>
											</div></li>
										<c:if test="${cart.discountPrice > 0.00}">
											<li><div
													style="text-align: center; padding-top: 10px; margin-top: 10px">
													<span class="aa-cartbox-total-title">Discount
														(Referral)</span><span class="aa-cartbox-total-price">$
														{{calDiscount()}}</span>
												</div></li>
										</c:if>
										<c:if test="${cart.voucherAmount > 0.00}">
											<li><div
													style="text-align: center; padding-top: 10px; margin-top: 10px">
													<span class="aa-cartbox-total-title">Voucher Amount
														(Referral)</span><span class="aa-cartbox-total-price">$
														{{calVoucherAmountt()}}</span>
												</div></li>
										</c:if>
										<li><div
												style="text-align: center; padding-top: 10px; margin-top: 10px">
												<span class="aa-cartbox-total-title">Total</span> <span
													class="aa-cartbox-total-price">$ {{calGrandTotal()}}</span>
											</div></li>
									</ul>
									<a class="aa-cartbox-checkout aa-primary-btn" href="/checkout">Checkout</a>
								</div>
							</c:if>
						</div>
					</div>

				</section>
			</div>

		</div>
	</div>
</body>
</html>