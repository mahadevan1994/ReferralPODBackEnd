<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/view/admin/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="container">
		<form:form action="/admin/grf/s?" method="post" onsubmit="return genericValidate()" name="genericReferralForm"
			commandName="genericReferral" enctype="multipart/form-data">
			<div class="form-group">
				<div class="minOrder">
					<label for="minimumOrderCount">Minimum Order Count</label>
					<form:errors path="minimumOrderCount" cssStyle="color: red" />
					<form:input  path="minimumOrderCount" id="minimumOrderCount"
						class="form-Control"  />
						<p id="minCount" style="color: red; margin-left: 420px;"></p>
				</div>
			</div>
			<div class="form-group">
				<div class="totalBusi">
					<label for="totalBusiness">Total Business (in $)</label>
					<form:errors path="totalBusiness" cssStyle="color: red" />
					<form:input  path="totalBusiness" id="totalBusiness"
						class="form-Control"  />
						<p id="ttlBsns" style="color: red; margin-left: 400px;"></p>
				</div>
			</div>
			<div class="btnGrp">
				<input type="submit" value="submit" class="btn btn-default" />
			</div>
			<div
				style="width: 50%; float: left; vertical-align: middle; margin-top: 20px; margin-left: 220px;">
				<span style="margin-left: -217px; color: green; font-size: 25;">${msg}</span>
			</div>
		</form:form>
	</div>
</div>
<script>
var minCount = document.forms['genericReferralForm']['minimumOrderCount'];
var ttlBsnss = document.forms['genericReferralForm']['totalBusiness'];
var errorMinCnt = document.getElementById("minCount");
var errorTtlBsns = document.getElementById("ttlBsns");

 

minCount.addEventListener('blur', minCountVerify, true);
ttlBsnss.addEventListener('blur', ttlBusinessVerify, true);
function genericValidate() {
    //var regex = "\d+(\.\d*)?|\.\d+";
    var regex = "/^[0-9]+([.,][0-9]{1,2})?$/";
    if (minCount.value == null || minCount.value == "") {
        minCount.style.border = "1px solid red";
        errorMinCnt.textContent = "Mininum count is required";
         minCount.focus();
            return false;
     }
     if(isNaN(minCount.value)){
         minCount.style.border = "1px solid red";
         errorMinCnt.textContent = "Minimum count in digits" ;
         minCount.focus();
         return false;
     }
     if (ttlBsnss.value == null || ttlBsnss.value == "") {
         ttlBsnss.style.border = "1px solid red";
         errorTtlBsns.textContent = "Total Business is required";
            ttlBsnss.focus();
                return false;
         }
    if(!parseFloat(ttlBsnss.value)){
        ttlBsnss.style.border = "1px solid red";
         errorTtlBsns.textContent = "Enter valid Total Business";
            ttlBsnss.focus();
                return false;
    }     
}

 

function minCountVerify()
{
    if(minCount.value != "") {
        minCount.style.border = "1px solid #5e6e66";
        errorMinCnt.innerHTML = "";
         return true;
    }    
}
function ttlBusinessVerify() {
    if(ttlBsnss.value != "") {
        ttlBsnss.style.border = "1px solid #5e6e66";
        errorTtlBsns.innerHTML = "";
         return true;
    }
}
</script>       
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
