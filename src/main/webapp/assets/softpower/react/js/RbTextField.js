var RbTextField = React.createClass({displayName: "RbTextField",
	
	getInitialState: function() {
		return { textValue: '', errMsg: '' };
	},
	getDefaultProps: function() {
		return { icon: '', require: 'false', placeholder: '', autoTrim: 'true', toUpperCase: 'false', toLowerCase: 'false', maxLength: '-1' }
	},
	
	componentWillMount: function() {
		
	},
	
	doValidate: function(value) {
		if (typeof this.props.validate === 'function') {
			var errMsg = this.props.validate(value);
			if (errMsg == null || errMsg === '') {
				this.setState({errMsg: ''});
			} else {
				this.setState({errMsg: errMsg});
			}
		}
	},
	
	handleBlur: function(event) {
		if ('true' === this.props.autoTrim) {
			this.setState({textValue: this.refs.inputText.getDOMNode().value.trim()});
			this.doValidate(this.refs.inputText.getDOMNode().value.trim());
		}
	},
	handleChange: function(event) {
		if ('true' === this.props.toUpperCase) {
			this.setState({textValue: event.target.value.toUpperCase()});
		} else if ('true' === this.props.toLowerCase) {
			this.setState({textValue: event.target.value.toLowerCase()});
		} else {
			this.setState({textValue: event.target.value});
		}
		this.doValidate(this.refs.inputText.getDOMNode().value);
	},
	handleClick: function(event) {
	},
	
	render: function() {
		var cx = React.addons.classSet;
		var classesTextField = cx({
			'text-field': (this.state.errMsg === ''),
			'text-field-error': (this.state.errMsg !== ''),
			'icon-area': (this.props.icon !== '')
		});
		var classesRequire = cx({
			'field-required-flag': (this.props.require.toLowerCase() === 'true')
		});
		var classesIcon = cx({
			'field-style-hook': true,
			'rb-icon': (this.state.errMsg === ''),
			'rb-icon-error': (this.state.errMsg !== '')
		});
		
		return (
		React.createElement("div", {className: "rb-field"}, 
			/* TEXT-FIELD */
			React.createElement("input", {type: "text", name: this.props.name, ref: "inputText", className: classesTextField, 
			 value: this.state.textValue, 
			 placeholder: this.props.placeholder, 
			 onBlur: this.handleBlur, 
			 onClick: this.handleClick, 
			 onChange: this.handleChange, 
			 maxLength: this.props.maxLength}
			), 
			
			/* ICON */
			this.props.icon.toLowerCase() !== ''
			?	React.createElement("span", {className: classesIcon + ' ' + this.props.icon.toLowerCase()})
			:	React.createElement("span", null), 
			
			
			/* ICON : MANDATORY */
			this.props.require.toLowerCase() === 'true'
			?	React.createElement("span", {className: classesRequire}, 
					React.createElement("span", {className: "required-flag-icon", "data-html": "true", "data-tooltip": this.props.requireMsg}, 
						React.createElement("i", {className: "fa fa-pencil rb-icon-mandatory"})
					)
				)
			:	React.createElement("span", null)
			
		)
		);
	}
});
