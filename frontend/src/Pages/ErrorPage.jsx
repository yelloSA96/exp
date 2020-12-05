import React, { Component } from 'react';

class ErrorPage extends Component {

	constructor(props) {
		super(props);
		this.state = {
			message: this.props.location.state ? this.props.location.state.message : 'Error boiiiiiiii'
		}
	}

	render() {
		return (
			<div>
				{this.state.message}
			</div>
		);
	}

}

export default ErrorPage;