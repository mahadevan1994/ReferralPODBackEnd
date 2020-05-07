<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/view/admin/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="container">
		<form:form action="/admin/grf/s?" method="post"
			commandName="genericReferral" enctype="multipart/form-data">
			<div class="form-group">
				<div class="minOrder">
					<label for="minimumOrderCount">Minimum Order Count</label>
					<form:errors path="minimumOrderCount" cssStyle="color: red" />
					<form:input path="minimumOrderCount" id="minimumOrderCount"
						class="form-Control" required="required" />
				</div>
			</div>
			<div class="form-group">
				<div class="totalBusi">
					<label for="totalBusiness">Total Business (in $)</label>
					<form:errors path="totalBusiness" cssStyle="color: red" />
					<form:input path="totalBusiness" id="totalBusiness"
						class="form-Control" required="required" />
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
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
