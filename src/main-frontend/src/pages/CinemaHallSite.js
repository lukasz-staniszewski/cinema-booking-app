import {useCallback, useEffect, useState} from "react";
import styles from "./CinemaHallSite.module.css";
import {useLocation} from "react-router-dom";

const CinemaHallSite = ()=>{
    const [rows, setRows] = useState();
    const [columns, setColumns] = useState();
    const [reservatedSeats, setReservatedSeats] = useState([]);
    const [choosePlace, setChoosePlace] = useState([]);
    const location = useLocation();
    const {showtimeInfo} = location.state;

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
    console.log("NUMBER OF HALL:")
    console.log(showtimeInfo.cinemaHallNumber);
    useEffect(()=> {
        fetchFullHall();
        fetchReservations();
    },[fetchFullHall, fetchReservations])

    return (
        <div className={styles.hall}>
            <div>
            {[...Array(rows).keys()].map((row) => (
                <div className={styles.row} key={`${row}`}>
                    {[...Array(columns).keys()].map((column) => (
                        <button
                            className={
                                chairColor(row, column)
                            }
                            onClick={choosePlaceHandler}
                            key={`${row} ${column}`}
                            id={`${row} ${column}`}
                            disabled={reservatedSeats.includes(`${row} ${column}`) ? true :false}
                        >
                            <i id={`${row} ${column}`} className="fas fa-couch"/>
                        </button>
                    ))}
                </div>
            ))}
            <div className={styles.screen}>
                <p>ekran</p>
                <div></div>
            </div>
            </div>
        </div>
    );
}

export default CinemaHallSite;