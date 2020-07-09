import React, { Component } from "react";

import ProjectService from "../Service/project.service";


class ShowInfo extends Component {
    
	constructor(props) {
        super(props)
        this.state = {
            project: null,
            id: this.props.match.params.id,
            activities: []
        }
        this.getProject = this.getProject.bind(this)
        this.getActivties = this.getActivities.bind(this)
    }

    componentDidMount() {
        this.getProject(this.state.id);
        this.getActivities(this.state.id);
    }

    getProject(id) {
    	ProjectService.getProject(id)
            .then(
                response => {
                    console.log(response);
                    this.setState({ project: response.data })
                }
       )
    }
    
    getActivities(id) {
		ProjectService.getActivities(id).then(
			response => {
				this.setState({
					activities: response.data
			 	});
			});
	}
    
    
    render() {
    	return (
	    <div className="container">
			<header className="jumbotron">
			{ this.state.project && <h1>Name : {this.state.project.name}</h1> }
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

export default ShowInfo 