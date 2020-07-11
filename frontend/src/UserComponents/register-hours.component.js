import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import UserService from "../Service/user.service";
import AuthService from "../Service/auth.service";

const required = value => {
	if (!value) {
		return (
				<div className="alert alert-danger" role="alert">
				This field is required!
				</div>
		);
	}
};

const vhours = value => {
	if (value <= 0 || value >= 9) {
		return (
				<div className="alert alert-danger" role="alert">
				Hours bust be between 0 and 8.
				</div>
		);
	}
};

export default class AddActivity extends Component {
	constructor(props) {
		super(props);
		this.handleAddHours = this.handleAddHours.bind(this);
		this.onChangeHours = this.onChangeHours.bind(this);

		this.state = {
				hours: "",
				successful: false,
				message: "",
				activity_id: this.props.match.params.id
		};
	}

	onChangeHours(e) {
		this.setState({
			hours: e.target.value
		});
	}

	handleAddHours(e) {
		e.preventDefault();

		this.setState({
			message: "",
			successful: false
		});

		this.form.validateAll();
		const user_id = AuthService.getCurrentUser().id;
		if (this.checkBtn.context._errors.length === 0) {
			console.log(this.state.activity_id)
			UserService.registerHours(
					user_id,
					this.state.activity_id,
					this.state.hours)
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
		return (
				<div className="col-md-12">
				<div className="card card-container">

				<Form
				onSubmit={this.handleAddHours}
				ref = {c => {
					this.form = c;
				}}
				>
				{!this.state.successful && (
						<div>

						<div className="form-group">
						<label htmlFor="budget">Hours</label>
						<Input
						type="text"
							className="form-control"
							name="hours"
							pattern="[0-9]*" 
							inputmode="numeric"	
							value={this.state.hours}
						onChange={this.onChangeHours}
						validations={[required, vhours]}
						/>
						</div>

						<div className="form-group">
						<button className="btn btn-primary btn-block">Register Hours</button>
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