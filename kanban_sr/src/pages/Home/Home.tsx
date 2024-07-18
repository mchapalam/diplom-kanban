import {FC} from 'react';
import {store} from "../../store/store.ts";
import {Form, WarningMessage} from "../../components";
import styles from "./Home.module.scss"

const Home:FC<{isLoading: boolean}> = ({isLoading}) => {
	const user = store(state => state.user)

	if (isLoading) return <WarningMessage message={'...'} text={'Загрузка'}/>

	if (!user) return (
		<div className={styles.Home}>
			<Form/>
		</div>
	)

	return (
		<div className={styles.Home}>
			<h2 className={styles.textHead}>Управление проектами в IT-индустрии: лучшие практики и инновационные подходы</h2>
			<p className={styles.text}> Управление операционной деятельностью ИТ-разработчика представляет собой комплексный процесс, включающий планирование, контроль и оптимизацию задач и проектов. В условиях современного рынка, характеризующегося высокой конкуренцией и быстрым развитием технологий, эффективное управление проектами становится ключевым фактором успеха для ИТ-компаний. В данной дипломной работе рассматривается проект создания системы управления задачами и проектами, разработанной на платформе Java Spring с использованием современных технологий, таких как Spring Security, RESTful API, JWT, Redis, PostgreSQL и WebFlux. </p>
		</div>
	);
};

export default Home;
