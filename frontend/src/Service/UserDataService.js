import axios from 'axios'

const URL = `http://localhost:8080/admin`

class UserDataService {

    retrieveAllUsers() {
        return axios.get(`${URL}/showUsers`);
    }
    
    deleteUser(id) {
    	return axios.delete(`${URL}/users/${id}`);
    }
}

export default new UserDataService()