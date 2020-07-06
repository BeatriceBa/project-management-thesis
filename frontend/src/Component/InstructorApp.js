import React, { Component } from 'react';
import ListUsersComponent from './ListUsersComponent';


class InstructorApp extends Component {
    render() {
    	return (<>
        	<h1>Instructor Application</h1>
        	<ListUsersComponent/>
        	</>
    		)
    }
}

export default InstructorApp