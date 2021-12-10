import classes from "./Film.module.css";

const Film = (props) => {

    return (
        <li key={props.show.id} className={classes[`card-content`]}>
                <div className={classes.information}>
                    <h2>{props.show.name}</h2>
                </div>
                <p className={classes.position}>{props.show.director}</p>
                <div className={classes.about}>
                    <p>Production year: {props.show.production_year}</p>
                    <p>Day: {props.show.date}</p>
                    <p>Hour: {props.show.hour}</p>
                </div>
        </li>
    );
};

export default Film;