import React, { Component } from "react";

import AuthService from "../Service/auth.service";


export default class ConfirmAccount extends Component {
	constructor(props) {
		super(props);

		this.state = {
				token: this.props.match.params.token,
				message: ""
		};
	}


	componentDidMount() {
		AuthService.confirmAccount(this.state.token)
		.then(() => {
			this.props.history.push("/login");
			window.location.reload();
		},
		error => {
			const resMessage = (error.response &&
					error.response.data &&
					error.response.data.message) ||
					error.message ||
					error.toString();
			this.setState({
				message: resMessage
			});
		});
	}

	render() {
		return (
			<div className="col-md-12">
				<div className="card card-container">
					{this.state.message && (
						<div className="form-group">
							<div className="alert alert-danger" role="alert">
								{this.state.message}
							</div>
						</div> )}
				</div>
			</div>
		);
	}
}