import classes from "./Film.module.css";
import {Link} from "react-router-dom";

const Film = (props) => {
    const convertHour = (hour) =>{
        let divided = hour.split(':');
        return divided[0] + ":" + divided[1];
    }
    return (
        <li key={props.show.movieId} className={classes.card}>
            <h2>{props.show.name}</h2>
            <h3>{props.show.director}</h3>
            <ul className={classes.info}>
               {props.show.type && <li>{props.show.type}</li>}
                {props.show.productionYear && <li>{props.show.productionYear}</li>}
                {props.show.length && <li>{props.show.length} min.</li>}
            </ul>
            {props.show.description && <p>{props.show.description}</p>}
            <ul className={classes.hours}>
                    {props.show.showtimeInfo.map(item=><Link key={item.showtimeId} to="/rezerwacja" state={{showtimeInfo: item, filmInfo: props.show,}} >{convertHour(item.hour)}</Link>)}
            </ul>
        </li>
    );
};

export default Film;