import React, {useCallback, useContext, useEffect, useState} from "react";
import styles from "./CinemaHallSite.module.css";
import {useLocation} from "react-router-dom";
import AuthContext from "../components/store/auth-context";
import jwt_decode from "jwt-decode";
import {Fragment} from "react";
import ReservationInfoScreen from "../components/ReservationInfoScreen";
import Loader from "react-loader-spinner";

const CinemaHallSite = ()=>{
    const [rows, setRows] = useState();
    const [columns, setColumns] = useState();
    const [reservatedSeats, setReservatedSeats] = useState([]);
    const [choosePlace, setChoosePlace] = useState([]);
    const location = useLocation();
    const [isLoadingReservations, setIsLoadingReservations] = useState(true);
    const {showtimeInfo, filmInfo} = location.state;
    const authCtx = useContext(AuthContext);
    const [modalIsShown, setModalIsShown] = useState(false);
    const [isErrorReservation, setIsErrorReservation] = useState(false);
    const [isReservationPerformed, setIsReservationPerformed] = useState(false);
    const style = { position: "absolute", display: "flex", justifyContent: "center", alignItems: "center", width: "100%", height: "100%", backgroundSize: "cover", backgroundColor: "rgba(0,0,0,0.8)" , zIndex: "5"};

    const performReservation = () =>{
        let today = new Date();
        setIsReservationPerformed(false);
        let date = today.getFullYear()+'-'+('0' + today.getMonth()+1).slice(-2)+'-'+('0' + today.getDate()).slice(-2);
        let seats_list = [];
        choosePlace.map((item)=>{
            let seat_info = item.split(' ');
            seats_list.push(
                {
                    rowNumber: parseInt(seat_info[0], 10),
                    seatInRowNumber: parseInt(seat_info[1],10),
                    cinemaHallNumber: showtimeInfo.cinemaHallNumber,
                }
            )
            return seats_list;
        })
        if (checkCanReservate()){
            let fetch_json = {
               timestamp: date,
                clientEmail: mailFromJWT(),
                showTimeId: showtimeInfo.showtimeId,
                reservationSeats: seats_list,
            }
            const requestOptions = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': authCtx.token},
                body: JSON.stringify(fetch_json)
            };
            fetch(' /reservations/reservation', requestOptions).then((response)=>{
                setModalIsShown(true);
                setIsReservationPerformed(true);
                if (!response.ok){
                    setIsErrorReservation(true);
                }
                else {
                    setIsErrorReservation(false);
                }
                setChoosePlace([]);
            });


        }
    }

    const mailFromJWT = () =>{
        if (authCtx.isUserLogged) {
            const decoded = jwt_decode(authCtx.token);
            return decoded.sub;
        }
        else{
            console.log("User is not logged to get mail from token!");
        }
    }

    const fetchFullHall = useCallback(async () => {
        try{
            const response_fetch = await fetch(`cinema_halls/cinema_hall/${showtimeInfo.cinemaHallNumber}`, {headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }});

            if(!response_fetch.ok){
                throw new Error("Full cinema fetch not ok!");
            }

            else {
                const full_hall_json = await response_fetch.json();
                setRows(full_hall_json.nrows);
                setColumns(full_hall_json.nseatsInRows);
            }
        }
        catch (err){
            console.log(err);
            console.log("Cant connect do database!");
        }
        // eslint-disable-next-line
    }, []);

    const checkCanReservate = () =>{
        return choosePlace.length > 0 && authCtx.isUserLogged;
    }

    const fetchReservations = useCallback(async () => {
        try{
            setIsLoadingReservations(true);
            const response_fetch = await fetch(`show_time_seats/showtime_id/?show_time_id=${showtimeInfo.showtimeId}`, {headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }});

            if(!response_fetch.ok){
                throw new Error("Reservations fetch not ok!");
            }

            else {
                const reservations_json = await response_fetch.json();
                let temp_reservations = [];
                reservations_json.map(item=>{
                    return temp_reservations.push(`${item.rowNumber} ${item.seatInRowNumber}`);
                });
                setReservatedSeats(temp_reservations);
                setIsLoadingReservations(false);
            }

        }
        catch (err){
            console.log(err);
            console.log("Cant connect do database!");
        }

        // eslint-disable-next-line
    }, []);


    const choosePlaceHandler = (e) => {
        if (!choosePlace.includes(e.target.id)) {
            setChoosePlace((prevPlaces) => [...prevPlaces, e.target.id]);
        } else {
            setChoosePlace((prevPlaces) =>
                prevPlaces.filter((item) => item !== e.target.id)
            );
        }
    };

    const chairColor = (row, column) => {
        if(reservatedSeats.includes(`${row} ${column}`)) {
            return styles.occupied;
        }
        else if (choosePlace.includes(`${row} ${column}`)){
            return styles.active;
        }
        return "";
    }

    useEffect(()=> {
        fetchFullHall();
        // eslint-disable-next-line
    },[]);

    useEffect(()=> {
        console.log("FETCHING!~~~~~~~~")
        fetchReservations();
        // eslint-disable-next-line
    },[isReservationPerformed]);


    const hideModalHandler = ()=>{
        setModalIsShown(false);
    }
    // if (isLoadingReservations){
    //     return (<div style={style}><Loader type="Plane" color="8b0000" secondaryColor="white"/></div>)
    // }
    return (
        <Fragment>
            {modalIsShown && <ReservationInfoScreen onClose={hideModalHandler} isError={isErrorReservation}/>}
            <div className={styles.hall}>
                {isLoadingReservations && <div style={style}><Loader type="Plane" color="white" secondaryColor="white"/></div>}
                <div className={styles.centerwrapper}>
                    <div className={styles.showtimeinfo}>
                        <div>
                            <div>
                                <i className="fas fa-map-marked-alt"/>
                                <p>Sala {showtimeInfo.cinemaHallNumber}</p>
                            </div>
                            <div>
                                <p>{filmInfo.date},</p>
                                <p>godz. {showtimeInfo.hour}</p>
                            </div>
                        </div>
                        <div>
                            <div>
                                <h2>{filmInfo.name}</h2>
                                <h5>{filmInfo.director}</h5>
                            </div>
                            <div className={styles.space}/>
                            <div>
                                <ul className={styles.filminfo}>
                                    {filmInfo.type && <li>{filmInfo.type}</li>}
                                    {filmInfo.productionYear && <li>{filmInfo.productionYear}</li>}
                                    {filmInfo.length && <li>{filmInfo.length} min.</li>}
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div>
                            {[...Array(rows).keys()].map((row) => (
                                <div className={styles.row} key={`${rows-(row)}`}>
                                    <div className={styles.linewrapper}>
                                        <p>{rows-(row)}</p>
                                        <div className={styles.line}/>
                                    </div>
                                        {[...Array(columns).keys()].map((column) => (
                                            <button
                                                className={chairColor(rows-(row), column+1)}
                                                onClick={choosePlaceHandler}
                                                key={`${rows-(row)} ${column+1}`}
                                                id={`${rows-(row)} ${column+1}`}
                                                disabled={reservatedSeats.includes(`${rows - (row)} ${column + 1}`)}
                                            >
                                                <i id={`${rows-(row)} ${column+1}`} className="fas fa-couch"/>
                                                <span
                                                    id={`${rows-(row)} ${column+1}`}
                                                    className={chairColor(rows-(row), column+1)}
                                                >{column+1}</span>
                                            </button>
                                        ))}
                                    <div className={styles.linewrapper}>
                                        <div className={styles.line}/>
                                        <p>{rows-(row)}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div className={styles.screen}>
                            <p>ekran</p>
                            <div/>
                        </div>
                        <div className={styles.underscreen}>
                            <div className={styles.legend}>
                                <div>
                                    <i className="fas fa-couch"/>
                                    <p>wolne</p>
                                </div>
                                <div>
                                    <i className="fas fa-couch"/>
                                    <p>zajęte</p>
                                </div>
                                <div>
                                    <i className="fas fa-couch"/>
                                    <p>wybrane</p>
                                </div>
                            </div>
                            <button className={`${checkCanReservate() ? '' : styles['locked']} ${styles.reservationbtn}`} onClick={performReservation} disabled={!checkCanReservate()}>
                                Zarezerwuj
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>);
}

export default CinemaHallSite;