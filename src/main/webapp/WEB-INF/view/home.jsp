<%@include file="/WEB-INF/view/template/header.jsp"%>
<!-- Primary function library placed before the callback and Primary Code Block. -->
<!-- Start slider -->
<!-- <button onclick="testMe('${_csrf.parameterName}=${_csrf.token}')">Click Me</button> -->

<!-- Place this code inside the <body> element as close to the top as possible -->
<script charset="utf-8" type="text/javascript">
	function session_id() {
		return /SESS\w*ID=([^;]+)/i.test(document.cookie) ? RegExp.$1 : false;
	}

	function getProfileName() {
		return "${pageContext.request.userPrincipal.name}";
	}
</script>

<script charset="utf-8" type="text/javascript">
	RR.jsonCallback = function() {
		// Place your rendering logic here. Actual code varies depending on your website implementation.
		console.dir(RR.data.JSON.placements);
	};
</script>
<c:if test="${not empty error}">
	<div
		style="font-size: 4rem; font-weight: bold; color: red; text-align: center">${error}</div>
</c:if>
<!-- / slider -->
<!-- Start Promo section -->
<section id="aa-promo">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-promo-area">
					<div class="row">
						<!-- promo left -->
						<div class="col-md-5 no-padding">
							<div class="aa-promo-left">
								<div class="aa-promo-banner">
									<img style="object-fit: cover;" src="img/for-women-450x450.jpg"
										alt="img">
									<div class="aa-prom-content">
										<span>75% Off</span>
										<h4>
											<a href="/pd/s?mc=women">For Women</a>
										</h4>
									</div>
								</div>
							</div>
						</div>
						<!-- promo right -->
						<div class="col-md-7 no-padding">
							<div class="aa-promo-right">
								<div class="aa-single-promo-right">
									<div class="aa-promo-banner">
										<img style="object-fit: cover;" src="img/for-men-300x220.jpg"
											alt="img">
										<div class="aa-prom-content">
											<span>Exclusive Item</span>
											<h4>
												<a href="/pd/s?mc=men">For Men</a>
											</h4>
										</div>
									</div>
								</div>
								<div class="aa-single-promo-right">
									<div class="aa-promo-banner">
										<img src="img/for-shoes.jpg" alt="img">
										<div class="aa-prom-content">
											<span>On Sale</span>
											<h4>
												<a href="/pd/s?sc=shoes">On Shoes</a>
											</h4>
										</div>
									</div>
								</div>
								<div class="aa-single-promo-right">
									<div class="aa-promo-banner">
										<img src="img/for-kids.jpg" alt="img">
										<div class="aa-prom-content">
											<span>New Arrivals</span>
											<h4>
												<a href="/pd/s?mc=kids">For Kids</a>
											</h4>
										</div>
									</div>
								</div>
								<div class="aa-single-promo-right">
									<div class="aa-promo-banner">
										<img style="object-fit: cover;" src="img/for-bags.jpg"
											alt="img">
										<div class="aa-prom-content">
											<span>25% Off</span>
											<h4>
												<a href="/products/all?sc=bags">For Bags</a>
											</h4>
										</div>
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
<!-- / Promo section -->
<!-- Products section -->
<section id="aa-product" ng-controller="cartCtrl">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="aa-product-area">
						<div class="aa-product-inner">
							<!-- start prduct navigation -->
							<ul class="nav nav-tabs aa-products-tab">
								<c:set var="counter" value="0" scope="page" />
								<c:forEach items="${mainCategoryNameList}" var="category">
									<c:set var="counter" value="${counter + 1}" scope="page" />
									<c:if test="${counter eq 1}">
										<li class="active"><a href="#${category}"
											data-toggle="tab">${category}</a></li>
									</c:if>
									<c:if test="${counter ne 1}">
										<li><a href="#${category}" data-toggle="tab">${category}</a></li>
									</c:if>
								</c:forEach>
							</ul>
							<!-- Tab panes -->
							<div class="tab-content">
								<!-- Start men product category -->
								<c:set var="counter" value="0" scope="page" />
								<c:forEach items="${mainCategoryNameList}" var="category">
									<c:set var="counter" value="${counter + 1}" scope="page" />
									<c:if test="${counter eq 1}">
										<div class="tab-pane fade in active" id="${category}">
									</c:if>
									<c:if test="${counter ne 1}">
										<div class="tab-pane fade" id="${category}">
									</c:if>
									<ul class="aa-product-catg">
										<!-- start single product item -->
										<c:forEach items="${products}" var="product">
											<c:if
												test="${category eq product.productCategory.mainCategoryName && !product.referralGift}">
												<li>
													<figure>
														<a class="aa-product-img"
															href="<c:url value=" /pd/p?id=${product.productId} " />">
															<img style="width: 250px; height: 300px"
															src="<c:url value="images/${product.productId}/0.png" />">
														</a>
														<a class="aa-add-card-btn" style="cursor: hand"
															ng-click="addToCart('${product.productId}','${sessionScope.communicationId_}','${_csrf.parameterName}=${_csrf.token}')">
															<span class="fa fa-shopping-cart"></span>Add To Cart
														</a>
														<figcaption>
															<h4 class="aa-product-title">
																<a
																	href="<c:url value=" /pd/p?id=${product.productId} " />">${product.productName}</a>
															</h4>
															<span class="aa-product-price">$${product.productPrice}</span><br>
															<span class="aa-product-price"><del>$999</del></span>
														</figcaption>
													</figure> <!-- product badge --> <span class="aa-badge aa-sale"
													href="#">SALE!</span>
												</li>
											</c:if>
										</c:forEach>
									</ul>
									<a class="aa-browse-btn" href="/pd/s?mc=${category}">Browse
										all Product <span class="fa fa-long-arrow-right"></span>
									</a>
							</div>
							<!-- / men product category -->
							</c:forEach>
						</div>
						<!-- quick view modal -->
						<div class="modal fade" id="quick-view-modal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-body">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<div class="row">
											<!-- Modal view slider -->
											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="aa-product-view-slider">
													<div class="simpleLens-gallery-container" id="demo-1">
														<div class="simpleLens-container">
															<div class="simpleLens-big-image-container">
																<a class="simpleLens-lens-image"
																	data-lens-image="img/view-slider/large/polo-shirt-1.png">
																	<img src="img/view-slider/medium/polo-shirt-1.png"
																	class="simpleLens-big-image">
																</a>
															</div>
														</div>
														<div class="simpleLens-thumbnails-container">
															<a href="#" class="simpleLens-thumbnail-wrapper"
																data-lens-image="img/view-slider/large/polo-shirt-1.png"
																data-big-image="img/view-slider/medium/polo-shirt-1.png">
																<img src="img/view-slider/thumbnail/polo-shirt-1.png">
															</a> <a href="#" class="simpleLens-thumbnail-wrapper"
																data-lens-image="img/view-slider/large/polo-shirt-3.png"
																data-big-image="img/view-slider/medium/polo-shirt-3.png">
																<img src="img/view-slider/thumbnail/polo-shirt-3.png">
															</a> <a href="#" class="simpleLens-thumbnail-wrapper"
																data-lens-image="img/view-slider/large/polo-shirt-4.png"
																data-big-image="img/view-slider/medium/polo-shirt-4.png">
																<img src="img/view-slider/thumbnail/polo-shirt-4.png">
															</a>
														</div>
													</div>
												</div>
											</div>
											<!-- Modal view content -->
											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="aa-product-view-content">
													<h3>T-Shirt</h3>
													<div class="aa-price-block">
														<span class="aa-product-view-price">$34.99</span>
														<p class="aa-product-avilability">
															Avilability: <span>In stock</span>
														</p>
													</div>
													<p>Lorem ipsum dolor sit amet, consectetur adipisicing
														elit. Officiis animi, veritatis quae repudiandae quod
														nulla porro quidem, itaque quis quaerat!</p>
													<h4>Size</h4>
													<div class="aa-prod-view-size">
														<a href="#">S</a> <a href="#">M</a> <a href="#">L</a> <a
															href="#">XL</a>
													</div>
													<div class="aa-prod-quantity">
														<form action="">
															<select name="" id="">
																<option value="0" selected="1">1</option>
																<option value="1">2</option>
																<option value="2">3</option>
																<option value="3">4</option>
																<option value="4">5</option>
																<option value="5">6</option>
															</select>
														</form>
														<p class="aa-prod-category">
															Category: <a href="#">Polo T-Shirt</a>
														</p>
													</div>
													<div class="aa-prod-view-bottom">
														<a href="#" class="aa-add-to-cart-btn"><span
															class="fa fa-shopping-cart"></span>Add To Cart</a> <a
															href="#" class="aa-add-to-cart-btn">View Details</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- / quick view modal -->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</section>
