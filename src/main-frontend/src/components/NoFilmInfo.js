import styles from "./NoFilmInfo.module.css";

const NoFilmInfo = () => {
    return(
        <div className={styles.wrapper}>
            <div>
                <i className="far fa-sad-tear"/>
                <h2>
                    Wybacz!
                </h2>
                <i className="far fa-sad-tear"/>
            </div>
            <div>
                <p>
                    Nie ma żadnego seansu w podanym terminie
                </p>
            </div>

        </div>)
};

export default NoFilmInfo;