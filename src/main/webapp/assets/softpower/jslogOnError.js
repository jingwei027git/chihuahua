/**
 *  JSLOG onerror monitor
 *
 *  [requeire]
 *   - jQuery
 *   - html2canvas (for screenshot)
 *
 *  [reserved function]
 *   - N/A
 *
 */

window.JSLOG_ONERROR = (function() {
	var paramAppKey = null;
	var paramJslogAbsPath = null;

	var _options = null;

	var _argumentCheckResult = 0; // {0 = unchecked, 1 = checked(OK), -1 = checked(NOT OK)}

	var _MAX_ERR_COUNT = 2;
	var _errCount = 0;

	this.collect = function() {
		if (!verifyRequirement()) { clearErrors(); return; }
		if (!verifyErrCount()) { clearErrors(); return; }

		paramAppKey = getParamAppKey();
		paramJslogAbsPath = getParamJslogRestURL();
		_options = getOptions();
		TRACE('paramAppKey : ' + paramAppKey);
		TRACE('paramJslogAbsPath : ' + paramJslogAbsPath);

		if (!verifyArguments()) { clearErrors(); return; }
		var error;
		errors = errors || [];
		while (errors.length > 0) {
			_errCount++;
			error = errors.shift();
			catchError(error);
		}
	}

	function FORCETRACE(msg) {
		try {
			if (typeof console == 'object') {
				console.log(msg);
			}
		}catch(e){ }
	}

	function TRACE(msg) {
		try {
			if (jslog_opts.trace) {
				console.log(msg);
			}
		}catch(e){ }
	}

	function verifyRequirement() {
		if (typeof jQuery == 'function') {
			if (typeof jslog_params == 'undefined' ||
				typeof jslog_opts == 'undefined') {
				FORCETRACE('[ERROR] jslog_params or jslog_opts not define');
				return false;
			}else{
				return true;
			}
		}else{
			FORCETRACE('[ERROR] jQuery not present');
			return false;
		}
	}

	function verifyErrCount() {
		TRACE('verifyErrCount {' + _errCount + '}');
		if (_errCount >= _MAX_ERR_COUNT) {
			TRACE('[WARN] errCount exceed MAX_ERR_COUNT');
			return false;
		}else{
			return true;
		}
	}

	function verifyArguments() {
		TRACE('verifyArguments {' + _argumentCheckResult + '}');
		if (_argumentCheckResult ==  1) return true;
		if (_argumentCheckResult == -1) return false;

		if ($.trim(paramJslogAbsPath) == '' || $.trim(paramAppKey) == '') {
			_argumentCheckResult = -1;
			FORCETRACE('[ERROR] paramJslogAbsPath or paramAppKey is empty');
			return false;
		}else{
			_argumentCheckResult = 1;
			return true;
		}
	}

	function clearErrors() {
		TRACE('clearErrors {}');
		try {
			errors = errors || [];
			errors = [];
		}catch(e){ }
	}

	function catchError(args) {
		TRACE('catchError {}');
		if (args && args != 'undefined') {
			try {
				handleErr(args[0], args[1], args[2]);
			}catch(e){ }
		}
	}

	function handleErr(errMsg, url, line) {
		TRACE('handleErr {' + errMsg + '} {' + url + '} {' + line + '}');

		// PASS error from jslogOnError.js
		if ($.trim(url) != '') {
			if (url.indexOf('jslogOnError.js') != -1) {
				TRACE('PASS error from jslogOnError.js');
				return;
			}
		}

		var asyncTiming = 1000;
		setTimeout(function() {
			TRACE('setTimeout {}');
			var jslogOnError = new Object();
			jslogOnError.appKey = paramAppKey;
			jslogOnError.error = getJslogErrorObj(_errCount, errMsg, url, line, -1, null);
			jslogOnError.client = getClientInfo();

			postOnError(jslogOnError, _options);
		}, asyncTiming);
	}

	function getJslogErrorObj(errCount, errMsg, url, line, col, errObj) {
		TRACE('getJslogErrorObj {' + errCount + '} {' + errMsg + '} {' + url + '} {' + line + '} {' + '} {' + col + '} {' + errObj + '}');
		var error = {};
		error.errCount = errCount;
		error.errMsg = errMsg;
		error.url = url;
		error.line = line;
		error.col = col;
		error.errObj = errObj;
		return error;
	}

	function getClientInfo() {
		TRACE('getClientInfo {}');
		var client = {};
		client.navigatorAppVersion = navigator.appVersion;
		client.navigatorCookieEnabled = navigator.cookieEnabled;
		client.navigatorLanguage = navigator.language;
		client.navigatorPlatform = navigator.platform;
		client.navigatorUserAgent = navigator.userAgent;
		client.navigatorVendor = navigator.vendor;

		client.screenWidth = screen.width;
		client.screenHeight = screen.height;
		client.screenAvailWidth = screen.availWidth;
		client.screenAvailHeight = screen.availHeight;
		client.screenColorDepth = screen.colorDepth;
		client.screenPixelDepth = screen.pixelDepth;

		client.locationHref = location.href;
		client.locationHostname = location.hostname;
		client.locationPathname = location.pathname;
		client.locationProtocol = location.protocol;

		client.documentWidth = -1;
		client.documentHeight = -1;
		try { client.documentWidth = $(document).width(); } catch (e) { };		// maybe fail (if DOM not finished)
		try { client.documentHeight = $(document).height(); } catch (e) { };	// maybe fail (if DOM not finished)

		return client;
	}

	function postOnError(jslogOnError, options) {
		TRACE('postOnError {}');
		try {
		$.support.cors = true;
		$.ajax({
            url: paramJslogAbsPath,
            type: 'post',
            data: JSON.stringify(jslogOnError),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
            	jslogOnError = data;
            	TRACE('options.screenshot {' + options.screenshot + '}');
            	if (options.screenshot) {
            		putOnErrorScreenshot(jslogOnError);
            	}
            	TRACE('options.sourcecode {' + options.sourcecode + '}');
            	if (options.sourcecode) {
            		putOnErrorSourcecode(jslogOnError);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
            	TRACE(errorThrown);
            }
        });
		}catch(e){ }
	}

	function clear(jslogOnError) {
		TRACE('clear {}');
		jslogOnError.screenshot = null;
		jslogOnError.sourcecode = null;
	}

	function putOnErrorSourcecode(jslogOnError) {
		TRACE('putOnErrorSourcecode {' + jslogOnError.error.url + '}');
		try {
			var $html = $("html");
			if ($html.size() > 0) {
	        	var sourcecode = {};
	        	sourcecode.mimetype = "html/text";
	        	sourcecode.content = $html.html();
	        	jslogOnError.sourcecode = sourcecode;
	        	putOnError(jslogOnError, jslogOnError.error.id, 'sourcecode');
			}else{
				TRACE('[WARN] html tag not found, pass sourcecode generate');
			}
//			var url = $.trim(jslogOnError.error.url);
//			if (url != '' && url.indexOf(document.domain) !== -1) {
//				$.ajax({
//		            url: url,
//		            type: 'get',
//		            success: function (data, textStatus, jqXHR) {
//		            	clear(jslogOnError);
//		            	var sourcecode = {};
//		            	sourcecode.mimetype = "html/text";
//		            	sourcecode.content = data;
//		            	jslogOnError.sourcecode = sourcecode;
//		            	putOnError(jslogOnError, jslogOnError.error.id);
//		            },
//		            error: function (jqXHR, textStatus, errorThrown) {
//		            	TRACE("[WARN] ajax get {" + url + "} failure, error is " + errorThrown);
//		            }
//		        });
//			} else {
//				TRACE("[WARN] url illegal, url {" + url + "}");
//			}
		} catch (e) { }
	}

	function putOnErrorScreenshot(jslogOnError) {
		TRACE('putOnErrorScreenshot');
		try {
			var $html = $("html");
			if (typeof html2canvas === 'function' && $html.size() > 0) {
				html2canvas($html, {
					onrendered: function(canvas) {
						clear(jslogOnError);
						var screenshot = {};
						screenshot.mimetype = "image/png";
						screenshot.base64 = canvas.toDataURL("image/png");
						jslogOnError.screenshot = screenshot;
						putOnError(jslogOnError, jslogOnError.error.id, 'screenshot');
					}
				});
			} else {
				TRACE("[WARN] html2canvas not present or html tag not found");
			}
		} catch (e) { }
	}

	function putOnError(jslogOnError, id, target) {
		TRACE('putOnError {' + target + '}');
		try {
			$.ajax({
	            url: paramJslogAbsPath + '/' + id,
	            type: 'put',
	            data: JSON.stringify(jslogOnError),
	            contentType: "application/json; charset=utf-8",
	            dataType: 'json'
	        });
		}catch(e){ }
	}

	function getParamAppKey() {
		TRACE('getParamAppKey');
		if (!(typeof jslog_params === 'undefined') && (jslog_params.length > 0)) {
			return jslog_params[0];
		}
		return '';
	}

	function getParamJslogRestURL() {
		TRACE('getParamJslogRestURL');
		if (!(typeof jslog_params === 'undefined') && (jslog_params.length > 1)) {
			return jslog_params[1];
		}
		return '';
	}

	function getOptions() {
		TRACE('getOptions');
		jslog_opts = jslog_opts || {};

		// [trace] {true, false}
		if ($.trim(jslog_opts.trace) == '') {
			jslog_opts.trace = false;
		}else{
			if (jslog_opts.trace === true || jslog_opts.trace === false) {
				// OK
			}else{
				jslog_opts.trace = false;
			}
		}

		// [screenshot] {true, false}
		if ($.trim(jslog_opts.screenshot) == '') {
			jslog_opts.screenshot = false;
		}else{
			if (jslog_opts.screenshot === true || jslog_opts.screenshot === false) {
				// OK
			}else{
				jslog_opts.screenshot = false;
			}
		}

		// [sourcecode] {true, false}
		if ($.trim(jslog_opts.sourcecode) == '') {
			jslog_opts.sourcecode = false;
		}else{
			if (jslog_opts.sourcecode === true || jslog_opts.sourcecode === false) {
				// OK
			}else{
				jslog_opts.sourcecode = false;
			}
		}

		return jslog_opts;
	}

	return {
		collect : collect
	}
})();
