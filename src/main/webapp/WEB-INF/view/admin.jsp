<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
	<title>Basic Example with JSX</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/softpower/react/css/rb-react.css">
	
	<style type="text/css">
	body {
		font: 13px/1.3 "Lucida Grande",Verdana,sans-serif;
		color: #19272E;
	}
	</style>
</head>
<body>
	<p>.<p>.<p>.<p>
	<table>
	<tr><td><span id="txtUsername"></span></td></tr>
	<tr><td><span id="txtAddress"></span></td></tr>
	<tr><td><span id="txtMemo"></span></td></tr>
	</table>
	<p>
	<div id="app"></div>
	
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	<i class="fa fa-user"></i>
 	<i class="fa fa-user fa-lg" style="color: red"></i>
 	
</body>
<footer>
	<span data-tooltip="cccccccccccc">
		<i class="fa fa-asterisk rb-icon-mandatory"></i>
	</span>
	<script src="${pageContext.request.contextPath}/assets/jquery/1.8.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/react/0.13.3/react-with-addons.js"></script>
	<script src="${pageContext.request.contextPath}/assets/softpower/react/js/RbTextField.js"></script>
	<script type="text/javascript">
		function abc(val) {
			if (val.length < 1) {
				return 'must input';
			} else {
				return '';
			}
		}
		React.render(
			React.createElement(RbTextField, {name: "username", placeholder: ' user name', icon: "fa fa-user fa-lg", require: "true", validate: abc, requireMsg: 'Answer is required', autoTrim: 'false'}),
			$("#txtUsername")[0]
		);
		React.render(
			React.createElement(RbTextField, {name: "address", validate: abc, showValidate: "true", placeholder: ' address', require: "true", requireMsg: 'Answer is required', toLowerCase: 'true', maxLength: "6"}),
			$("#txtAddress")[0]
		);
		React.render(
				React.createElement(RbTextField, {name: "memo", placeholder: ' memo', toUpperCase: 'true', maxLength: "20", icon: 'fa fa-calendar-plus-o'}),
				$("#txtMemo")[0]
			);
	</script>
</footer>
</html>