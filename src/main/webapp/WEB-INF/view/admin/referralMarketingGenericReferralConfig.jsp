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
				<input type="submit" value="submit" class="btn btn-default">
			</div>

			<div class="form-group"
				style="width: 100%; margin: 20px 0; padding: 15px 0; float: left; text-align: center;">
				+ Add Configuration</div>

		</form:form>
	</div>
</div>
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
