import axios from "axios";
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/api/user/";

class UserService {

	getMyActivities(userId) {
		return axios.post(API_URL + "getMyActivities", {userId}, { headers: authHeader() });
	}

	seeProjectInfo(activityId) {
		return axios.post(API_URL + "seeProjectInfo", {activityId}, { headers: authHeader() });
	}
	
	seeActivities(projectId) {
		return axios.post(API_URL + "seeActivities", {projectId}, { headers: authHeader() });				
	}
	
	registerHours(userId, activityId, hours) {
		return axios.post(API_URL + "registerHours", {userId, activityId, hours}, { headers: authHeader() });				
	}

}

export default new UserService();