import {FC} from 'react'
import styles from './Header.module.scss'
import {Link, useNavigate} from 'react-router-dom'
import {LocalStorage} from "../../helpers";
import {store} from "../../store/store.ts";

const Header: FC<{isLoading: boolean}> = ({isLoading}) => {
	const {user, projects, resetStore} = store(state => state)
	const navigate = useNavigate()

	const newProject = () => {
		alert('New Project')
	}

	const logout = () => {
		if (!user) return;
		LocalStorage.removeToken()
		resetStore()
		navigate('/')
	}

	return (
		<header className={styles.header}>
			<Link className={styles.logo} to={'/'}>
				<img className={styles.logoImg} src="/img.svg" alt="logo"/>
				<div className={styles.logoLink}>Di-пломчик</div>
				<span className={styles.user}>Пользователь: {user?.username || 'Гость'}</span>
			</Link>
			{
				user && !isLoading && (
					<ul className={styles.menu}>
						<li className={`${styles.btn} ${styles.menu__item} ${styles.dropdown}`}>
							<span className={styles.span}>Список проектов</span>
							<ul className={styles.dropdown__list}>
								{projects && projects.length > 0 ? projects.map(project => (
									<li key={project.id} className={styles.dropdown__elem}>
										<Link to={`project/${project.id}`} className={styles.dropdown__link}>{project.title}</Link>
									</li>
								)) : <div className={styles.projectEmpty}>Пусто</div>}
							<div className={styles.newProject} onClick={newProject}>Новый проект</div>
							</ul>
						</li>
						<li className={styles.menu__item}>
							<Link to={'/'} className={styles.menu__link}>Главная</Link>
						</li>
						<li className={styles.menu__item}>
							<Link to={'/stats'} className={styles.menu__link}>Графики</Link>
						</li>
						<li className={styles.menu__item} onClick={logout}>
							<a href={'#'} className={`${styles.menu__link} ${styles.logout}`}>
								<svg width="800px" height="800px" viewBox="0 0 24 24" fill="transparent" stroke="#000" xmlns="http://www.w3.org/2000/svg">
									<path
										d="M3 21.0001L14 21V5.98924C14 4.6252 14 3.94318 13.7187 3.47045C13.472 3.05596 13.0838 2.74457 12.6257 2.59368C12.1032 2.42159 11.4374 2.56954 10.1058 2.86544L7.50582 3.44322C6.6117 3.64191 6.16464 3.74126 5.83093 3.98167C5.53658 4.19373 5.30545 4.48186 5.1623 4.8152C5 5.19312 5 5.65108 5 6.56702V21.0001M13.994 5.00007H15.8C16.9201 5.00007 17.4802 5.00007 17.908 5.21805C18.2843 5.4098 18.5903 5.71576 18.782 6.09209C19 6.51991 19 7.07996 19 8.20007V21.0001H21M11 12.0001H11.01"
										strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"
									/>
								</svg>
							</a>
						</li>
					</ul>
				)
			}
		</header>
	)
}

export default Header