import axios from 'axios'

const CHOSEN_ACTION = 'showUsers'
const URL = 'http://localhost:8080'
const INSTRUCTOR_API_URL = `${URL}/test/${CHOSEN_ACTION}`

class UserDataService {

    retrieveAllUsers() {
        return axios.get(`${INSTRUCTOR_API_URL}`)
    }
}

export default new UserDataService()