import React, { Component } from 'react'
import AdminRegForm from './AdminRegForm.jsx'
import ConsultantRegForm from './ConsultantRegForm.jsx'
import ManagerRegForm from './ManagerRegForm.jsx'

class RegistrationForm extends Component {
	constructor(props) {
		super(props);
		console.log(props);
		this.state = {
			employeeType: props.employeeType
		}
	}

	componentWillReceiveProps(props) {
		this.setState({ employeeType: props.employeeType })
	}

	render() {
		if (this.state.employeeType === 'Admin') {
			return (
				<AdminRegForm />
			);
		} else if (this.state.employeeType === 'Consultant') {
			return (
				<ConsultantRegForm />
			);
		} else if (this.state.employeeType === 'Manager') {
			return (
				<ManagerRegForm />
			);
		} else {
			return (<div></div>);
		}
	}
}

export default RegistrationForm;