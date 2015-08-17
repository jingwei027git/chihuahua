<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="//libs.cncdn.cn/html2canvas/0.5.0-alpha2/html2canvas.min.js "></script>
	
	<script type="text/javascript" src="http://localhost:28080/chihuahua/js/jslogOnError.js"></script>
	<script type="text/javascript">
	<!--
	var jslog_params = jslog_params || [];
	jslog_params.push("3d9a24a783494c0b9ef6eb88a811a4a5");
	jslog_params.push("http://localhost:28080/chihuahua/errors");
	var jslog_opts = jslog_opts || {};
	jslog_opts.screenshot = true;
	jslog_opts.sourcecode = true;
	jslog_opts.trace = true;
	errors = [];
	window.onerror = function() {errors.push(arguments);JSLOG_ONERROR.collect();}
	//-->
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
</body>

</html>

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
