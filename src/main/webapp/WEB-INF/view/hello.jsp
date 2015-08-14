<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
	<h1>chihuahua REST testing page</h1>
	<table>
	<tr><td>CREATE</td>
		<td><input type="text" id="postResult" style="width:20px" disabled /></td>
		<td><input type="button" id="postBtn" value="TEST" /></td>
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
			success: function(jslogOnError) {
				$("#postResult").val(jslogOnError.error.id);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
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
</script>
