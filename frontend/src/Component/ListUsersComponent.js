import React, { Component } from 'react';
import UserDataService from '../Service/UserDataService';


class ListUsersComponent extends Component {
	constructor(props) {
		super(props)
		this.state = {
			users: [],
			message: null
		}
		this.refreshUsers = this.refreshUsers.bind(this)
		this.deleteUserClicked = this.deleteUserClicked.bind(this)
	}

	componentDidMount() {
		this.refreshUsers();
	}

	refreshUsers() {
		console.log("refresh users");
		UserDataService.retrieveAllUsers()
			.then(
				response => {
					console.log(response);
					this.setState({ users: response.data })
				}
			)
	}
	
	deleteUserClicked(id) {
		UserDataService.deleteUser(id)
			.then(
				response => {
					this.setState({ message: `Delete of user ${id} Successful` })
					this.refreshUsers()
				}
			)
	}
	
	render() {
		return (
			<div className="container">
				<h3>All Users</h3>
				{this.state.message && <div className="alert alert-success">{this.state.message}</div>}
				<div className="container">
					<table className="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							{
								this.state.users.map(
									user =>
										<tr key={user.id}>
											<td>{user.id}</td>
											<td>{user.name}</td>
											<td><button className="btn btn-warning" 
												onClick={() => this.deleteUserClicked(user.id)}>
												Delete</button></td>
										</tr>
								)
							}
						</tbody>
					</table>
				</div>
			</div>
		)
	}
}

export default ListUsersComponent