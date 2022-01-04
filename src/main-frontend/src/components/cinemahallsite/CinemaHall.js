import {Fragment, useCallback, useEffect, useState} from "react";
import styles from "./CinemaHall.module.css";

const CinemaHall = (props) => {
    const [rows, setRows] = useState();
    const [columns, setColumns] = useState();
    const [reservatedSeats, setReservatedSeats] = useState([]);

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

    const choosePlaceHandler = (e) => {
        if (!props.choosePlace.includes(e.target.id)) {
            props.setChoosePlace((prevPlaces) => [...prevPlaces, e.target.id]);
        } else {
            props.setChoosePlace((prevPlaces) =>
                prevPlaces.filter((item) => item !== e.target.id)
            );
        }
    };

    const chairColor = (row, column) => {
        if(reservatedSeats.includes(`${row} ${column}`)) {
            return styles.occupied;
        }
        else if (props.choosePlace.includes(`${row} ${column}`)){
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
    },[props.isReservationPerformed]);

    return(
        <Fragment>
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
        </Fragment>)
};

export default CinemaHall;