import React from 'react';

export class TextBox extends React.Component {
	
	constructor(props) {
		super(props);
	}
	
	render() {
		return (
			<div>
				{this.props.textName}: <input 
					value={this.props.value}
					name={this.props.stateName}
					onChange={this.props.handleChange} />
			</div>
		)
	}
}