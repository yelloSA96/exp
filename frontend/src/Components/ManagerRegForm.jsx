import React, { Component } from 'react'

class ManagerRegForm extends Component {
	constructor(props) {
		super(props);
		this.state = {

		}
	}

	render() {
		return (
			<React.Fragment>
				<input type='text' name='email' placeholder='Email' />
				<input type='text' name='firstName' placeholder='First Name' />
				<input type='text' name='lastName' placeholder='Last Name' /> 
			</React.Fragment>
		);
	}
}

export default ManagerRegForm;