import {FC, useEffect} from "react";
import { Route, Routes } from 'react-router-dom'
import {Header, WarningMessage} from '../components'
import {Home, Kanban, Stats} from "../pages";
import {authApi, projectsApi} from "../helpers";
import {store} from "../store/store.ts";
import {useQuery} from "@tanstack/react-query";
import {ReactNotifications} from "react-notifications-component";
import 'react-notifications-component/dist/theme.css'
import './App.scss'

const App:FC = () => {
	const {setUser, setProjects} = store(state => state)

	const query = useQuery({
		queryKey: ['user', 'projects'],
		queryFn: async () => {
			console.log('query')
			const userResponse = await authApi.user()
			return {
				user: userResponse,
				projects: userResponse ? await projectsApi.fetchProjectsByUserId() : null
			}
		},
	})

	useEffect(() => {
		if (query.data) {
			setUser(query.data.user)
			setProjects(query.data.projects)
		}
	}, [query.data]);

  return (
    <div className={'App'}>
		<ReactNotifications/>
		<Header isLoading={query.isLoading}/>
		<Routes>
			<Route path="/" element={<Home isLoading={query.isLoading}/>} />
			<Route path="/project/:id" element={<Kanban isLoading={query.isLoading}/>} />
			{/*<Route path="/stats" element={<WarningMessage message={'404'} text={'Такой страницы не существует, когда-нибудь она появится...'}/>} />*/}
			<Route path="/stats" element={<Stats/>} />
			<Route path="*" element={<WarningMessage message={'404'} text={'Такой страницы не существует'}/>} />
		</Routes>
    </div>
  )
}

export default App
