import React, { Component } from "react";

import UserService from "../Service/user.service";


export default class SeeProjectInfo extends Component {
	constructor(props) {
		super(props)
		this.state = {
			id: this.props.match.params.id
		}
		this.seeProjectInfo = this.seeProjectInfo.bind(this)
	}

	componentDidMount() {
		this.seeProjectInfo(this.state.id);
	}

	seeProjectInfo(id) {
		UserService.seeProjectInfo(id)
		.then(
				response => {
					console.log(response.data);
					this.setState({
						activities: response.data 
					})
				}
		)
	}

	render() {
		return (
				<div className="container">
					<header className="jumbotron">
						<h1>Informations about project</h1>
					</header>
					<div className="container">
						<table className="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Budget</th>
									<th>Begins</th>
									<th>Ends</th>
								</tr>
							</thead>
							<tbody>
								{this.state.activities && this.state.activities.map(
										activity =>
											<tr key={activity.id}>
												<td>{activity.name}</td>
												<td>{activity.budget}</td>
												<td>{activity.beginning}</td>
												<td>{activity.end}</td>
											</tr>
								)}
							</tbody>
						</table>
					</div>
				</div>
		);
	}
}