<!-- / Products section -->
<!-- banner section -->
<section id="aa-banner">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="aa-banner-area">
						<a href="#"><img
							src="<spring:url value="img/banner-footer-1170x170.jpg" />"
							alt="fashion banner img"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- popular section -->
<section id="aa-popular-category" ng-controller="cartCtrl">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="aa-popular-category-area">
						<!-- start prduct navigation -->
						<ul class="nav nav-tabs aa-products-tab">
							<li class="active"><a href="#popular" data-toggle="tab">Popular</a></li>
							<li><a href="#latest" data-toggle="tab">Latest</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<!-- Start men popular category -->
							<div class="tab-pane fade in active" id="popular">
								<ul class="aa-product-catg aa-popular-slider">
									<!-- start single product item -->
									<c:forEach items="${productPopular}" var="product">
										<c:if test="${!product.referralGift}">
											<li>
												<figure>
													<a class="aa-product-img"
														href="<c:url value=" /pd/p?id=${product.productId} " />">
														<img style="width: 250px; height: 300px"
														src="<c:url value="images/${product.productId}/0.png" />">
													</a>
													<a class="aa-add-card-btn" style="cursor: hand"
														ng-click="addToCart('${product.productId}','${sessionScope.communicationId_}','${_csrf.parameterName}=${_csrf.token}')">
														<span class="fa fa-shopping-cart"></span>Add To Cart
													</a>
													<figcaption>
														<h4 class="aa-product-title">
															<a
																href="<c:url value=" /pd/p?id=${product.productId} " />">${product.productName}</a>
														</h4>
														<span class="aa-product-price">$${product.productPrice}</span><span
															class="aa-product-price"><del>$999</del></span>
													</figcaption>
												</figure> <!-- product badge --> <span class="aa-badge aa-sale"
												href="#">SALE!</span>
											</li>
										</c:if>
									</c:forEach>
								</ul>
								<a class="aa-browse-btn" href="/pd/s">Browse all Product <span
									class="fa fa-long-arrow-right"></span></a>
							</div>
							<!-- / popular product category -->

							<!-- start latest product category -->
							<div class="tab-pane fade" id="latest">
								<ul class="aa-product-catg aa-latest-slider">
									<!-- start single product item -->
									<c:forEach items="${productLatest}" var="product">
										<c:if test="${!product.referralGift}">
											<li>
												<figure>
													<a class="aa-product-img"
														href="<c:url value=" /pd/p?id=${product.productId} " />">
														<img style="width: 250px; height: 300px"
														src="<c:url value="images/${product.productId}/0.png" />">
													</a>
													<a class="aa-add-card-btn" style="cursor: hand"
														ng-click="addToCart('${product.productId}','${sessionScope.communicationId_}','${_csrf.parameterName}=${_csrf.token}')">
														<span class="fa fa-shopping-cart"></span>Add To Cart
													</a>
													<figcaption>
														<h4 class="aa-product-title">
															<a
																href="<c:url value=" /pd/p?id=${product.productId} " />">${product.productName}</a>
														</h4>
														<span class="aa-product-price">$${product.productPrice}</span><span
															class="aa-product-price"><del>$999</del></span>
													</figcaption>
												</figure> <!-- product badge --> <span class="aa-badge aa-sale"
												href="#">SALE!</span>
											</li>
										</c:if>
									</c:forEach>
								</ul>
								<a class="aa-browse-btn" href="#">Browse all Product <span
									class="fa fa-long-arrow-right"></span></a>
							</div>
							<!-- / latest product category -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- / popular section -->


<%@include file="/WEB-INF/view/template/footer.jsp"%>