import classes from "./Film.module.css";

const Film = (props) => {
    const convertHour = (hour) =>{
        let divided = hour.split(':');
        return divided[0] + ":" + divided[1];
    }

    return (
        <li key={props.show.movieId} className={classes[`card-content`]}>
                <div className={classes.information}>
                    <h2>{props.show.name}</h2>
                </div>
                <p className={classes.position}>{props.show.director}</p>
                <div className={classes.about}>
                    <p>Production year: {props.show.productionYear}</p>
                    <p>Length: {props.show.length} minutes</p>
                    <p>Playing hours:</p>
                    <div>
                        {
                            props.show.hour.map((item)=> <p>{convertHour(item)}</p>)}
                    </div>
                </div>
        </li>
    );
};

export default Film;