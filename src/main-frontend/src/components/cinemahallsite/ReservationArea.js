import styles from "./ReservationArea.module.css"
import {useState} from "react";
import CinemaHall from "./CinemaHall";
import ReservationPerformer from "./ReservationPerformer";

const ReservationArea = (props) =>{
    const [choosePlace, setChoosePlace] = useState([]);
    const [isReservationPerformed, setIsReservationPerformed] = useState(false);

    return (
        <div className={styles.reservationareawrapper}>
            <CinemaHall
                showtimeInfo={props.showtimeInfo}
                choosePlace={choosePlace}
                setChoosePlace={setChoosePlace}
                isReservationPerformed={isReservationPerformed}
                setIsLoadingReservations={props.setIsLoadingReservations}
            />
            <ReservationPerformer
                showtimeInfo={props.showtimeInfo}
                choosePlace={choosePlace}
                setChoosePlace={setChoosePlace}
                setIsReservationPerformed={setIsReservationPerformed}
                setModalIsShown={props.setModalIsShown}
                setIsErrorReservation={props.setIsErrorReservation}
            />
        </div>
    );
};

export default ReservationArea;