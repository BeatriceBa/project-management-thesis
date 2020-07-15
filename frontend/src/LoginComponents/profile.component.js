import React, { Component } from "react";
import AuthService from "../Service/auth.service";

export default class Profile extends Component {
	constructor(props) {
		super(props);

		this.state = {
				currentUser: AuthService.getCurrentUser()
		};
	}

	render() {
		const { currentUser } = this.state;
		return (
				<div className="container">
					<header className="jumbotron">
					<h3>
						<strong> Welcome {currentUser.name} {currentUser.surname} </strong>
					</h3>
				</header>
				<p>
					<strong>Daily Hours:</strong>{" "}
					{currentUser.dailyHours}
				</p>
				<p>
					<strong>Price per hour:</strong>{" "}
					{currentUser.pricePerHour}
					</p>
				<p>
					<strong>Id:</strong>{" "}
					{currentUser.id}
				</p>
				<p>
					<strong>Email:</strong>{" "}
					{currentUser.mail}
				</p>
					<strong>Authorities:</strong>
				<ul>
					{currentUser.roles &&
						currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
				</ul>
				</div>
		);
	}
}