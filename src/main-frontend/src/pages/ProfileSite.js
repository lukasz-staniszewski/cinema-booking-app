import classes from "./ProfileSite.module.css";
import UserInfo from "../components/profilesite/UserInfo";
import Reservations from "../components/profilesite/Reservations";
import styles from "./CinemaHallSite.module.css";
import Loader from "react-loader-spinner";
import React, {useState} from "react";
const ProfileSite = () =>{
    const [isLoadingReservations, setIsLoadingReservations] = useState(false);
    return (
        <div className={classes.divbackground}>
            <UserInfo/>
            <Reservations setIsLoadingReservations = {setIsLoadingReservations}/>
            <a href="https://www.freepik.com">Designed by macrovector / Freepik</a>
            {isLoadingReservations && <div className={styles.loaderwrapper}><Loader type="Plane" color="white" secondaryColor="white"/></div>}

        </div>
    )
};

export default ProfileSite;