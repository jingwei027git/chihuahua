var RbSearchableSelectBox = React.createClass({
	
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
		<div className="rb-field">
			{/* TEXT-FIELD */}
			<input type="text" name={this.props.name} ref="inputText" className={classesTextField}
			 value={this.state.textValue}
			 placeholder={this.props.placeholder}
			 onBlur={this.handleBlur}
			 onClick={this.handleClick}
			 onChange={this.handleChange}
			 maxLength={this.props.maxLength}
			/>
			
			{/* ICON */}
			{this.props.icon.toLowerCase() !== ''
			?	<span className={classesIcon + ' ' + this.props.icon.toLowerCase()}></span>
			:	<span></span>
			}
			
			{/* ICON : MANDATORY */}
			{this.props.require.toLowerCase() === 'true'
			?	<span className={classesRequire}>
					<span className="required-flag-icon" data-html="true" data-tooltip={this.props.requireMsg}>
						<i className="fa fa-pencil rb-icon-mandatory"></i>
					</span>
				</span>
			:	<span></span>
			}
		</div>
		);
	}
});
