import { Fragment } from "react/cjs/react.production.min";

import Film from "./Film"

import classes from "./FilmList.module.css";

const FilmList = (props) => {
    let listShows = props.films;


    return (
        <Fragment>
            <ul className={classes[`offerts-list`]}>
                {listShows.map((show) => (
                    <Film show={show} key={show.movieId}/>
                ))}
            </ul>
        </Fragment>
    );
};

export default FilmList;