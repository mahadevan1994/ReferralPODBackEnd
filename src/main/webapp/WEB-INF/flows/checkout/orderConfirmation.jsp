<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/view/template/header.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="container" style="margin-left: 0%">
        <div class="page-header orderConfirmation">
            <h1>Order</h1>

            <p class="lead">Order Confirmation</p>
            
            <p class="lead">P.S: 10% of the order total will be credited back as Loyalty points</p>
        </div>

        <div class="container">
            <div class="row">
                <form:form commandName="order" class="form-horizontal">
                    <div class="well col-xs-10 col-sm-10 col-md-10 col-xs-offset-10 col-sm-offset-1">
                        <div class="text-center">
                            <h1>Receipt</h1>
                        </div>
						<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                            	<c:forEach items="${shippingAddresses}" var="shippingAddress">
                            	<c:if test="${shippingAddress.addressId eq customerOrderShippingAddress.originalShippingAddressId }">
                                	<c:set var="address" value="${shippingAddress}"></c:set>
                                </c:if>
                                </c:forEach>
                                <address>
                                	
                                    <strong>Shipping Address</strong><br>
                                        ${address.address}
                                    <br>
                                        ${address.city}, ${address.state}
                                    <br>
                                        ${address.country}, ${address.zipCode}
                                    <br>
                                </address>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                <p>Order Date: <fmt:formatDate type="date" value="${now}" /></p>
                                <p><strong>Name: ${address.fullName}</strong></p>
                                <p><strong>Phone: ${address.phoneNumber}</strong></p>
                            </div>
                        </div>

                        <div class="row">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <form:hidden path="orderTotalPrice" value="cart.grandTotal" />
                                <form:hidden path="orderDate" value="${now}" />
                                <c:forEach var="cartItem" items="${cart.cartItems}">
                                    <tr class="orderConfirmation_items">
                                        <td class="col-md-9"><em>${cartItem.product.productName}</em></td>
                                        <td class="col-md-1" style="text-align: center">${cartItem.quantity}</td>
                                        <td class="col-md-1" style="text-align: center">${cartItem.product.productPrice}</td>
                                        <td class="col-md-1" style="text-align: center">{{converDouble(${cartItem.totalPriceDouble})}}</td>
                                    </tr>
                                </c:forEach>

								<c:if test="${cart.discountPrice > 0}">
									<tr>
	                                    <td></td>
	                                    <td></td>
	                                    <td class="text-right orderConfirmation_GTotal">
	                                        <h4><strong>Discount (Referral)</strong></h4>
	                                    </td>
	                                    <td class="text-center text-danger">
	                                        <h4><strong>$${cart.discountPrice}</strong></h4>
	                                    </td>
	                                </tr>
                                </c:if>
                                <c:if test="${cart.voucherAmount > 0}">
									<tr>
	                                    <td></td>
	                                    <td></td>
	                                    <td class="text-right orderConfirmation_GTotal">
	                                        <h4><strong>Voucher Amount (Referral)</strong></h4>
	                                    </td>
	                                    <td class="text-center text-danger">
	                                        <h4><strong>$${cart.voucherAmount}</strong></h4>
	                                    </td>
	                                </tr>
                                </c:if>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td class="text-right orderConfirmation_GTotal">
                                        <h4><strong>Grand Total: </strong></h4>
                                    </td>
                                    <td class="text-center text-danger">
                                        <h4><strong>$${cart.grandTotal}</strong></h4>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <input type="hidden" name="_flowExecutionKey">

                        <br><br>
                        <div class="orderConfirmation_btnGrp">
                            <button class="btn btn-default" name="_eventId_backToCollectCustomerInfo">Back</button>
                            <input type="submit" value="Submit Order" class="btn btn-default" name="_eventId_orderConfirmed">
                            <button class="btn btn-default" name="_eventId_cancel">Cancel</button>
                        </div>
                        
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/view/template/footer.jsp" %>