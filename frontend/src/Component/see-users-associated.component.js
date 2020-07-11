import React, { Component } from "react";

import ProjectService from "../Service/project.service";


export default class UsersAssociated extends Component {
    
	constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            activity: null,
            users: []
        }
        this.getActivity = this.getActivity.bind(this)
        this.getUsers = this.getUsers.bind(this)
        this.addAssociatedUser = this.addAssociatedUser.bind(this)
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
		ProjectService.getUsersAssociated(id).then(
			response => {
				this.setState({
					users: response.data
			 	});
			});
	}
    
    addAssociatedUser(id){
    	this.props.history.push(`/associateUser/${id}`)
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
	                        </tr>
	             )}
	            </tbody>
	        </table>
	        { this.state.activity && <td><button className="btn btn-success" 
            	onClick={() => this.addAssociatedUser(this.state.activity.id)}>Associate User</button></td> }                            
	    </div>
	</div>
    );
  }

}

