import React, { Component } from 'react';
import ListUsersComponent from './ListUsersComponent';
import LoginComponent from './LoginComponent';

import {
	  BrowserRouter as Router,
	  Switch,
	  Route,
	  Link
	} from "react-router-dom";

class InstructorApp extends Component {
    render() {
    	return (
    		<Router>
    		<>
    			<Switch>
					<Route path="/login" exact component={LoginComponent} />
    				<Route path="/admin/showUsers" exact component={ListUsersComponent} />
    			</Switch>
    		</>
    		</Router>
    	)
    }
}

export default InstructorApp