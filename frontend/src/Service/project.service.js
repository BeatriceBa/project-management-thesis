import axios from "axios";
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/api/admin/";

class ProjectService {

	addProject(name, budget, beginning, end) {
		return axios.post(API_URL + "addProject", {name, budget, beginning, end}, { headers: authHeader() });
	}
	
	getAdminBoard() {
		return axios.get(API_URL + "getProjects", { headers: authHeader() });
	}
	
	addActivity(name, budget, beginning, end, projectId) {
		return axios.post(API_URL + "addActivity", {name, budget, beginning, end, projectId}, { headers: authHeader() });
	}
	
	getActivities(projectId) {
		return axios.post(API_URL + "getActivities", {projectId}, { headers: authHeader() });
	}
	
	getProject(projectId) {
		return axios.post(API_URL + "getProject", {projectId }, { headers: authHeader() });
	}
	
	associateUser(userId, activityId) {
		return axios.post(API_URL + "associateUser", {userId, activityId}, { headers: authHeader() });
	}
	
	getUsersAssociated(activityId) {
		return axios.post(API_URL + "getUsersAssociated", {activityId}, { headers: authHeader() });
	}
	
	getActivity(activityId) {
		return axios.post(API_URL + "getActivity", {activityId}, { headers: authHeader() });
	}
	
	getUsers() {
		return axios.get(API_URL + "getUsers", { headers: authHeader() });
	}
	
}

export default new ProjectService();