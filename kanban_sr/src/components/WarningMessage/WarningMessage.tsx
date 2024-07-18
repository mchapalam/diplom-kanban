import {FC} from "react";
import styles from "./WarningMessage.module.scss"

interface iWarningMessage {
	message?: string
	text?: string
	marginTop?: string | number
}

const WarningMessage:FC<iWarningMessage> = ({message, text, marginTop}) => {
	return (
		<div className={styles.container} style={{marginTop: marginTop}}>
			<h1 className={styles.message}>{message}</h1>
			<p className={styles.text}>{text}</p>
		</div>
	);
};

export default WarningMessage;