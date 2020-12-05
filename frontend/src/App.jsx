import React, { Component } from 'react';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import LoginPage from './Pages/LoginPage.jsx';
import HomePage from './Pages/HomePage.jsx';
import RegistrationPage from './Pages/RegistrationPage.jsx';
import ErrorPage from './Pages/ErrorPage.jsx';
import './App.css';

import Navbar from './Components/Navbar';
import Opportunity from './Opportunity/Opportunity';
import viewTrainees from './Schedule/viewTrainees';
import viewSchedule from './Schedule/viewSchedule';
import addSelfLearning from './Schedule/addSelfLearning';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.css';

import removeSkills from './Schedule/removeSkills';

class App extends Component {

	constructor(props) {
		super(props);
		this.state = {
		}
	}

	render() {
		return (
			
			<Router>
				<Switch>
					<Route path='/' exact={true} component={LoginPage} />
					<Route path='/home' exact={true} component={HomePage} />
					<Route path='/register' exact={true} component={RegistrationPage} />
					<Route path='/error' exact={true} component={ErrorPage} />
          <Route path="/viewTrainees" exact={true} component={viewTrainees} />
          <Route path="/schedule/:id" component={viewSchedule} />
          <Route path="/addSelfLearning" component={addSelfLearning} />
          <Route path="/opportunity" component={Opportunity} />
          <Route path="/skills" component={removeSkills} />

        </Switch>
      </Router>
    );
  }
}


export default App;