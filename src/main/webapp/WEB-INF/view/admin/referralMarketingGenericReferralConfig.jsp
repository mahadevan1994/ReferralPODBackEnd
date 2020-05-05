<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/view/admin/template/header.jsp" %>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

 <div class="container">

<form:form action="/admin/grf/s?" method="post" commandName="genericReferral" enctype="multipart/form-data">
            <div class="form-group">
                <label for="minimumOrderCount">Minimum Order Count</label> <form:errors path="minimumOrderCount" cssStyle="color: red" />
                <form:input path="minimumOrderCount" id="minimumOrderCount" class="form-Control" style="margin-left: 53px" />
            </div>
            <div class="form-group">
                <label for="totalBusiness">Total Business (in $)</label> <form:errors path="totalBusiness" cssStyle="color: red" />
                <form:input path="totalBusiness" id="totalBusiness" class="form-Control" style="margin-left: 68px"/>
            </div>
            <input type="submit" value="submit" class="btn btn-default" style="margin-left: 110px">
            
            <div class="form-group">
            <span style="margin-left: 310px">+ Add Configuration</span>
            </div>

        </form:form>    
    </div>
    </div>
<%@include file="/WEB-INF/view/admin/template/footer.jsp" %>
