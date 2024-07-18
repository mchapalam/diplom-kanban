export type tJob = {
	id: string,
	title: string,
	info: string,
	dateCreate: string,
	dateUpdate: string,
	type: 'New' | 'Done' | 'ToDo' | 'InProgress' | 'Testing',
	expectedTime: number,
	actualTime: number,
	projectId: tProject['id'],
}

export type tProject = {
	id: string,
	title: string,
}

export type tUser = {
	id: string,
	token: string,
	refreshToken: string,
	username: string,
	email: string,
	roles: string[]
}

export type tUserState = tUser | null
export type tJobsState = tJob[] | null
export type tProjectState = tProject[] | null
export type tUsersState = Array<{
	id: string,
	firstName: string,
	lastName: string,
	username: string,
	wages: number,
	jobs: [
		{
			id: tJob['id'],
			title: tJob['title'],
			dateCreate: tJob['dateCreate'],
			dateUpdate: tJob['dateUpdate'],
			type: tJob['type'],
			expectedTime: number,
			actualTime: number,
			projectId: tProject['id']
		}
	]
}> | null