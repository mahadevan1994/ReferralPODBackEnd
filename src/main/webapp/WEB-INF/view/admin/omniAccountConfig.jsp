<%@include file="/WEB-INF/view/admin/template/header.jsp" %>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <h1 class="page-header">Omni Account Management</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Omni Account Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>
                     <c:if test="${!customer.omniAccountEnabled}">
                     Disabled
                     </c:if>
                      <c:if test="${customer.omniAccountEnabled}">
                     Enabled
                     </c:if>
                   </td>
                    <td>
                    <c:if test="${!customer.omniAccountEnabled}">
                     <a href="/admin/cu/ee?id=${customer.customerId}">
                            <span class="glyphicon glyphicon-ok-circle"></span></a>
                     </c:if>
                     <c:if test="${customer.omniAccountEnabled}">
                     <a href="/admin/cu/eee?id=${customer.customerId}">
                            <span class="glyphicon glyphicon-remove"></span></a>
                     </c:if>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>

<%@include file="/WEB-INF/view/admin/template/footer.jsp" %>
