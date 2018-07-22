import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery'; 
import {TextBox} from './TextBox';

class Menu extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			merchantName: "",
			street: "",
			city: "",
			state: "",
			country: "",
			zipCode: ""
		};
		this.handleClick = this.handleClick.bind(this);
		this.handleChange = this.handleChange.bind(this);
	}

	handleClick() {
		$.ajax({
	      type: 'POST',
	      url: 'merchant/register',
	      contentType: 'application/json',
	      data: JSON.stringify({
	    	  name: this.state.merchantName,
	    	  address: {
	    		  street: this.state.street,
		    	  city: this.state.city,
		    	  state: this.state.state,
		    	  country: this.state.country,
		    	  zipCode: this.state.zipCode,
	    	  },  
    	  }),
	      success: function(response) {
	        console.log('success', response);
	      },
	      error: function(response) {
	         console.log('error', response);
	      }
	    });
	}

	handleChange(e) {
		this.setState({[e.target.name]: e.target.value});
	}

	render() {
		return (
			<div>
				<TextBox handleChange={this.handleChange} textName="Merchant Name" stateName="merchantName" value={this.state.merchantName}/>
				<TextBox handleChange={this.handleChange} textName="Street" stateName="street" value={this.state.street}/>
				<TextBox handleChange={this.handleChange} textName="City" stateName="city" value={this.state.city}/>
				<TextBox handleChange={this.handleChange} textName="State" stateName="state" value={this.state.state}/>
				<TextBox handleChange={this.handleChange} textName="Country" stateName="country" value={this.state.country}/>
				<TextBox handleChange={this.handleChange} textName="Zip Code" stateName="zipCode" value={this.state.zipCode}/>
				<button onClick={this.handleClick}>submit</button>
			</div>
		)
	}
}

ReactDOM.render(
	<Menu />,
	document.getElementById('react')
)