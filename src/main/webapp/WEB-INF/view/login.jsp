<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<%-- HEAD --%>
<head>
<meta charset="utf-8">
<title>Login Page</title>
<link href="${pageContext.request.contextPath}/assets/semantic/2.0.8/semantic.min.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
body {
	background-color: #DADADA;
}
body > .grid {
	height: 100%;
}
.column {
	max-width: 450px;
}
</style>
</head>

<%-- BODY --%>
<body>
<script src="${pageContext.request.contextPath}/assets/jquery/1.8.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/semantic/2.0.8/semantic.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/angular/1.4.4/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/angular/semantic/0.0.3/angular-semantic-ui.min.js"></script>

<div class="ui middle aligned center aligned grid">
<div class="column">
	<%-- LOGIN TITLE --%>
	<div class="ui teal message">
		<div class="header">
			Chihuahua login page
		</div>
	</div>
	<form action="<c:url value='/j_spring_security_check' />" method='POST' class="ui large form">
		<div class="ui stacked segment">
			<%-- ACCOUNT field --%>
			<div class="field">
				<div class="ui left icon input">
					<i class="user icon"></i>
					<input type="text" name="username" id="account" placeholder="account (email address)" />
				</div>
			</div>
			
			<%-- PASSWORD filed --%>
			<div class="field">
				<div class="ui left icon input">
					<i class="lock icon"></i>
					<input type="password" name="password" id="password" placeholder="password" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
			</div>
			
			<%-- LOGIN button --%>
			<div class="ui fluid large teal submit button">
				Login
			</div>
		</div>
		
		<%-- FORM VALIDATION message --%>
		<div class="ui error message">
			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
		</div>
		
		<%-- LOGIN ERROR message --%>
		<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
	</form>
</div>
</div>

<script type="text/javascript">
<%-- FORM VALIDATE --%>
(function ($) {
	$('.ui.form').form({
		fields: {
			username: {
				identifier: 'username',
				rules: [{
					type: 'empty',
					prompt: 'Please enter Account'
				}]
			},
			password: {
				identifier: 'password',
				rules: [{
					type: 'empty',
					prompt: 'Please enter Password'
				}]
			}
		}
	});
}(jQuery));
</script>

</body>

</html>
