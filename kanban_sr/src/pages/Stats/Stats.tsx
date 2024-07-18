import {FC, useEffect, useState} from 'react';
import styles from  './Stats.module.scss';
import graphImg from '../../assets/svg/graph.svg';
import graphDiaImg from '../../assets/svg/graph-svg.svg';
import {Bar} from "react-chartjs-2";
import {
	Chart as ChartJS,
	CategoryScale,
	LinearScale,
	BarElement,
	Tooltip,
} from 'chart.js';
import {tUsersState} from "../../utils/types.ts";
import {authApi} from "../../helpers";
import {WarningMessage} from "../../components";

ChartJS.register(
	CategoryScale,
	LinearScale,
	BarElement,
	Tooltip,
);


const Stats:FC = () => {
	const [users, setUsers] = useState<tUsersState>(null);

	useEffect(() => {
		authApi.users()
			.then(r => setUsers(r))
	}, [])

	if (!users) return <WarningMessage message={'Cписок пользователей не получен'} text={'Увы...'}/>;
	const labels = users.map(user => `${user.firstName} ${user.lastName}`)
	const dataValues = users.map(user  => {
		console.log(user.jobs)
		if (!user.jobs) return 0
		const totalExpectedTime = user.jobs.reduce((sum, el) => sum + el.expectedTime, 0)
		console.log(user.wages, totalExpectedTime, user.wages * totalExpectedTime)
		return (user.wages * totalExpectedTime).toFixed(2)
	})
	const data = {
		labels,
		datasets: [
			{
				label: 'Зарплата',
				data: dataValues,
				backgroundColor: 'rgba(255, 99, 132, 0.8)',
			},
		],
	};

	return (
		<div className={styles.stats}>
			<h1 className={styles.title}>Статистика</h1>
			<div className={styles.chart}>
				<div className={styles.type}>
					<img src={graphImg} alt=""/>
				</div>
				<div className={styles.type}>
					<img src={graphImg} alt=""/>
				</div>
				<div className={styles.type}>
					<img src={graphDiaImg} alt=""/>
				</div>
			</div>
			<div className={styles.graph}>
				<Bar data={data}/>
			</div>
		</div>
	);
};

export default Stats;