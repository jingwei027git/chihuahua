var spFeedback = {};

spFeedback.tabHtml = '';
spFeedback.dialogHtml = '';

spFeedback.getTab = function() {
	return $("#spFeedback_tab");
}

spFeedback.getDialog = function() {
	return $("#spFeedback_dialog");
}

spFeedback.getBlock = function() {
	return $("#spFeedback_block");
}


/*
 * initial feedback UI
 * 
 * $node - set a element for feedback UI use (it will append on the element)
 * tabHtml - custom tab html
 * dialogHtml - custom dialog html
 */
spFeedback.init = function($node, tabHtml, dialogHtml, options) {
	var me = this;
	
	if ($node.size() > 0) {
		this.tabHtml = $.trim(tabHtml);
		this.dialogHtml = $.trim(dialogHtml);
		
		var $spFeedback = generateSpFeedback($node);
		
		createBlock($spFeedback);
    	createTab($spFeedback);
    	createDialog($spFeedback);
    	registerEvent();
    }
	
	function generateSpFeedback($elem) {
		var html = '<div id="spFeedback" class="spFeedback"></div>';
		$elem.append(html);
		return $("div[id='spFeedback']");
	}
	
	function createBlock($elem) {
		var html = '<div id="spFeedback_block" class="feedback_block" style="display: none;"></div>';
    	$elem.append(html);
	}
    
	/*
	 * create feedback tab
	 */
    function createTab($elem) {
    	var html = '';
    	var source = $.trim(me.tabHtml);
    	if (source == '') {
	    	html += '<a id="spFeedback_tab" class="feedback_tab" href="#">Feedback</a>';
    	}else{
    		html += source;
    	}
    	$elem.append(html);
    }
    
    /*
     * create feedback Dialog (default hide)
     */
    function createDialog($elem) {
    	var html = '';
    	var source = $.trim(me.dialogHtml);
    	if (source == '') {
	    	html += '<div id="spFeedback_dialog" class="feedback_dialog popover drop-in" style="display: none;">';
	    	html += '	<div class="popover-content">';
	    	html += '		<div class="row-fluid">';
	    	html += '			<label>Description(*)</label>';
	    	html += '			<textarea id="spFeedback_textarea"></textarea>';
	    	html += '		</div>';
	    	html += '		<div>';
	    	html += '			<a class="btn" id="spFeedback_cancel">Cancel</a>';
	    	html += '			<a class="btn btn-primary" id="spFeedback_submit">Submit Feedback</a>';
	    	html += '		</div>';
	    	html += '	</div>';
	    	html += '</div>';
    	}else{
    		html += source;
    	}
    	$elem.append(html);
    }
    
    function clearInput() {
    	$("#spFeedback_textarea").val("");
    }
    
    function openDialog(flag) {
    	if (flag) {
    		if ($.isFunction(options.beforeShowDialog)) {
				options.beforeShowDialog();
			}
			$("html").addClass("feedback_no_scroll");
			me.getBlock().show();
			me.getDialog().show();
			if ($.isFunction(options.afterShowDialog)) {
				options.afterShowDialog();
			}
		}else{
			if ($.isFunction(options.beforeHideDialog)) {
				options.beforeHideDialog();
			}
			$("html").removeClass("feedback_no_scroll");
			me.getDialog().hide();
			me.getBlock().hide();
			if ($.isFunction(options.afterHideDialog)) {
				options.afterHideDialog();
			}
		}
    }
    
    function registerEvent_tab() {
    	$("#spFeedback_tab").bind("click", function() {
    		if (me.getDialog().is(":visible")) {
    			openDialog(false);
    		}else{
    			openDialog(true);
    		}
    	});
    }
    
    function registerEvent_btn_cancel() {
    	$("#spFeedback_cancel").bind("click", function() {
    		clearInput();
    		openDialog(false);
    	});
    }
    
    function registerEvent_btn_submit() {
    	$("#spFeedback_submit").bind("click", function() {
    		if ($.isFunction(options.submitFeedback)) {
    			options.submitFeedback();
    		}else{
    			alert('please implement function submitFeedback via options');
    		}
    	});
    }
    
    function registerEvent() {
    	registerEvent_tab();
    	registerEvent_btn_cancel();
    	registerEvent_btn_submit();
    }
    
}