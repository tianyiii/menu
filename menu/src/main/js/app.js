const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {greeting: ""};
	}

	componentDidMount() {
		setState({greeting: "heeeey"})
	}

	render() {
		return (
			<div>"heeyyyy"</div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)