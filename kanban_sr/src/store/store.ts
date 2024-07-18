import {create} from "zustand";
import {tJob, tJobsState, tProjectState, tUserState} from "../utils/types.ts";
import {jobTypes} from "../utils/enum.ts";

interface iStore {
	user: tUserState

	jobs: tJobsState

	projects: tProjectState

	setJobs: (jobs: tJobsState) => void
	addNewJob: (job: tJob) => void
	deleteJob: (jobId: tJob['id']) => void
	updateJob: (jobId: tJob['id'], properties: Partial<tJob>) => void
	changeTableJob: (id: tJob['id'], type: number) => void
	setUser: (user: tUserState) => void
	setProjects: (projects: tProjectState) => void

	resetStore: () => void
}

export const store = create<iStore>((set) => ({
	user: null,

	jobs: null,

	projects: null,

	setJobs: (jobs) => set({jobs}),
	addNewJob: (job) => {
		set(state => {
			if (!state.jobs) return state;
			// const newJob: tJob = {
			// 	id: crypto.randomUUID(),
			// 	title: title,
			// 	dateCreate: new Date().toISOString(),
			// 	dateUpdate: new Date().toISOString(),
			// 	info: info,
			// 	type: 'New',
			// 	expectedTime: 0,
			// 	actualTime: 0,
			// 	projectId: projectId,
			// }
			const newJobs = [...state.jobs, job]
			return {jobs: newJobs}
		})
	},
	deleteJob: (jobId) => {
		set(state => {
			if (!state.jobs) return state
			return {jobs: [...state.jobs].filter(job => job.id !== jobId)}
		})
	},
	updateJob: (jobId, properties) => {
		set(state => {
			if (!state.jobs) return state;
      const newJobs = state.jobs.map(job => {
        if (job.id!== jobId) return job
        return {...job,...properties}
      })
      return {jobs: newJobs}
		})
	},
	changeTableJob: (id, type) => {
		set(state => {
			if (!state.jobs) return state;
			const newJobs = state.jobs.map(job => {
				const newType = jobTypes[type] as tJob['type']
				if (job.id === id) job.type = newType
				console.log(newType)
				return job
			})
			return {jobs: newJobs}
		})
	},

	setProjects: (projects) => set({projects}),

	setUser: (user) => set({user}),

	resetStore: () => set({user: null, jobs: null, projects: null})
}))
