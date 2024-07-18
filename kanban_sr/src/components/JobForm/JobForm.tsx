import {FC, useState} from "react";
import styles from "./JobForm.module.scss";
import {Input, Textarea} from "../";
import {store} from "../../store/store.ts";
import {tProject} from "../../utils/types.ts";
import { motion } from "framer-motion";
import {jobsApi} from "../../helpers";

interface iJobForm {
	projectId: tProject['id']
	onCreate: () => void
}

const JobForm:FC<iJobForm> = ({projectId, onCreate}) => {
	const addNewJob = store(state => state.addNewJob)
	const [title, setTitle] = useState<string>('')
	const [info, setInfo] = useState<string>('')

	const createNewJob = () => {
		console.log({
			title,
			info,
			dateCreate: new Date().toISOString()
		})
		jobsApi.createJob(title, info, projectId)
			.then((r) => {
				console.log('ответ', r)
				if (r) {
					addNewJob(r)
					onCreate()
				}
			})
	}

	const anim = {
		initial: {
      opacity: 0,
			scaleY: .9,
    },
    animate: {
      opacity: 1,
			scaleY: 1,
      transition: {
        duration: 0.2
      }
    }
	}
	
	return (
		<motion.li className={`${styles.item}`} {...anim}>
			<div className={`${styles.itemContent}`}>
				<Input
					type={'text'}
					placeholder={'Заголовок'}
					value={title}
					onChange={(value) => setTitle(value)}
				/>
				<Textarea value={info} onChange={(value) => setInfo(value)} placeholder={'Описание'}/>
			</div>
			<div className={`${styles.itemButtons}`}>
				<button className={`btn`} onClick={onCreate}>Отмена</button>
				<button className={`btn`} onClick={createNewJob}>Отправить</button>
			</div>
		</motion.li>
	);
};

export default JobForm;