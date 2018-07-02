import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery'; 

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {value: ""};
	}

	componentDidMount() {
		$.ajax({
	      type: 'GET',
	      url: 'merchant/test',
	      contentType: 'application/json',
	      dataType: 'json', //specify jsonp
	      success: function(data) {
	    	  console.log(data);
	        this.setState({value: data});
	      }.bind(this),
	      error: function(e) {
	         console.log('error', e);
	      }
	    });
	}

	render() {
		return (
			<div>{this.state.value}</div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)