import axios from 'axios'

class AuthenticationService {
	
	executeBasicAuthenticationService(mail,password) {
		return axios.get(`localhost:8080/basicauth`,
	            { headers: { authorization: this.createBasicAuthToken(mail, password) } })
	}
	
	createBasicAuthToken(mail, password) {
        return 'Basic ' + window.btoa(mail + ":" + password)
    }
	

    registerSuccessfulLogin(username, password) {
        //let basicAuthHeader = 'Basic ' +  window.btoa(username + ":" + password)
        //console.log('registerSuccessfulLogin')
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        this.setupAxiosInterceptors(this.createBasicAuthToken(username, password))
    }
    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default AuthenticationService
