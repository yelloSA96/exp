import React, { Component } from 'react';
import RegistrationForm from '../Components/RegistrationForm.jsx';
import { Redirect } from 'react-router-dom';


class RegistrationPage extends Component {
	constructor(props) {
		super(props);
		this.state = {
			employeeType: '--Please Select--',
			activeUser: this.props.location.state ? this.props.location.state.activeUser : { firstName: '', userID: -1, role: 'ROLE_EMPLOYEE' }
		}
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		console.log(sessionStorage.getItem("token"))
	}

	handleChange(event) {
		this.setState({ employeeType: event.target.value });
	}

	async handleSubmit(event) {
		event.preventDefault();
		fetch('/api/register/' + this.state.employeeType, {
			method: 'PUT',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'Authorization': sessionStorage.getItem("token"),
			},
			body: JSON.stringify({
				"email": event.target.email.value,
				"firstName": event.target.firstName.value,
				"lastName": event.target.lastName.value,
				"stream": event.target.lastName.value,
			}),
		})
	}

	render() {
		if (this.state.activeUser.role === 'ROLE_ADMIN') {
			return (
				<form onSubmit={this.handleSubmit}>
					<select value={this.state.employeeType} onChange={this.handleChange}>
						<option value='--Please Select--'>--Please Select--</option>
						<option value='Admin'>Admin</option>
						<option value='Consultant'>Consultant</option>
						<option value='Manager'>Manager</option>
					</select>
					<RegistrationForm employeeType={this.state.employeeType} />
					<input type="submit" value="Submit" />
				</form>
			);
		} else {
			return (
				<Redirect to={{
					pathname: '/home',
					state: { activeUser: this.state.activeUser }
				}}
				/>
			);
		}
	}

}

export default RegistrationPage;