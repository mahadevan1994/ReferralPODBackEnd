<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/view/admin/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Program Type</th>
					<th>Benefit Type</th>
					<th>Referral Amount</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${savedConfiguration}" var="configuration">
					<tr>
						<td>${configuration.programType}</td>
						<td>${configuration.benefitType}</td>
						<td>${configuration.referralAmount}</td>
						<td><a href="/admin/pd/s?id=${product.productId}"> <span
								class="glyphicon glyphicon-pencil"></span></a> <a
							href="/admin/pd/d?id=${product.productId}"> <span
								class="glyphicon glyphicon-remove"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container">
		<input type="submit" value="+ Add Configuration" class="form-group"
			onclick="showConfigurationFields()" id="addCongfigBtn" />
		<hr class="horizontalLine">
		<div id="addConfig" style="display: none">

			<form:form action="/admin/irf/s?" method="post"
				commandName="addConfiguration" enctype="multipart/form-data">
				<table>
					<tr>
						<td><label for="programType" id="pType">Program Type:</label></td>
						<td><label class="checkbox-inline"><form:radiobutton
									path="programType" id="programType" value="single"
									checked="checked" class="prgType" />Single Incentive</label></td>
						<!--<td><label class="checkbox-inline"><form:radiobutton path="programType" id="programType" value="double" />Dual Incentive</label></td> -->
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><label for="benefitType" id="bType">Benefit Type:</label></td>
						<td><label class="checkbox-inline"><form:radiobutton
									path="benefitType" id="benefitType" value="loyalty" />Loyalty</label></td>
						<td><label class="checkbox-inline"><form:radiobutton
									path="benefitType" id="benefitType" value="voucher" />Voucher</label></td>
						<td><label class="checkbox-inline"><form:radiobutton
									path="benefitType" id="benefitType" value="discount" />Discount</label></td>
					</tr>

				</table>
				<br />

				<div id="referralMsgAmt">
					<label for="referralMessage">Referral Message</label> <label
						class="checkbox-inline"><form:textarea
							path="referralMessage" id="referralMessage" value="" /></label><br /> <br />
					<label for="referralAmount" id="referralAmountType"></label> <label
						class="checkbox-inline"><form:input path="referralAmount"
							id="referralAmount" value="" /></label>
				</div>
				<br />
				<input type="submit" value="Add" id="add" class="btn btn-default" />
			</form:form>
		</div>
	</div>
</div>
<script>
	$("#add").prop("disabled", true);
	$("#referralMsgAmt").hide();

	function showConfigurationFields() {
		var divElement = document.getElementById("addConfig");
		if (divElement.style.display === "none") {
			divElement.style.display = "block";
		}
	}

	$(document)
			.ready(
					function() {
						$('input[type="radio"]')
								.click(
										function() {
											var radioValue = $(this).attr(
													"value");
											$("#referralMsgAmt").show();
											switch (radioValue) {
											case "loyalty":
												$("#referralAmountType").text(
														"Loyalty Points");
												$("#referralMessage")
														.text(
																"Hello there, Loyalty points with <points> can be availed by registering with us.");
												break;
											case "voucher":
												$("#referralAmountType").text(
														"Voucher Amount");
												$("#referralMessage")
														.text(
																"Hello there, Voucher amount with <amount> will be emailed to your registered email id post the first order.");
												break;
											case "discount":
												$("#referralAmountType").text(
														"Discount Amount");
												$("#referralMessage")
														.text(
																"Hello there, Discount of <discount> is applicable on your first order.");
												break;
											case "giftItem":
												$("#referralAmountType").text(
														"Gift Item");
												$("#referralMessage")
														.text(
																"Hello there, Gift Item <Gift Item Description> will be added to the cart post registration.");
												break;
											}
											$("#add").prop("disabled", false);
										});
					});
</script>
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
