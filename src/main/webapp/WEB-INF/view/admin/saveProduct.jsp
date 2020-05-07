<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/view/admin/template/header.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="container">
        <div class="page-header">
            <h1>${title}</h1>
        </div>

        <form:form action="/admin/pd/s" method="post" commandName="product" enctype="multipart/form-data">
        
        	<form:hidden path="productViews" value="${product.productViews}" />
            <form:hidden path="productId" value="${product.productId}" />
            
            <div class="form-group">
                <label for="name">Name</label><br><form:errors path="productName" cssStyle="color: red" />
                <form:input path="productName" id="name" class="form-Control" />
            </div>

            <div class="form-group">
                <label for="productCategory">Category</label><br><form:errors path="productCategory" cssStyle="color: red" />
                <c:forEach items="${categoryList}" var="category">
                	<c:if test="${category.categoryId eq product.productCategory.categoryId}">
	                    <label class="checkbox-inline">
	                    	<form:radiobutton path="productCategory" checked="checked" value="${category}"/>
	                           ${category.mainCategoryName} - ${category.subCategoryName}
	                    </label>
                	</c:if>
                	<c:if test="${category.categoryId ne product.productCategory.categoryId}">
	                	<label class="checkbox-inline">
	                    	<form:radiobutton path="productCategory" value="${category}"/>
	                           ${category.mainCategoryName} - ${category.subCategoryName}
	                    </label>
                    </c:if>
                </c:forEach>
            </div>

            <div class="form-group">
                <label for="productSummary">Summary</label><br><form:errors path="productSummary" cssStyle="color: red" />
                <form:input path="productSummary" id="productSummary" class="form-Control" />
            </div>

            <div class="form-group">
                <label for="price">Price</label> <br><form:errors path="productPrice" cssStyle="color: red" />
                <form:input type="Number" step="0.01" path="productPrice" id="price" class="form-Control" min="0"/>
            </div>

            <input type="submit" value="submit" class="btn btn-default">
            <a href="/admin/pd/m" class="btn btn-default">Cancel</a>

        </form:form>
    </div>
</div>
<%@include file="/WEB-INF/view/admin/template/footer.jsp" %>