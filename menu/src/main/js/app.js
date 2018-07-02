import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery'; 

class Menu extends React.Component {

	constructor(props) {
		super(props);
		this.state = {name: ""};
		this.handleClick = this.handleClick.bind(this);
		this.handleChange = this.handleChange.bind(this);
	}

	handleClick() {
		$.ajax({
	      type: 'POST',
	      url: 'merchant/register',
	      contentType: 'application/json',
	      // dataType: 'json',
	      data: JSON.stringify({
	    	  name: this.state.name
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
		this.setState({name: e.target.value});
	}

	render() {
		return (
			<div>
				merchant name: <input value={this.state.name} onChange={this.handleChange}/>
				<button onClick={this.handleClick}>submit</button>
			</div>
		)
	}
}

ReactDOM.render(
	<Menu />,
	document.getElementById('react')
)