import React, { Component } from "react";

import ProjectService from "../Service/project.service";


export default class BoardAdmin extends Component {
	constructor(props) {
		super(props)
		this.state = {
			projects: [],
			message: null,
			successful:false
		}
		this.refreshProjects = this.refreshProjects.bind(this)
		this.showInfo = this.showInfo.bind(this)
		this.updateProject = this.updateProject.bind(this)
		this.deleteProject = this.deleteProject.bind(this)
	}

	componentDidMount() {
		this.refreshProjects();
	}

	refreshProjects() {
		ProjectService.getProjects()
		.then(
				response => {
					console.log(response);
					this.setState({ projects: response.data })
				}
		)
	}

	showInfo(id) {
		console.log("info of " + id)
		this.props.history.push(`/showInfo/${id}`)
	}

	addActivity(id) {
		console.log("info of " + id)
		this.props.history.push(`/addActivity/${id}`)
	}
	
	updateProject(id) {
		console.log("update " + id)
		this.props.history.push(`/updateProject/${id}`)
	}
	
	deleteProject(id) {
		console.log("delete " + id)
		ProjectService.deleteProject(id)
		.then( response => {
			this.setState({
				message: response.data.message,
				successful : true
			});
			setTimeout(window.location.reload(false), 2000);
		}, error => {
			const resMessage = 
				(error.response && error.response.data && error.response.data.message) ||
				error.message ||
				error.toString();

			this.setState({
				message: resMessage,
				successful : false
			});
		});
	}

	render() {
		return (
				<div className="container">
				<header className="jumbotron">
				<h1>All projects</h1>
				</header>
				<div className="container">
				<table className="table">
				<thead>
				<tr>
				<th>Name</th>
				<th>Budget</th>
				<th>Begins</th>
				<th>Ends</th>
				<th>Info</th>
				<th>Add Activity</th>
				<th>Delete</th>
				<th>Update</th>
				</tr>
				</thead>
				<tbody>
				{this.state.projects.map(
						project =>
						<tr key={project.id}>
						<td>{project.name}</td>
						<td>{project.budget}</td>
						<td>{project.beginning}</td>
						<td>{project.end}</td>
						<td><button className="btn btn-info" onClick={() => this.showInfo(project.id)}>Show Info</button></td>
						<td><button className="btn btn-success" onClick={() => this.addActivity(project.id)}>Add Activity</button></td>
						<td><button className="btn btn-danger" onClick={() => this.deleteProject(project.id)}>Delete</button></td>
						<td><button className="btn btn-warning" onClick={() => this.updateProject(project.id)}>Update</button></td>
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