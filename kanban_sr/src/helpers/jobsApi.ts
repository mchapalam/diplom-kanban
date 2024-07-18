import {tJob, tProject} from "../utils/types.ts";
import axios from "axios";
import {API_HOST} from "../utils/constants.ts";
import LocalStorage from "./LocalStorage.ts";

const authHeader = () => ({headers: {'Authorization': `Bearer ${LocalStorage.getToken()}`}})

const fetchJobsByProjectId = async (projectId: tProject['id']) => {
	try {
		const response = await axios.get<tJob[]>(API_HOST+`/api/v1/app/job/id_project=${projectId}`, authHeader())
		return response.data
	} catch (e) {
		console.log(e)
		return null
	}
}

const changeType = async (jobId: tJob['id'], step: 'next' | 'back') => {
	try {
		await axios.get<tJob>(`${API_HOST}/api/v1/app/job/${step}_stage_job=${jobId}`, authHeader())
	} catch (e) {
		console.log(e)
	}
}

const createJob = async (title: tJob['title'], info: tJob['info'], projectId: tProject['id']) => {
	try {
		const body = {title, info, projectId, expectedTime: 0}
		const response = await axios.post<tJob>(`${API_HOST}/api/v1/app/job`, body, authHeader())
		return response.data
	} catch (e) {
		console.log(e)
		return null
	}
}

export default {fetchJobsByProjectId, changeType, createJob}