import {NavLink} from "react-router-dom";
import {Fragment, useState, useContext} from "react";

import styles from "./MainNavigation.module.css"

import AuthScreen from "../../auth/AuthScreen";
import AuthContext from "../store/auth-context.js";

const MainNavigation = () =>{
    const [modalIsShown, setModalIsShown] = useState(false);
    const authCtx = useContext(AuthContext);

    const showModalHandler = ()=>{
        setModalIsShown(true);
    }

    const hideModalHandler = ()=>{
        setModalIsShown(false);
    }

    return(
        <Fragment>
            {modalIsShown && <AuthScreen onClose={hideModalHandler}/>}
            <nav className={styles.navigation}>
                <ul>
                    <li>
                        <NavLink to="/" className={(data) => (data.isActive ? styles.active : "")}>Start</NavLink>
                    </li>
                    <li>
                        <NavLink to="/repertuar" className={(data) => (data.isActive ? styles.active: "")}> Repertuar</NavLink>
                    </li>
                    {authCtx.isUserLogged && <li><NavLink to={"/profil"}>Profil</NavLink></li>}
                    <li>
                        <div className={styles.buttonwrapper}><button className={authCtx.isUserLogged ? '' : styles['log-reg-fs']} onClick={authCtx.isUserLogged ? authCtx.logout : showModalHandler}>
                            {
                                authCtx.isUserLogged ? "Wyloguj" : <Fragment>Zaloguj /<br/>Zarejestruj</Fragment>
                            }
                            </button></div>
                    </li>
                </ul>
            </nav>
        </Fragment>
    )
}

export default MainNavigation;