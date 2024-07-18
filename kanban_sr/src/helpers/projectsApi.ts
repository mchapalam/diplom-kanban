import axios from "axios";
import {API_HOST} from "../utils/constants.ts";
import {tProject, tProjectState} from "../utils/types.ts";
import LocalStorage from "./LocalStorage.ts";


const authHeader = () => ({headers: {'Authorization': `Bearer ${LocalStorage.getToken()}`}})

const fetchProjectsByUserId = async (): Promise<tProjectState> => {
	try {
		const response = await axios.get<tProject[]>(API_HOST+'/api/v1/app/project', authHeader())
		console.log(response.data)
		return response.data
	} catch (e) {
		console.log(e)
		return null
	}
}

// const fetchProjectById = async (projectId: tProject['id']) => {
// 	try {
// 		const response = await axios.get<tProject[]>(API_HOST+'/api/v1/app/project',
// 			{
// 				headers: {
// 					'Authorization': `Bearer ${LocalStorage.getToken()}`
// 				}
// 			})
// 		console.log(response.data)
// 		return response.data
// 	} catch (e) {
// 		console.log(e)
//     return null
// 	}
// }

export default {fetchProjectsByUserId}