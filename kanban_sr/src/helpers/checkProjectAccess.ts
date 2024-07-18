import {tProject} from "../utils/types.ts";


const checkProjectAccess = (projects: tProject[] | null, projectId: tProject['id'] | undefined) => {
	if (!projects || !projectId) return false;
	const found = projects.find(pr => pr.id === projectId);
	return !!found;
}

export default checkProjectAccess;