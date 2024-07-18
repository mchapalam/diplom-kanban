import axios, {AxiosError} from "axios";
import {tUserState, tUser, tUsersState} from "../utils/types.ts";
import LocalStorage from "./LocalStorage.ts";
import {AUTH_API_LOGIN_URL, AUTH_API_REGISTER_URL, AUTH_API_USER_URL, AUTH_API_USERS_URL} from "../utils/constants.ts";

const authHeader = () => ({headers: {'Authorization': `Bearer ${LocalStorage.getToken()}`}})

type loginReturn = (username: tUser['username'], password: string) => Promise<tUser | string>
const login: loginReturn = async (username, password) => {
	try {
		console.log('login')
		const body = {username, password}
		const response = await axios.post<tUser>(AUTH_API_LOGIN_URL, body)
		LocalStorage.setToken(response.data.token)
		return response.data
	} catch (e) {
		if (e instanceof AxiosError)
			// return e.response ? `${e.response.status} - ${e.response.statusText || 'Максим отправляет 401 ошибку в любом случае)'}` : 'Unknown error'
			return e.response ? `${e.response.status} - ${e.response.statusText || 'Ошибка)'}` : 'Unknown error'
		return String(e)
	}
}

type registerReturn = (username: string, password: string, email: string) => Promise<tUser | string>
const register: registerReturn = async (username, password, email) => {
	try {
		console.log('register')
		const body = {username, password, email}
		const response = await axios.post<tUser>(AUTH_API_REGISTER_URL, body)
		LocalStorage.setToken(response.data.token)
		return response.data
	} catch (e) {
		if (e instanceof AxiosError)
			// return e.response ? `${e.response.status} - ${e.response.statusText || 'Максим отправляет 401 ошибку в любом случае)'}` : 'Unknown error'
			return e.response ? `${e.response.status} - ${e.response.statusText || 'Ошибка)'}` : 'Unknown error'
		return String(e)
	}
}

const user = async (): Promise<tUserState> => {
	try {
		const response = await axios.get<tUser>(AUTH_API_USER_URL, authHeader())
		return response.data
	} catch (error) {
		return null
	}
}

const users = async  (): Promise<tUsersState> =>  {
	try {
		const response = await axios.get<tUsersState>(AUTH_API_USERS_URL, authHeader())
		console.log(response.data)
		return response.data
	} catch (e) {
		return null
	}
}

export default {login, register, user, users}
