import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import ProjectService from "../Service/project.service";


export default class BoardAdmin extends Component {
	constructor(props) {
        super(props)
        this.state = {
            projects: [],
            message: null
        }
        this.refreshProjects = this.refreshProjects.bind(this)
        this.showInfo = this.showInfo.bind(this)
    }

    componentDidMount() {
        this.refreshProjects();
    }

    refreshProjects() {
    	ProjectService.getAdminBoard()
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
    
    
    render() {
    	return (
    		<div className="container">
    			<header className="jumbotron">
    			<h3>All projects</h3>
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
                    {this.state.projects.map(
                            project =>
                                <tr key={project.id}>
                                    <td>{project.name}</td>
                                    <td>{project.budget}</td>
                                    <td>{project.beginning}</td>
                                    <td>{project.end}</td>
                                    <td><button className="btn btn-success" onClick={() => this.showInfo(project.id)}>Show Info</button></td>
                                    <td><button className="btn btn-success" onClick={() => this.addActivity(project.id)}>Add Activity</button></td>
                                </tr>
                     )}
	                </tbody>
	            </table>
	        </div>
	    </div>

    );
  }
}