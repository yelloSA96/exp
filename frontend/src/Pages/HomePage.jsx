import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Redirect } from 'react-router-dom';


class HomePage extends Component {
	constructor(props) {
		super(props);
		this.state = {
			activeUser: this.props.location.state ? this.props.location.state.activeUser : { firstName: '', userID: -1, role: 'ROLE_ANONYMOUS' },
		}
	}

	render() {
		if (this.state.activeUser.role === 'ROLE_ADMIN') {
			//------------- 			  -----------------
			//------------- ADMIN SECTION -----------------
			//------------- 			  -----------------
			// Route to admin homepage here
			return (
				<div>
					<div> Welcome home {this.state.activeUser.firstName} </div>
					<Link to={{
						pathname: '/register',
						state: {
							activeUser: this.state.activeUser
						}
					}}> Register </Link>
				</div>
			);
		} else if (this.state.activeUser.role === 'ROLE_CONSULTANT') {
			//------------- 			  -----------------
			//---------- CONSULTANT SECTION ---------------
			//------------- 			  -----------------
			// Route to consultant homepage here
			return (
				<div>
					<div> Welcome home {this.state.activeUser.firstName} </div>
				</div>
			);
		} else if (this.state.activeUser.role === 'ROLE_MANAGER') {
			//------------- 			  -----------------
			//------------- MANAGER SECTION ---------------
			//------------- 			  -----------------
			// Route to manager homepage here
			return (
				<div>
					<div> Welcome home {this.state.activeUser.firstName} </div>
				</div>
			);
		} else {
			return (
				<Redirect to={{
					pathname: '/error',
					state: { activeUser: this.state.activeUser,
							 message: 'Oop! Something went wrong, ya dingus.' }
				}}
				/>
			);
		}
	}
}

export default HomePage;