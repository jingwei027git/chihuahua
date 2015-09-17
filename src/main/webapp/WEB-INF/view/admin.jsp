<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
	<title>Basic Example with JSX</title>
	<style type="text/css">
	body {
		font: 13px/1.3 "Lucida Grande",Verdana,sans-serif;
		color: #19272E;
	}
	.icon-phone {
		background-image: url("//fareharbor.com/static/images/sprite.png?b2e2d4643591");
	}
	.rb-field {
		position: relative;
		display: block;
	}
	.rb-field .text-field {
		padding: 0px 33px 0px 12px;
		height: 28px;
		width: 100%;
		box-sizing: border-box;
		background: #FAFBFC none repeat scroll 0% 0%;
		border-radius: 3px;
		box-shadow: 0px 2px 5px 2px #E6ECF0 inset;
		color: #152833;
		font: 13px "Lucida Grande",Verdana,sans-serif;
		border: 1px solid #9BADB8;
		
		margin: 0px;
	}
	.rb-field .text-field:focus {
		box-shadow: 0px 0px 4px 2px #E6ECF0;
		border: 1px solid #4D616D;
	}
	.rb-field .text-field-error {
		padding: 0px 33px 0px 12px;
		height: 28px;
		width: 100%;
		box-sizing: border-box;
		background: #FAFBFC none repeat scroll 0% 0%;
		border-radius: 3px;
		box-shadow: 0px 2px 5px 2px #E6ECF0 inset;
		color: #152833;
		font: 13px "Lucida Grande",Verdana,sans-serif;
		border: 1px solid #E24F1D;
		
		margin: 0px;
	}
	.rb-field .text-field-error:focus {
		box-shadow: 0px 0px 4px 2px #E6ECF0;
		border: 1px solid #E24F1D;
	}
	.rb-field .icon-area {
		padding-left: 30px;
	}
	.rb-field .field-style-hook {
		display: block;
		width: 14px;
		height: 16px;
		position: absolute;
		left: 12px;
		top: 6px;
		background-position: -378px -162px;
		
		pointer-events: none;
		z-index: 1;
	}
	.rb-field .field-required-flag {
		width: 24px;
		background: #D5DDE4 none repeat scroll 0% 0%;
		position: absolute;
		top: 1px;
		right: 1px;
		bottom: 1px;
		border-top-right-radius: 2px;
		border-bottom-right-radius: 2px;
		cursor: help;
		z-index: 2;
		
		display: block;
	}
	.rb-field .field-required-flag .required-flag-icon {
		width: 10px;
		height: 10px;
		display: block;
		background-position: -468px -162px;
		position: absolute;
		margin: auto;
		top: 0px;
		right: 0px;
		bottom: 0px;
		left: 0px;
		
		background-image: url("//fareharbor.com/static/images/sprite.png");
	}
	</style>
</head>
<body>
	<span id="txtUsername"></span>
	<p>
	<span id="txtAddress"></span>
	
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
 
</body>
<footer>
	<script src="${pageContext.request.contextPath}/assets/jquery/1.8.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/react/0.13.3/react.js"></script>
	<script src="${pageContext.request.contextPath}/assets/react/0.13.3/JSXTransformer.js"></script>
	<script type="text/jsx">
		var RbTextField = React.createClass({
			getInitialState : function() {
				return {
					errorClass : ''
				};
			},

			componentWillMount : function() {

			},

			handleClick : function() {
				
			},

			handleChange : function(e) {
				if (typeof this.props.validate === 'function') {
					var errMsg = this.props.validate(this.refs.inputText.getDOMNode().value);
					if (errMsg == null || errMsg === '') {
						this.setState({errorClass : ''});
					} else {
						this.setState({errorClass : '-error'});
					}
				}
			},
			
			render : function() {
				var sIcon = (this.props.icon || '').toLowerCase();
				var sIconClass = (sIcon === '')? '': 'icon-area';
				var bRequire = ((this.props.require || '').toLowerCase() === 'true');
				return (
					<span className="rb-field">
						<input type="text" name={this.props.name} ref="inputText" className={'text-field' + this.state.errorClass + ' ' + sIconClass} onClick={this.handleClick} onChange={this.handleChange} />
						<span className={'field-style-hook ' + sIcon}></span>
						<span className={bRequire? 'field-required-flag': ''}>
							<span className="required-flag-icon"></span>
						</span>
					</span>
				);
			}
		});
		
		function abc(val) {
			if (val.length < 1) {
				return 'must input';
			} else {
				return '';
			}
		}

		React.render(
			<RbTextField name="username" icon="icon-phone" require="true" validate={abc} />,
			$("#txtUsername")[0]
		);
		React.render(
			<RbTextField name="address" require="false" />,
			$("#txtAddress")[0]
		);
	</script>
</footer>
</html>