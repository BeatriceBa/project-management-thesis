import React, { Component } from "react";

import UserService from "../Service/user.service";
import AuthService from "../Service/auth.service";


export default class BoardUser extends Component {
	constructor(props) {
		super(props)
		this.state = {
			activities: []
		}
		this.getMyActivities = this.getMyActivities.bind(this)
	}

	componentDidMount() {
		this.getMyActivities();
	}

	getMyActivities() {
		const userId = AuthService.getCurrentUser().id;
		UserService.getMyActivities(userId)
		.then(
				response => {
					console.log(response.data);
					this.setState({
						activities: response.data 
					})
				}
		)
	}
	
	registerHours(id) {
		console.log("info of " + id)
		this.props.history.push(`/registerHours/${id}`)
	}
	
	

	render() {
		return (
				<div className="container">
					<header className="jumbotron">
						<h1>My Activities</h1>
					</header>
					<div className="container">
						<table className="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Budget</th>
									<th>Begins</th>
									<th>Ends</th>
									<th>Register Hours</th>
								</tr>
							</thead>
							<tbody>
								{this.state.activities.map(
									activity =>
										<tr key={activity.id}>
											<td>{activity.name}</td>
											<td>{activity.budget}</td>
											<td>{activity.beginning}</td>
											<td>{activity.end}</td>
											<td><button className="btn btn-success" 
												onClick={() => this.registerHours(activity.id)}>Register Hours</button></td>											
										</tr>
								)}
							</tbody>
						</table>
					</div>
				</div>
		);
	}
}