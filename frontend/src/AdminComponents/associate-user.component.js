import React, { Component } from "react";

import ProjectService from "../Service/project.service";


export default class AssociateUser extends Component {

	constructor(props) {
		super(props)
		this.state = {
			id: this.props.match.params.id,
			activity: null,
			users: [],
			message : "",
			successful : false
		}
		this.getActivity = this.getActivity.bind(this)
		this.getUsers = this.getUsers.bind(this)
	}

	componentDidMount() {
		this.getActivity(this.state.id);
		this.getUsers(this.state.id);
	}

	getActivity(id) {
		ProjectService.getActivity(id)
		.then(
				response => {
					console.log(response);
					this.setState({ activity: response.data })
				}
		)
	}

	getUsers(id) {
		ProjectService.getAvailableUsers().then(
				response => {
					this.setState({
						users: response.data
					});
				});
	}

	associateUser(userId, activityId) {
		ProjectService.associateUser(userId, activityId)
		.then( response => {
			this.setState({
				message: response.data.message,
				successful : true
			});
		}, error => {
			const resMessage = 
				(error.response && error.response.data && error.response.data.message) ||
				error.message ||
				error.toString();

			this.setState({
				message: resMessage,
				successful : false

			});
		}
		);
	}


	render() {
		return (
				<div className="container">
				<header className="jumbotron">
				{ this.state.activity && <h1>Users related to activity: {this.state.activity.name}</h1> }
				</header>
				<div className="container">
				<table className="table">
				<thead>
				<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Price Per Hour</th>
				<th>Mail</th>
				</tr>
				</thead>
				<tbody>
				{this.state.users && this.state.users.map(
						user =>
						<tr key={user.id}>
						<td>{user.name}</td>
						<td>{user.surname}</td>
						<td>{user.pricePerHour}</td>
						<td>{user.mail}</td>
						<td><button className="btn btn-success" 
							onClick={() => this.associateUser(user.id, this.state.activity.id)}>Associate User</button></td>	                            
							</tr>
				)}
				</tbody>
				</table>
				{this.state.message && (
						<div className="form-group">
						<div
						className={
								this.state.successful
								? "alert alert-success"
										: "alert alert-danger"
						}
						role="alert"
							>
						{this.state.message}
						</div>
						</div>
				)}
				</div>
				</div>
		);
	}

}

