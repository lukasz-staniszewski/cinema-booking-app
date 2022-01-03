import styles from "./NoFilmInfo.module.css";

const NoFilmInfo = () => {
    return(
        <div className={styles.wrapper}>
            <p>
                Wybacz! Nie ma żadnego seansu w podanym terminie :(
            </p>
        </div>)
};

export default NoFilmInfo;