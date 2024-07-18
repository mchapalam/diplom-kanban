import {FC, useEffect, useRef, useState} from 'react';
import styles from './Form.module.scss'
import Input from "../Input/Input.tsx";
import {authApi, projectsApi} from "../../helpers/";
import {store} from "../../store/store.ts";
import { motion } from 'framer-motion';
import {tUser} from "../../utils/types.ts";
import {Store} from "react-notifications-component";

type inputState = {value: string, error: string}
const defaultState: inputState = {value: '', error: ''}

const Form:FC = () => {
	const {setUser, setProjects} = store(state => state)
	const disableRef = useRef<boolean>(false)
	const [register, setRegister] = useState<boolean>(false)
	const [login, setLogin] = useState<inputState>(defaultState)
	const [password, setPassword] = useState<inputState>(defaultState)
	const [passwordConf, setPasswordConf] = useState<inputState>(defaultState)
	const [email, setEmail] = useState<inputState>(defaultState)
	const [error, setError] = useState<string | undefined>()

	const handleResponse = (response: string | tUser) => {
		console.log('handleResponse', response)
		if (typeof response === 'string')
			setError(response)
		else {
			setUser(response)
			projectsApi.fetchProjectsByUserId().then(data => {
				setProjects(data)
				Store.addNotification({
					title: "Вы вошли как: "+response.username,
					type: "success",
					insert: "top",
					container: "bottom-center",
					animationIn: ["animate__animated", "animate__fadeIn"],
					animationOut: ["animate__animated", "animate__fadeOut"],
					dismiss: {
						duration: 4000,
						onScreen: true
					}
				})
			})
		}


	}

	const clearErrors = () => {
		const inputs = [setLogin, setPassword, setPasswordConf, setEmail]
		inputs.forEach(input => input(prev => ({...prev, error: ''})))
    setError(undefined)
	}

	const emailRegex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/iu

	const validateInputs = (): boolean => {
		clearErrors()
		const loginSymbols = 2
		const passwordSymbols = 2

		const errors: Array<() => void>= []

		if (login.value.trim().length < loginSymbols)
			errors.push(() => setLogin(prev => ({...prev, error: `Логин должет быть более ${loginSymbols-1} символа(ов)`})))

		if (password.value.trim().length < passwordSymbols)
			errors.push(() => setPassword(prev => ({...prev, error: `Пароль должет быть более ${passwordSymbols-1} символа(ов)`})))

		if (register && !emailRegex.test(email.value.trim()))
			errors.push(() => setEmail(prev => ({...prev, error: `Введите правильный E-mail`})))

		if (register && email.value.trim().length < 1)
			errors.push(() => setEmail(prev => ({...prev, error: `Поле E-mail должно быть заполнено`})))

		if (register && password.value !== passwordConf.value)
			errors.push(() => setPasswordConf(prev => ({...prev, error: `Пароли не совпадают`})))

		if (errors.length > 0) {
			console.log('Поля не прошли валидацию')
			errors.forEach(error => error())
      return false;
		}
		clearErrors()
		return true;
	}

	const submitHandler = async () => {
		disableRef.current = true;
		if (validateInputs())
		register
			? handleResponse(await authApi.register(login.value, password.value, email.value))
			: handleResponse(await authApi.login(login.value, password.value))
		disableRef.current = false;
	}

	useEffect(() => {
		setError('')
		clearErrors()
	}, [register]);

	const errorAnimation = {
		animate: {
      opacity: 1
    },
    initial: {
      opacity: 0
    },
		transition: {
			duration: 0.3
		}
	}

	return (
		<form className={styles.form} onSubmit={(e) => e.preventDefault()}>
			<h2 className={styles.head}>{register ? 'Регистрация' : 'Авторизация'}</h2>
			<div className={styles.inputGroup}>
				<Input
					type={'text'}
					span={'Логин'}
					value={login.value}
					error={login.error}
					onChange={(value) => setLogin(prev => ({...prev, value}))}
				/>
				{register && (
					<Input
						type={'text'}
						span={'E-mail'}
						value={email.value}
						error={email.error}
						onChange={(value) => setEmail(prev => ({...prev, value}))}
					/>
				)}
				<Input
					type={'text'}
					span={'Пароль'}
					value={password.value}
					error={password.error}
					onChange={(value) => setPassword(prev => ({...prev, value}))}
				/>
				{register && (
					<Input
						type={'text'}
						span={'Подтверждение пароля'}
						value={passwordConf.value}
						error={passwordConf.error}
						onChange={(value) => setPasswordConf(prev => ({...prev, value}))}
					/>
				)}
				{error && <motion.div className={styles.error} {...errorAnimation}>{error}</motion.div>}
			</div>
			<div className={styles.buttonGroup}>
				<button
					className={`${styles.button} ${styles.button_active}`}
					onClick={submitHandler}
					disabled={disableRef.current}>{register ? 'Зарегистрироваться' : 'Войти'}</button>
				<button className={styles.button}
					onClick={() => setRegister(prev => !prev)}
					disabled={disableRef.current}>{register ? 'К авторизации' : 'К регистрации'}</button>
			</div>
		</form>
	);
};

export default Form;