import {NavLink} from "react-router-dom";
import styles from "./MainNavigation.module.css"

const MainNavigation = () =>{
    return(
        <nav>
            <ul>
                <li>
                    <NavLink to="/" className={(data) => (data.isActive ? styles.active: "")}>Home</NavLink>
                </li>
                <li>
                    <NavLink to="/repertuar" className={(data) => (data.isActive ? styles.active: "")}> Repertuar</NavLink>
                </li>
            </ul>
        </nav>
    )
}

export default MainNavigation;