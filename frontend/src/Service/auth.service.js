import axios from "axios";

const API_URL = "http://localhost:8080/api/authentication/";

class AuthService {
	login(mail, password) {
		return axios.post(API_URL + "signin", { mail,password })
			.then(response => {
				if (response.data.token) {
			          localStorage.setItem('user', JSON.stringify(response.data));
			        }
				return response.data;
	});
  }

	logout() {
		localStorage.removeItem("user");
	}

	register(name, surname, pricePerHour, mail, password) {
		return axios.post(API_URL + "signup", { name, surname, pricePerHour, mail, password });
	}

	getCurrentUser() {
		return JSON.parse(localStorage.getItem('user'));;
	}
}

export default new AuthService();