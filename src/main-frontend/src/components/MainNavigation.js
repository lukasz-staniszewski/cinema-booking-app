import {NavLink} from "react-router-dom";
import styles from "./MainNavigation.module.css"

const MainNavigation = () =>{
    return(
        <nav className={styles.navigation}>
            <ul>
                <li>
                    <NavLink to="/" className={(data) => (data.isActive ? styles.active: "")}>Start</NavLink>
                </li>
                <li>
                    <NavLink to="/repertuar" className={(data) => (data.isActive ? styles.active: "")}> Repertuar</NavLink>
                </li>
            </ul>
        </nav>
    )
}

export default MainNavigation;