import styles from './StartingSite.module.css';
import {Link} from "react-router-dom";

const StartingSite = () =>{
    return (
        <div className={styles.mainDiv}>
            <div className={styles.textDiv}>
                <div>
                <h1>Twoje przyszłe kino</h1>
                <Link className={styles.btn} to={'/repertuar'}>Zobacz repertuar
                </Link>
                </div>
            </div>
        </div>
    );
}

export default StartingSite;