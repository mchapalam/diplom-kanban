import {FC, memo, useState} from "react";
import {EditIcon, DeleteIcon, BackIcon, NextIcon} from "../Icons";
import styles from "./Job.module.scss";
import {tJob} from "../../utils/types.ts";
import {store} from "../../store/store";
import {printDate, jobsApi} from "../../helpers";
import {motion} from "framer-motion"
import {Input, Textarea} from "../";

interface iJob {
	job: tJob
	nextType: number | false
	prevType: number | false
	index?: number
}

const Job: FC<iJob> = memo(({job, nextType, prevType, index}) => {
	const {changeTableJob, deleteJob, updateJob} = store(state => state);
	const [loading, setLoading] = useState<boolean>(false);
	const [edit, setEdit] = useState<boolean>(false);

	const [title, setTitle] = useState<string>(job.title);
	const [info, setInfo] = useState<string>("");

const handleChangeType = async (type: 'next' | 'back', id: tJob['id'], indexType: number) => {
  try {
    setLoading(true);
    await jobsApi.changeType(id, type);
    changeTableJob(id, indexType);
  }
	catch (e) {console.log(e)}
  finally {setLoading(false);}
};

const nextHandler = async () => {
  if (nextType === false) return;
  await handleChangeType('next', job.id, nextType);
};

const prevHandler = async () => {
  if (prevType === false) return;
  await handleChangeType('back', job.id, prevType);
};

	const updateHandler = () => {
		updateJob(job.id, {title});
		setEdit(false);
	};

	const anim = {
		initial: {
			opacity: 0,
			y: 40,
		},
		animate: {
			opacity: 1,
			y: 0,
			transition: {
				duration: (index || 1)*0.2,
			},
		},
	};

	return (
		<motion.li className={`${styles.item}`} {...anim}>
			<div className={`${styles.itemContent}`}>
				{edit
					? <Input type={"text"} value={title} onChange={(value) => setTitle(value)}/>
					: <h3 className={`${styles.itemTitle}`}>{job.title}</h3>}

				{edit
					? <Textarea value={info} onChange={(value) => setInfo(value)}/>
					: <p className={`${styles.itemText}`}>
						{job.info}
						{/*Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus,*/}
						{/*autem sunt non dicta quaerat eos odio distinctio, impedit magnam placeat cum*/}
						{/*repellendus labor*/}
					</p>}

				<p className={`${styles.itemDate}`}>{printDate(job.dateCreate).fullDate}</p>
			</div>

			<div className={`${styles.itemButtons}`}>
				{edit
					? <>
						<button className={"btn"} onClick={() => setEdit(false)}>Отмена</button>
						<button className={"btn"} onClick={updateHandler}>Сохранить</button>
					</>
					: <>
						{prevType !== false &&
							<button className={`${styles.item__link} ${styles.link_back}`} onClick={prevHandler} disabled={loading}>
								<BackIcon h={15} w={15}/>
							</button>}
						<button className={`${styles.item__link} ${styles.link_edit}`} onClick={() => setEdit(true)}>
							<EditIcon h={15} w={15}/>
						</button>
						<button className={`${styles.item__link} ${styles.link_delete}`} onClick={() => deleteJob(job.id)}>
							<DeleteIcon h={15} w={15}/>
						</button>
						{nextType !== false &&
							<button className={`${styles.item__link} ${styles.link_next}`} onClick={nextHandler} disabled={loading}>
								<NextIcon h={15} w={15}/>
							</button>}
					</>}
			</div>
		</motion.li>
	);
});

export default Job;
