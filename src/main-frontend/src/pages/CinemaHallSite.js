import {useCallback, useContext, useEffect, useState} from "react";
import styles from "./CinemaHallSite.module.css";
import {useLocation} from "react-router-dom";
import AuthContext from "../components/store/auth-context";
import jwt_decode from "jwt-decode";
import {Fragment} from "react";
import ReservationInfoScreen from "../components/ReservationInfoScreen";

const CinemaHallSite = ()=>{
    const [rows, setRows] = useState();
    const [columns, setColumns] = useState();
    const [reservatedSeats, setReservatedSeats] = useState([]);
    const [choosePlace, setChoosePlace] = useState([]);
    const location = useLocation();
    const {showtimeInfo} = location.state;
    const authCtx = useContext(AuthContext);
    const [modalIsShown, setModalIsShown] = useState(false);
    const [isErrorReservation, setIsErrorReservation] = useState(false);

    const performReservation = () =>{
        let today = new Date();
        let date = today.getFullYear()+'-'+('0' + today.getMonth()+1).slice(-2)+'-'+('0' + today.getDate()).slice(-2);
        let seats_list = [];
        // eslint-disable-next-line
        choosePlace.map((item)=>{
            let seat_info = item.split(' ');
            seats_list.push(
                {
                    rowNumber: parseInt(seat_info[0], 10),
                    seatInRowNumber: parseInt(seat_info[1],10),
                    cinemaHallNumber: showtimeInfo.cinemaHallNumber,
                }
            )
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
                if (!response.ok){
                    setIsErrorReservation(true);
                }
                else {
                    setIsErrorReservation(false);
                }
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
                prevPlaces.filter((item, index) => item !== e.target.id)
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
        fetchReservations();
    },[fetchFullHall, fetchReservations])

    const hideModalHandler = ()=>{
        setModalIsShown(false);
    }

    return (
        <Fragment>
            {modalIsShown && <ReservationInfoScreen onClose={hideModalHandler} isError={isErrorReservation}/>}
            <div className={styles.hall}>
                <div>
                    {[...Array(rows).keys()].map((row) => (
                        <div className={styles.row} key={`${row}`}>
                            {[...Array(columns).keys()].map((column) => (
                                <button
                                    className={
                                        chairColor(rows-(row), column+1)
                                    }
                                    onClick={choosePlaceHandler}
                                    key={`${rows-(row)} ${column+1}`}
                                    id={`${rows-(row)} ${column+1}`}
                                    disabled={reservatedSeats.includes(`${rows-(row)} ${column+1}`) ? true :false}
                                >
                                    <i id={`${rows-(row)} ${column+1}`} className="fas fa-couch"/>
                                </button>
                            ))}
                        </div>
                    ))}
                    <div className={styles.screen}>
                        <p>ekran</p>
                        <div></div>
                    </div>
                </div>
                <button className={checkCanReservate() ? '' : styles['locked']} onClick={performReservation} disabled={!checkCanReservate()}>
                    Zarezerwuj
                </button>
            </div>
        </Fragment>);
}

export default CinemaHallSite;