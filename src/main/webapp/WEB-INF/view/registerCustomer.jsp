<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@include file="/WEB-INF/view/template/header.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="ca-header">
                    <div class="ca-header-title">
                    <div class="icon-gen-secure-bk-huge macys-options-icon" id="iconSecure">
                        <h3>Secure create account</h3>
                      </div>
                      
                    </div>
                  </div>
                <div class="aa-myaccount-area">
                    <div class="col-md-6" style="float: none; margin-left: 30%; width: 50%"><!-- "-->
                        <div class="aa-myaccount-login">

                            

                            <div id="error" style="color: red">${email_exists}</div><br>
                            <div class="cell ca-profile-copy">
                                <p>We'll email you an <span class="extra20off">EXTRA 25% OFF</span> your next purchase!*.</p>
                            </div>
                            <form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user">
                                <c:set var = "now" value = "<%= new java.util.Date()%>" />
                                
                                <div class="form-group">
                                    <label for="customerName">Your Name<span style="color: red">*</span></label>
                                    <form:errors path="customerName" cssStyle="color: red" />
                                    <form:input path="customerName" id="customerName" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <label for="email">Email<span style="color: red">*</span></label>
                                    <form:errors path="email" cssStyle="color: red" />
                                    <form:input type="email" path="email" id="email" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <label for="password">Password<span style="color: red">*</span></label>
                                    <form:errors path="password" cssStyle="color: red" />
                                    <form:input path="password" id="password" type="password" class="form-Control" />
                                </div>
                                <div class="form-group">
                                    <label for="passwordConfirm">Password again<span style="color: red">*</span></label>
                                    <input type="password" id="passwordConfirm" class="form-Control">
                                </div>
                                <div id="sr-preferences-title" class="grid-x ca-profile-title">
                                    <div class="small-12 cell">
                                      <label>My Preferences</label>
                                    </div>
                                  </div>
                                <div class="ca-preference">
                                    <div class="cell">
                                      <!-- <label for="ca-profile-send-email" class="show-for-sr">My Preferences Email Notification</label> -->
                                      <input type="checkbox" id="ca-profile-send-email" name="ca-profile-send-email" class="subscribe" checked="checked" aria-describedby="ca-profile-send-email-desc">
                                      <span id="ca-profile-send-email-desc" class="subscribe-label">Send me emails about sales, events, new arrivals and more!</span>
                                    </div>
                                    <div class="cell">
                                      <!-- <label for="ca-profile-text-msg" class="show-for-sr">My Preferences Text Alerts</label> -->
                                      <input type="checkbox" id="ca-profile-text-msg" name="ca-profile-text-msg" class="subscribe" aria-describedby="ca-profile-text-msg-desc">
                                      <span id="ca-profile-text-msg-desc" class="subscribe-label">
                                          Get text alerts on sales, orders and important account information.
                                      </span>
                                    </div>
                                  </div>
                                <script>
                                    $("#passwordConfirm").on("change paste keyup", function() {
                                        var password = $("#password").val();
                                        var confirmPassword = $("#passwordConfirm").val();

                                        if (password != confirmPassword){
                                            $("#register-submit").prop('disabled', true);
                                            $("#error").html("Passwords do not match!");
                                        }else{
                                            $("#register-submit").prop('disabled', false);
                                            $("#error").html("");
                                        }
                                    });
                                    function jsfunction()
                                    {
                                        //you code
                                        return false;
                                    }
                                </script>

                                <input type="submit" value="Create Account" id="register-submit" class="btn btn-default" disabled>
                                <!-- <a href="<c:url value="/" />" class="btn btn-default">Cancel</a> -->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form:form>
                            <div class="grid-x policies-links">
                                <div class="cell ca-profile-footer-links">
                                    <p><a href="https://www.macys.com/cms/slp/3/112616doiwelcomeexclusions?cm_sp=simplified_account-_-create_profile-_-exclusions_apply" title="exclusions" class="ca-exclusions" target="_blank"><strong>*Exclusions apply.</strong></a></p>
                                    <p>By selecting "create account" you agree that you are subject to Macy's Notice of <a href="https://www.customerservice-macys.com/app/answers/detail/a_id/40?cm_sp=simplified_account-_-create_profile-_-privacy_practices" title="privacy practices" class="ca-privacy-practice" target="_blank"><strong>Privacy
                                      Practices</strong></a> and <a href="https://www.customerservice-macys.com/app/answers/detail/a_id/39?cm_sp=simplified_account-_-create_profile-_-legal_notice" title="legal notice" class="ca-legal-notice" target="_blank"><strong>Legal Notice</strong></a>.</p>
                                </div>
                              </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/view/template/footer.jsp" %>