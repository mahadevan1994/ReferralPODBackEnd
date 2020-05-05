<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/view/admin/template/header.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

	<div class="container">

		<form:form action="/admin/urf/s?" method="post"
			commandName="userReferral" enctype="multipart/form-data">
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="referralEnablement">Referral Enablement</label>
					<form:errors path="referralEnablement" cssStyle="color: red" />
				</div>
				<div style="width: 70%; float: right">
					<label for="referralEnablement">Enabled</label>
					<form:radiobutton path="referralEnablement" id="referralEnablement"
						value="true" />
					<label for="referralEnablement" style="margin-left: 45px">Disabled</label>
					<form:radiobutton path="referralEnablement" id="referralEnablement"
						value="false" />
				</div>
			</div>
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="referralFrequency">Referral Frequency</label>
				</div>
				<div style="width: 70%; float: right">
					<form:errors path="referralFrequency" cssStyle="color: red" />
					<form:input path="referralFrequency" id="referralFrequency"
						class="form-Control" />
				</div>
			</div>
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="referralEnablementDate">Referral Enablement
						Date</label>
				</div>
				<div style="width: 70%; float: right">
					<form:errors path="referralEnablementDate" cssStyle="color: red" />
					<form:input path="referralEnablementDate"
						id="referralEnablementDate" type="Date" class="form-Control" />
				</div>
			</div>
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label for="referralinkExpiry">Referral Link Expire (in
						days)</label>
				</div>
				<div style="width: 70%; float: right">
					<form:errors path="referralinkExpiry" cssStyle="color: red" />
					<form:input path="referralinkExpiry" id="referralinkExpiry"
						class="form-Control" />
				</div>
			</div>
			<div class="form-group" style="margin: 20px 0; padding: 15px 0;">
				<div style="width: 30%; float: left; vertical-align: middle;">
					<label class="control-label" for="referralBenifitExpiry">Referral
						Benefit Expire (in days)</label>
				</div>
				<div style="width: 70%; float: right">
					<form:input id="referralBenifitExpiry" path="referralBenifitExpiry"
						class="form-Control" />
				</div>
			</div>
			<div
				style="width: 50%; float: left; vertical-align: middle; margin-top: 20px; margin-left: 220px;">
				<input type="submit" value="submit" class="btn btn-default">
			</div>

		</form:form>
	</div>
</div>
<%@include file="/WEB-INF/view/admin/template/footer.jsp"%>
