import React from 'react';
import ReactDOM from 'react-dom';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {value: ""};
	}

	componentDidMount() {
		this.setState({value: "heeeesssy"})
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