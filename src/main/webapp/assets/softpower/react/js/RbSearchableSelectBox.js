var RbSearchableSelectBox = React.createClass({displayName: "RbSearchableSelectBox",
	
	getInitialState: function() {
		return { selectedValue: '' };
	},
	getDefaultProps: function() {
		return { require: 'false', placeholder: '' }
	},
	
	componentWillMount: function() {
		
	},
	
	doValidate: function(value) {
		
	},
	
	handleBlur: function(event) {
		
	},
	handleChange: function(event) {
		
	},
	handleClick: function(event) {
		
	},
	
	render: function() {
		var cx = React.addons.classSet;
		
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
