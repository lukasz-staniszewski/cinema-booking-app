import styles from "./ReservationItem.module.css";

const ReservationItem = (props) => {
    const {date, hallNumber, hour, movieName, seats, timestamp} = props.details;

    const convertTimeStamp = (date) => {
        let newDate = new Date(date);

        return newDate.getDate()+"/"+(newDate.getMonth()+1)+"/"+newDate.getFullYear();
    }

    return <div className={styles['reservation-item']}>
        <div className={styles.details}>
            <div className={styles.hall}>
                <i className="fas fa-map-marked-alt"/>
                <p>sala {hallNumber}</p>
            </div>
            <div className={styles.date}>
                <p>{date}</p>
                <p>godz. {hour}</p>
            </div>
        </div>
        <p className={styles.movie}>{movieName}</p>
        <div className={styles["seats"]}>
            {seats.map((seat,index) => <div key={index}><div className={styles["seat"]}>
                <i className="fas fa-couch"/>
                <span>{seat.seatInRowNumber} </span>
            </div>
                <p>R.{seat.rowNumber}</p></div>)}
        </div>
        <p>Data złożenia rezerwacji: {convertTimeStamp(timestamp)}</p>
    </div>
}

export default ReservationItem;