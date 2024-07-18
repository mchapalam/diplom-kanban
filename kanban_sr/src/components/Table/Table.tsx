import {FC, useState} from "react";
import styles from "./Table.module.scss";
import {tJob} from "../../utils/types.ts";
import {enTypesToRu, jobTypes} from "../../utils/enum.ts";
import {JobForm, Job} from "../";
import {useParams} from "react-router-dom";
import { motion } from "framer-motion";

interface iTable {
	jobs: tJob[]
	name: tJob['type']
	index?: number
}

const Table:FC<iTable> = ({jobs, name, index}) => {
	const [addForm, setAddForm] = useState<boolean>(false)

	const type = jobTypes[name] as number
	const prevType = type > 1 && type-1
	const nextType = type < 5 && type+1

	const params = useParams()

	const sortedJobs = jobs.sort((a, b) => new Date(a.dateCreate).getTime() > new Date(b.dateCreate).getTime() ? 1 : -1)
	const mappedJobs = sortedJobs.map((job, i) => <Job key={job.id} index={i} job={job} prevType={prevType} nextType={nextType}/>)

	const anim = {
		initial: {
      opacity: 0,
    },
    animate: {
      opacity: 1,
      transition: {
        duration: (index || 1)*0.3,
      },
    },
	}

	return (
		<motion.div className={`${styles.table}`} {...anim}>
			<div className={`${styles.borderColor}`}></div>
			<div className={`${styles.tableContent}`}>
				<div className={`${styles.tableHead}`}>
					<h2 className={`${styles.tableTitle}`}>{enTypesToRu[name]}</h2>
					{name === 'New'
						&& !addForm
						&& <button className="btn" onClick={() => setAddForm(prev => !prev)}>Добавить</button>}
				</div>
				<ul className={`${styles.list}`}>
					{addForm && params.id && <JobForm projectId={params.id} onCreate={() => setAddForm(false)}/>}
					{mappedJobs.length ? mappedJobs : <div className={styles.emptyTable}>Пусто</div>}
				</ul>
			</div>
		</motion.div>
	);
};

export default Table;
