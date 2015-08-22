<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
	<sec:csrfMetaTags />
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="//libs.cncdn.cn/html2canvas/0.5.0-alpha2/html2canvas.min.js "></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jslogOnError.js"></script>
	<script type="text/javascript">
	var jslog_params = jslog_params || [];
	jslog_params.push("3d9a24a783494c0b9ef6eb88a811a4a5");
	jslog_params.push("${pageContext.request.contextPath}/errors");
	var jslog_opts = jslog_opts || {};
	jslog_opts.screenshot = true;
	jslog_opts.sourcecode = true;
	jslog_opts.trace = true;
	errors = [];
	window.onerror = function() {errors.push(arguments);JSLOG_ONERROR.collect();}
	</script>
</head>
<body>
	<sec:authorize access="hasRole('USER')">
		hasRole(user) <p>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN')">
		hasRole(admin) <p>
	</sec:authorize>
	<a href="${pageContext.request.contextPath}/admin">jump to admin page</a>
	<p>
	<a href="${pageContext.request.contextPath}/errors/scriptcode/1">generate scriptcode</a>
	<p>
	{{1393300831 | date:'yyyy-MM-dd'}}ab
	<div ng-controller="myCtrl1">
		<input type="number" ng-model="quantity">
		<input type="number" ng-model="price">
		<p ng-bind="quantity * price"></p>
	</div>
	<div ng-controller="myCtrl2">
		<input type="text" ng-model="username">
		<input type="password" ng-model="password">
		<table>
			<tr>
				<td>{{$index}} {{name.Name | uppercase | lowercase }} - {{name.City}}</td>
			</tr>
		</table>
		<ul>
			<li ng-repeat="name in names | orderBy:'company'">
				{{$index}} {{name.Name | uppercase | lowercase }} - {{name.City}}
			</li>
		</ul>
	</div>
	<table>
	<tr><td>CREATE</td>
		<td><input type="text" id="postResult" style="width:20px" disabled /></td>
		<td><input type="button" id="postBtn" value="TEST" /></td>
		<td><input type="button" id="csrfBtn" value="REST-CSRF" /></td>
	</tr>
	</table>
</body>

</html>

<script type="text/javascript">
$( document ).ready( function() {
	$("#postBtn").bind("click", function() {
		var sampleData = getSampleData();
		var jsonString = JSON.stringify(sampleData);
		$.ajax({
			method: "POST",
			url: "${pageContext.request.contextPath}/errors",
			data: jsonString,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			beforeSend: function(jqXHR) {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");
				jqXHR.setRequestHeader(header, token);
				jqXHR.setRequestHeader("Authorization", "Basic " + btoa("admin:admin123"));
			},
			success: function(jslogOnError) {
				$("#postResult").val(jslogOnError.error.id);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	});
	
	$("#csrfBtn").bind("click", function() {
		xxxx();
	});
});

function getSampleData() {
	var client = {};
	client.navigatorAppVersion = "5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/44.0.2403.130 Safari/537.36";
	client.navigatorCookieEnabled = "true";
	client.navigatorLanguage = "zh-TW";
	client.navigatorPlatform = "Win32";
	client.navigatorUserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/44.0.2403.130 Safari/537.36";
	client.navigatorVendor = "Google Inc.";
	client.screenWidth = 1920;
	client.screenHeight = 1080;
	client.screenAvailWidth = 1920;
	client.screenAvailHeight = 1040;
	client.screenColorDepth = 24;
	client.screenPixelDepth = 24;
	client.locationHref = "http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015";
	client.locationHostname = "localhost";
	client.locationPathname = "/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action";
	client.locationProtocol = "http:";
	client.documentWidth = 1169;
	client.documentHeight = 5615;
	
	var error = new Object();
	error.errMsg = "Uncaught SyntaxError: Unexpected identifier";
	error.url = "http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action";
	error.line = 144;
	error.col = 16;
	error.errCount = 1;
	error.errObj = null;
	
	var jslogOnError = new Object();
	jslogOnError.appKey = "3d9a24a783494c0b9ef6eb88a811a4a5";
	jslogOnError.error = error;
	jslogOnError.client = client;
	jslogOnError.screenshot = null;
	
	return jslogOnError;
}

function xxxx() {
	$.ajax({
		type: 'GET',
		url: '/chihuahua/errors/scriptcode/1'
 
	}).done(function (data, textStatus, jqXHR) {
		var csrfToken = jqXHR.getResponseHeader('X-CSRF-TOKEN');
		if (csrfToken) {
			var cookie = JSON.parse($.cookie('helloween'));
			cookie.csrf = csrfToken;
			$.cookie('helloween', JSON.stringify(cookie));
			console.log(cookie);
		}
	}).fail(function (jqXHR, textStatus, errorThrown) {
		if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
			var cookie = JSON.stringify({method: 'GET', url: '/', csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN')});
			$.cookie('helloween', cookie);
			console.log(cookie);
		} else {
			console.error('Houston, we have a problem...');
		}
	});
}
</script>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl1', function($scope) {
    $scope.quantity = 2;
    $scope.price = 50;
});
app.controller('myCtrl2', function($scope, $http) {
    $scope.username = 'depp2';
    $scope.password = 'softpower';
    $scope.lastname = $scope.password;
    $scope.fullname = function() {
    	return $scope.username + " vs kevin";
    }
    $http.get("http://www.w3schools.com/angular/customers.php").success(function(response) {
    	$scope.names = response.records;
    });
});

app.filter('checkmark', function() {
	return function(input) {
		return input ? '\u2713' : '\u2718';
	};
});
</script>
