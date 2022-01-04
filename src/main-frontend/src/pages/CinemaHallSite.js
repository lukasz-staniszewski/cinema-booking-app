import React, {useState} from "react";
import styles from "./CinemaHallSite.module.css";
import {useLocation} from "react-router-dom";
import {Fragment} from "react";
import ReservationInfoScreen from "../components/cinemahallsite/ReservationInfoScreen";
import Loader from "react-loader-spinner";
import ShowTimeInfoHeader from "../components/cinemahallsite/ShowTimeInfoHeader"
import ReservationArea from "../components/cinemahallsite/ReservationArea";

const CinemaHallSite = ()=>{
    const [modalIsShown, setModalIsShown] = useState(false);
    const [isErrorReservation, setIsErrorReservation] = useState(false);
    const [isLoadingReservations, setIsLoadingReservations] = useState(true);
    const location = useLocation();
    const {showtimeInfo, filmInfo} = location.state;

    const hideModalHandler = ()=>{
        setModalIsShown(false);
    }

    return (
        <Fragment>
            {modalIsShown && <ReservationInfoScreen onClose={hideModalHandler} isError={isErrorReservation}/>}
            <div className={styles.hall}>
                {isLoadingReservations && <div className={styles.loaderwrapper}><Loader type="Plane" color="white" secondaryColor="white"/></div>}
                <div className={styles.centerwrapper}>
                    <ShowTimeInfoHeader showtimeInfo={showtimeInfo} filmInfo = {filmInfo}/>
                    <ReservationArea showtimeInfo={showtimeInfo} setIsLoadingReservations = {setIsLoadingReservations} setModalIsShown = {setModalIsShown} setIsErrorReservation = {setIsErrorReservation}/>
                </div>
            </div>
        </Fragment>);
}

export default CinemaHallSite;