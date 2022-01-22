import styles from "./ReservationPerformer.module.css";
import jwt_decode from "jwt-decode";
import {useContext} from "react";
import AuthContext from "../store/auth-context";

const ReservationPerformer = (props) =>{
    const authCtx = useContext(AuthContext);

    const checkCanReservate = () =>{
        return props.choosePlace.length > 0 && authCtx.isUserLogged;
    }

    const performReservation = () =>{
        let today = new Date();
        props.setIsReservationPerformed(false);
        let date = today.getFullYear()+'-'+('0' + today.getMonth()+1).slice(-2)+'-'+('0' + today.getDate()).slice(-2);
        let seats_list = [];
        props.choosePlace.map((item)=>{
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
                props.setIsReservationPerformed(true);
                if (!response.ok){
                    props.setIsErrorReservation(true);
                }
                else {
                    props.setIsErrorReservation(false);
                }
                props.setChoosePlace([]);
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

    return(
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
    )
};
export default ReservationPerformer;