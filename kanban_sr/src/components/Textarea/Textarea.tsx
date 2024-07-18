import {FC} from 'react';
import styles from "./Textarea.module.scss";

interface iTextarea {
	value: string
	onChange: (value: string) => void
	placeholder?: string
}

const Textarea:FC<iTextarea> = ({value, onChange, placeholder}) => {
	return (
		<textarea
			className={styles.textarea}
			value={value}
			onChange={({target}) => onChange(target.value)}
			placeholder={placeholder}
		/>
	);
};

export default Textarea;