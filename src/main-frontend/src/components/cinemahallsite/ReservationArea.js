import styles from "./ReservationAdrea.module.css"
import {useCallback, useContext, useEffect, useState} from "react";
import jwt_decode from "jwt-decode";
import AuthContext from "../store/auth-context";

const ReservationArea = (props) =>{
    const [rows, setRows] = useState();
    const [columns, setColumns] = useState();
    const [reservatedSeats, setReservatedSeats] = useState([]);
    const [choosePlace, setChoosePlace] = useState([]);
    const authCtx = useContext(AuthContext);
    const [isReservationPerformed, setIsReservationPerformed] = useState(false);

    const checkCanReservate = () =>{
        return choosePlace.length > 0 && authCtx.isUserLogged;
    }

    const choosePlaceHandler = (e) => {
        if (!choosePlace.includes(e.target.id)) {
            setChoosePlace((prevPlaces) => [...prevPlaces, e.target.id]);
        } else {
            setChoosePlace((prevPlaces) =>
                prevPlaces.filter((item) => item !== e.target.id)
            );
        }
    };

    const fetchFullHall = useCallback(async () => {
        try{
            const response_fetch = await fetch(`cinema_halls/cinema_hall/${props.showtimeInfo.cinemaHallNumber}`, {headers: {
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

    const fetchReservations = useCallback(async () => {
        try{
            props.setIsLoadingReservations(true);
            const response_fetch = await fetch(`show_time_seats/showtime_id/?show_time_id=${props.showtimeInfo.showtimeId}`, {headers: {
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
                props.setIsLoadingReservations(false);
            }

        }
        catch (err){
            console.log(err);
            console.log("Cant connect do database!");
        }
        // eslint-disable-next-line
    }, []);

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
                    cinemaHallNumber: props.showtimeInfo.cinemaHallNumber,
                }
            )
            return seats_list;
        })
        if (checkCanReservate()){
            let fetch_json = {
                timestamp: date,
                clientEmail: mailFromJWT(),
                showTimeId: props.showtimeInfo.showtimeId,
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
                props.setModalIsShown(true);
                setIsReservationPerformed(true);
                if (!response.ok){
                    props.setIsErrorReservation(true);
                }
                else {
                    props.setIsErrorReservation(false);
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
        fetchReservations();
        // eslint-disable-next-line
    },[isReservationPerformed]);

    return (
        <div className={styles.reservationareawrapper}>
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
    );
};

export default ReservationArea;