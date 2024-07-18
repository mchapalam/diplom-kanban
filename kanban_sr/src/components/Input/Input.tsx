import {FC} from "react";
import styles from "./Input.module.scss"

interface iInput {
	span?: string;
	type: 'text' | 'email' | 'password';
	placeholder?: string;
	value: string,
	error?: string
	onChange: (value: string) => void
	required?: boolean
}

const Input:FC<iInput> = ({span, type, placeholder, value, onChange, error, required}) => {

	return (
		<label className={styles.label}>
			<span className={styles.span}>{span || ''}</span>
			<input
				className={styles.input}
				type={type}
				placeholder={placeholder || ''}
				value={value}
				onChange={({target}) => onChange(target.value)}
				required={required}
			/>
			<span className={styles.error}>{error}</span>
		</label>
	)
};

export default Input;