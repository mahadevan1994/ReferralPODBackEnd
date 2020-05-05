<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/view/admin/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

	<div class="container">

		<form:form action="/admin/grf/s?" method="post"
			commandName="genericReferral" enctype="multipart/form-data">
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="minimumOrderCount">Minimum Order Count</label>
				</div>
				<div style="width: 70%; float: right">
					<form:errors path="minimumOrderCount" cssStyle="color: red" />
					<form:input path="minimumOrderCount" id="minimumOrderCount"
						class="form-Control" />
				</div>
			</div>
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="totalBusiness">Total Business (in $)</label>
				</div>
				<div style="width: 70%; float: right">
					<form:errors path="totalBusiness" cssStyle="color: red" />
					<form:input path="totalBusiness" id="totalBusiness"
						class="form-Control" />
				</div>
			</div>
			<div
				style="width: 50%; float: left; vertical-align: middle; margin-top: 20px; margin-left: 220px;">
				<input type="submit" value="submit" class="btn btn-default"/>
			</div>
		</form:form>
		<input type="submit" value="+ Add Configuration" class="form-group" style="width: 13%; margin: 50px 0; padding: 5px 0; float: left; text-align: center;" onclick="toggleHideShow()" />
		
		<div id="addConfig" style="display: none">
			<form:form action="/admin/grf/a?" method="post" commandName="addConfiguration" enctype="multipart/form-data">
				<table>
				<tr>
					<td><label for="programType">Program Type:</label></td>
					<td><label class="checkbox-inline"><form:radiobutton path="programType" id="programType" value="single" checked="checked"/>Single Incentive</label></td>
					<!--<td><label class="checkbox-inline"><form:radiobutton path="programType" id="programType" value="double" />Dual Incentive</label></td> -->
				</tr>
				<tr><td><br/></td></tr>
				<tr>
				    <td><label for="benefitType">Benefit Type:</label></td>
				    <td><label class="checkbox-inline"><form:radiobutton path="benefitType" id="benefitType" value="loyalty" />Loyalty</label></td>
				<td><label class="checkbox-inline"><form:radiobutton path="benefitType" id="benefitType" value="voucher" />Voucher</label></td>  
				</tr>
				<tr>
					<td></td>
				    <td><label class="checkbox-inline"><form:radiobutton path="benefitType" id="benefitType" value="discount" />Discount</label></td>
				<!--<td><label class="checkbox-inline"><form:radiobutton path="benefitType" id="benefitType" value="giftItem" />Gift Item</label></td>-->
				</tr>
				</table><br/>
			 
				<div id="referralMsgAmt">
					<label for="referralMessage">Referral Message</label>
					<label class="checkbox-inline"><form:textarea path="referralMessage" id="referralMessage" value="" style="width: 225px; height: 82px;"/></label><br/><br/>
					<label for="referralAmount" id="referralAmountType"></label>
					<label class="checkbox-inline"><form:input path="referralAmount" id="referralAmount" value="" /></label>
				</div><br/>
				<input type="submit" value="Add" id="add" class="btn btn-default" style="margin-left: 110px" />
			</form:form>
          </div>
	</div>
</div>
<script>
	$("#add").prop("disabled",true);
	$("#referralMsgAmt").hide();
	
	function toggleHideShow() {
	  var x = document.getElementById("addConfig");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  }
	}
	
	$(document).ready(function(){
	    $('input[type="radio"]').click(function(){
	    	var radioValue = $(this).attr("value");
	    	$("#referralMsgAmt").show();
	    	switch(radioValue){
	    		case "loyalty": 
	    			$("#referralAmountType").text("Loyalty Points");
	    			$("#referralMessage").text("Hello <user>, Loyalty points with <points> can be availed by registering with us.");
	    			//$("#referralAmount").val("<loyalty points>");
	    		break;
	    		case "voucher": 
	    			$("#referralAmountType").text("Voucher Amount");
	    			$("#referralMessage").text("Hello <user>, Voucher amount with <amount> will be emailed to your registered email id post the first order.");
	    			//$("#referralAmount").val("<voucher amount>");
			    break;
	    		case "discount": 
	    			$("#referralAmountType").text("Discount Amount");
	    			$("#referralMessage").text("Hello <user>, Discount of <discount> is application on your first order.");
	    			//$("#referralAmount").val("<discount amount>");
			    break;
	    		case "giftItem": 
	    			$("#referralAmountType").text("Gift Item");
	    			$("#referralMessage").text("Hello <user>, Gift Item <Gift Item Description> will be added to the cart post registration.");
	    			//$("#referralAmount").val("<gift item>");
			    break;
	    	}
	        $("#add").prop("disabled",false );
	    });
	});
</script>
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
