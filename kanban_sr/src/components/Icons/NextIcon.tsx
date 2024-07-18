import {FC} from 'react';

const NextIcon:FC<{w: number, h: number}> = ({w, h}) => {
	return (
		<svg width={w} height={h} viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
			<path d="M754.752 480H160a32 32 0 1 0 0 64h594.752L521.344 777.344a32 32 0 0 0 45.312 45.312l288-288a32 32 0 0 0 0-45.312l-288-288a32 32 0 1 0-45.312 45.312L754.752 480z"/>
		</svg>
	);
};

export default NextIcon;