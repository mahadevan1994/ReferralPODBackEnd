<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/view/admin/template/header.jsp" %>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

 <div class="container">

<form:form action="/admin/urf/s?" method="post" commandName="userReferral" enctype="multipart/form-data">
            <div class="form-group">
                <label for="referralEnablement">Referral Enablement</label> <form:errors path="referralEnablement" cssStyle="color: red" />
                <form:radiobutton path="referralEnablement" id="referralEnablement"  style="margin-left: 45px"/>
            </div>
            <div class="form-group">
                <label for="referralFrequency">Referral Frequency</label> <form:errors path="referralFrequency" cssStyle="color: red" />
                <form:input path="referralFrequency" id="referralFrequency" class="form-Control" style="margin-left: 53px" />
            </div>
            <div class="form-group">
                <label for="referralEnablementDate">Referral Enablement Date</label> <form:errors path="referralEnablementDate" cssStyle="color: red" />
                <form:input path="referralEnablementDate" id="referralEnablementDate" type="Date" class="form-Control" style="margin-left: 10px"/>
            </div>
            <div class="form-group">
                <label for="referralinkExpiry">Referral Link Expiry</label> <form:errors path="referralinkExpiry" cssStyle="color: red" />
                <form:input path="referralinkExpiry" id="referralinkExpiry" class="form-Control" style="margin-left: 52px"/>
            </div>

            <div class="form-group">
                <label class="control-label" for="referralBenifitExpiry">Referral Benifit Expiry</label>
                <form:input id="referralBenifitExpiry" path="referralBenifitExpiry"  class="form-Control" style="margin-left: 37px"/>
            </div>

            <input type="submit" value="submit" class="btn btn-default" style="margin-left: 110px">

        </form:form>    
    </div>
    </div>
<%@include file="/WEB-INF/view/admin/template/footer.jsp" %>
