import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./Service/auth.service";

import Login from "./LoginComponents/login.component";
import Register from "./LoginComponents/register.component";
import Home from "./LoginComponents/home.component";
import Profile from "./LoginComponents/profile.component";
import ConfirmAccount from "./LoginComponents/confirm-account.component";

import AddProject from "./AdminComponents/project.component";
import AddActivity from "./AdminComponents/add-activity.component";
import BoardAdmin from "./AdminComponents/board-admin.component";
import ShowInfo from "./AdminComponents/show-info.component";
import UsersAssociated from "./AdminComponents/see-users-associated.component";
import AssociateUser from "./AdminComponents/associate-user.component";
import UpdateProject from "./AdminComponents/update-project.component";
import UpdateActivity from "./AdminComponents/update-activity.component";

import BoardUser from "./UserComponents/board-user.component";
import RegisterHours from "./UserComponents/register-hours.component";


class App extends Component {
	constructor(props) {
		super(props);
		this.logOut = this.logOut.bind(this);
		this.handleWindowClose = this.handleWindowClose.bind(this);
		
		this.state = {
				showAdminBoard: false,
				showUserBoard : false,
				currentUser: undefined
		};
	}

	componentDidMount() {
		const user = AuthService.getCurrentUser();
		 
		if (user) {
			this.setState({
				currentUser: user,
				showUserBoard: user.roles.includes("USER"),
				showAdminBoard: user.roles.includes("ADMINISTRATOR")
			});
		}
	}

	logOut() {
		AuthService.logout();
	}
	
	handleWindowClose(){
	    this.logOut();
	}

	render() {
		const { currentUser, showAdminBoard, showUserBoard } = this.state;

		return (
				<Router>
				<div>
				<nav className="navbar navbar-expand navbar-dark bg-dark">
				<Link to={"/"} className="navbar-brand">
				Management
				</Link>

				<div className="navbar-nav mr-auto">
				<li className="nav-item">
				<Link to={"/home"} className="nav-link">
				Home
				</Link>
				</li>

				{showAdminBoard && (
						<div className="navbar-nav ml-auto">            		  
						<li className="nav-item">
						<Link to={"/admin"} className="nav-link">
						Admin Board
						</Link>
						</li>

						<li className="nav-item">
						<Link to={"/addProject"} className="nav-link">
						Add Project
						</Link>
						</li>

						</div> 
				)}

				{showUserBoard && (
						<li className="nav-item">
						<Link to={"/user"} className="nav-link">
						User
						</Link>
						</li>
				)}
				</div>

				{currentUser ? (
						<div className="navbar-nav ml-auto">
						<li className="nav-item">
						<Link to={"/profile"} className="nav-link">
						{currentUser.name}
						</Link>
						</li>
						<li className="nav-item">
						<a href="/login" className="nav-link" onClick={this.logOut}>
						LogOut
						</a>
						</li>
						</div>
				) : (
						<div className="navbar-nav ml-auto">
						<li className="nav-item">
						<Link to={"/login"} className="nav-link">
						Login
						</Link>
						</li>

						<li className="nav-item">
						<Link to={"/register"} className="nav-link">
						Sign Up
						</Link>
						</li>
						</div>
				)}
				</nav>

				<div className="container mt-3">
				<Switch>
				<Route exact path={["/", "/home"]} component={Home} />
				<Route exact path="/profile" component={Profile} />
				<Route exact path="/login" component={Login} />
				<Route exact path="/register" component={Register} />
				<Route exact path="/addProject" component={AddProject} />
				<Route exact path="/addActivity/:id" component={AddActivity} />
				<Route exact path="/confirmAccount/:token" component={ConfirmAccount} />
				<Route exact path="/updateProject/:id" component={UpdateProject} />
				<Route exact path="/updateActivity/:id" component={UpdateActivity} />
				<Route path="/showInfo/:id" component={ShowInfo} />
				<Route path="/getUsersAssociated/:id" component={UsersAssociated} />
				<Route path="/associateUser/:id" component={AssociateUser} />
				<Route path="/registerHours/:id" component={RegisterHours} />
				<Route path="/user" component={BoardUser} />
				<Route path="/admin" component={BoardAdmin} />
				</Switch>
				</div>
				</div>
				</Router>
		);
	}
}

export default App;