<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/view/template/header.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />

<section id="aa-myaccount">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="aa-myaccount-area">
					<div class="col-md-6"
						style="float: none; margin-left: 30%; width: 50%">
						<div class="aa-myaccount-login">
							<form:form
								action="${pageContext.request.contextPath}/sendReferral"
								method="post" modelAttribute="user">
								<c:set var="now" value="<%= new java.util.Date()%>" />
								<div class="form-group">
									<label for="email">Email<span style="color: red">*</span></label>
									<form:errors path="email" cssStyle="color: red" />
									<form:input type="email" path="email" id="email"
										class="form-Control" />
									<form:input type="hidden" path="communicationId" id="communicationId"
									class="form-Control" />
								</div>
								<input type="submit" value="Submit" id="register-submit"
									class="btn btn-default">
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<%@include file="/WEB-INF/view/template/footer.jsp"%>