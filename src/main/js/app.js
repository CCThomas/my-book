'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
// end::vars[]

// tag::app[]
class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {responseData: ""};
	}

	componentDidMount() {
    		client({method: 'GET', path: '/api'}).done(response => {
    			this.setState({responseData: response});
    		});
    }

	render() {
		return (this.state.responseData);
	}
}
// end::app[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
// end::render[]