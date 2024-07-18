type tPrintDate = (date: string) => {fullDate: string; ago: string}

const printDate: tPrintDate = (date) => {
	const initDate = new Date(date)
	const now = new Date()
  const month = initDate.getMonth() + 1
  const day = initDate.getDate()
  const year = initDate.getFullYear()

	const offset = (now.getTime() - initDate.getTime()) / 1000 / 60 / 60 / 24
  return {
		fullDate: `${day > 9 ? day : `0${day}`}-${month > 9 ? month : `0${month}`}-${year}`,
		ago: offset > 1 ? `${offset.toFixed(0)} дней назад` : 'Менее суток назад'
	}
}

export default printDate;