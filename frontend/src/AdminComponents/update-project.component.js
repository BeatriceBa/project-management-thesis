import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import ProjectService from "../Service/project.service";


const required = value => {
	if (!value) {
		return (
				<div className="alert alert-danger" role="alert">
				This field is required!
				</div>
		);
	}
};



export default class UpdateProject extends Component {
	constructor(props) {
		super(props);
		this.handleUpdateProject = this.handleUpdateProject.bind(this);
		this.onChangeName = this.onChangeName.bind(this);
		this.onChangeBudget = this.onChangeBudget.bind(this);
		this.onChangeBeginning = this.onChangeBeginning.bind(this);
		this.onChangeEnd = this.onChangeEnd.bind(this);
		this.getProject = this.getProject.bind(this);
		
		this.state = {
				name: "",
				budget: "",
				beginning: "",
				end: "",
				successful: false,
				message: "",
				project: undefined,
				id: this.props.match.params.id
		};
	}
	
	componentDidMount() {
		this.getProject();
	}
	
	getProject() {
		ProjectService.getProject(this.state.id)
		.then( response =>{
				this.setState({ project: response.data });
			});
	}

	onChangeName(e) {
		this.setState({
			name: e.target.value
		});
	}
	onChangeBudget(e) {
		this.setState({
			budget: e.target.value
		});
	}
	onChangeBeginning(e) {
		this.setState({
			beginning: e.target.value
		});
	}
	onChangeEnd(e) {
		this.setState({
			end: e.target.value
		});
	}

	handleUpdateProject(e) {
		e.preventDefault();

		this.setState({
			message: "",
			successful: false
		});

		this.form.validateAll();

		if (this.checkBtn.context._errors.length === 0) {
			ProjectService.updateProject(
					this.state.id,
					this.state.name,
					this.state.budget,
					this.state.beginning,
					this.state.end)
					.then( response => {
						this.setState({
							message: response.data.message,
							successful: true
						});
					}, error => {
						const resMessage = 
							(error.response && error.response.data && error.response.data.message) ||
							error.message ||
							error.toString();

						this.setState({
							successful: false,
							message: resMessage
						});
					}
					);
		}
	}

	render() {
		const vbudget = value => {
			if (value < this.state.project.budget) {
				return (
						<div className="alert alert-danger" role="alert">
						Value must be greater than the old project value.
						</div>
				);
			}
		};
		return (
				
				<div className="col-md-12">
				<div className="card card-container">

				<Form
				onSubmit={this.handleUpdateProject}
				ref = {c => {
					this.form = c;
				}}
				>
				{!this.state.successful && this.state.project && (
						<div>
						<div className="form-group">
						<label htmlFor="name">Project Name</label>
						<Input
						type="text"
							className="form-control"
							name="name"
							value={this.state.name}
							placeholder={this.state.project.name}
						onChange={this.onChangeName}
						validations={[required]}
						/>
						</div>

						<div className="form-group">
						<label htmlFor="budget">Budget</label>
						<Input
						type="text"
							className="form-control"
							name="budget"
							pattern="[0-9]*" 
							inputmode="numeric"	
							value={this.state.budget}
							placeholder={this.state.project.budget}
						onChange={this.onChangeBudget}
						validations={[required, vbudget]}
						/>
						</div>

						<div className="form-group">
						<label htmlFor="beginning">Beginning</label>
						<Input
						type="date"
							className="form-control"
							name="beginning"
							value={this.state.beginning}
							min={this.state.project.beginning}
						onChange={this.onChangeBeginning}
						validations={[required]}
						/>
						</div>

						<div className="form-group">
						<label htmlFor="end">End</label>
						<Input
						type="date"
							className="form-control"
								name="end"
									min={this.state.beginning}
						value={this.state.end}
						onChange={this.onChangeEnd}
						validations={[required]}
						/>
						</div>

						<div className="form-group">
						<button className="btn btn-primary btn-block">Update Project</button>
						</div>
						</div>
				)}

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
				<CheckButton
				style={{ display: "none" }}
				ref={c => {
					this.checkBtn = c;
				}}
				/>
				</Form>
				</div>
				</div>
		);
	}
}