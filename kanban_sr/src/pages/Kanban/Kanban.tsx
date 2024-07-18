import {FC, useEffect, useRef, useState} from 'react';
import styles from './Kanban.module.scss'
import {store} from "../../store/store";
import {useParams} from "react-router-dom";
import {checkProjectAccess, jobsApi} from "../../helpers";
import {WarningMessage, Table, Input} from "../../components";
import {useQuery} from "@tanstack/react-query";
import {jobTypes} from "../../utils/enum.ts";
import {tJob} from "../../utils/types.ts";

const sleepDuration = 1000

const Kanban:FC<{isLoading: boolean}> = ({isLoading}) => {
	const {user, setJobs, projects}  = store(state => state)
	const params = useParams()
	const toTopButtonRef = useRef<HTMLDivElement>(null)
	const [fetching, setFetching] = useState(isLoading)

	const [search, setSearch] = useState<string>('');

	const query = useQuery({
		queryKey: ['jobs'],
		refetchInterval: 5000,
		queryFn: async () => {
			if (params.id) return await jobsApi.fetchJobsByProjectId(params.id)
		}
	})

	const fetchingHandler = () => {
		setFetching(true)
		query.refetch()
			.finally(() => setFetching(false))
	}

	useEffect(() => {
		window.onscroll = scrollHandler
		return () => {window.onscroll = null}
	}, []);

	useEffect(() => {
		fetchingHandler()
	}, [params.id]);

	useEffect(() => {
		if (query.data && !query.error) setJobs(query.data)
	}, [query.data]);

	const scrollHandler = () => {
		if (!toTopButtonRef.current) return;
		toTopButtonRef.current.hidden = window.scrollY < 100;
	}

	const jobs = store(state => state.jobs) || []

	if (isLoading || query.isLoading) return <WarningMessage message={'...'} text={`Загрузка (исскуственная задержка ${sleepDuration}мс)`}/>
	if (!checkProjectAccess(projects, params.id) || !user) return <WarningMessage message={'403'} text={'Нет доступа к проекту, увы(...'}/>;

	const parseTypesEnum = Object.keys(jobTypes).slice(Object.keys(jobTypes).length / 2) as tJob['type'][]
	const filteredJobs = jobs.filter(job  => job.title.toLowerCase().includes(search.toLowerCase()))

	return (
		<main className={`container ${styles.mainContainer}`}>
			<div className={styles.toTop} onClick={() => window.scrollTo(0, 0)} ref={toTopButtonRef} hidden={true}></div>
			{
				fetching
					? <div className={styles.loading}></div>
					: <>
						<Input type={'text'} value={search} onChange={(value) => setSearch(value)} placeholder={'Поиск определенной работы...'}/>
						{filteredJobs.length > 0
								? <>
									<div className={`${styles.tables}`}>
										{parseTypesEnum.map((table, i) =>
											<Table key={table} name={table} jobs={filteredJobs.filter(job => job.type === table)} index={i}/>)}
									</div>
								</>
								: <WarningMessage message={'Пусто'} text={'Не удалось найти работы.'} marginTop={30}/>
						}
					</>
			}


		</main>
	);
};

export default Kanban;
