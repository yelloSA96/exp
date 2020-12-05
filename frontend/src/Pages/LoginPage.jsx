import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';


class LoginPage extends Component {
	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this)
		this.state = {
			activeUser: null,
		}
	}

	async handleSubmit(event) {
		event.preventDefault();
		console.log(event.target.email.value)
		fetch('/api/login', {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				"email": event.target.email.value,
				"password": event.target.password.value,
			})
		})
			.then(response => response.json())
			.then(token => {
				console.log(token);
				const userFromToken = JSON.parse(atob(JSON.stringify(token).split('.')[1]));
				console.log(userFromToken);
				this.setState({activeUser: userFromToken});
				sessionStorage.setItem("token", "Bearer " + token.jwtToken);
			});

	}

	render() {
		if (this.state.activeUser) {
			return (
				<Redirect to={{
					pathname: '/home',
					state: { activeUser: this.state.activeUser }
				}}
				/>
			);
		} else {
			return (
				<form onSubmit={this.handleSubmit}>
					<input type="text" name="email" placeholder="Email" />
					<input type="password" name="password" placeholder="Password" />
					<button>Login</button>
				</form>
			);
		}
	}
}

export default LoginPage;