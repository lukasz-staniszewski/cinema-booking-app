import Modal from "../layout/Modal";
import styles from "./ReservationInfoScreen.module.css"

const ReservationInfoScreen = (props) => {
    return(
       <Modal onClose = {props.onClose}>
            <div className={styles.wrapper}>
                <h2>
                    {props.isError ? "Nie udało się dokonać rezerwacji!" : "Udało Ci się dokonać rezerwacji!"}
                </h2>
                <p>
                    {props.isError ? "Miejsce, które zaznaczyłeś zostało w międzyczasie zarezerwowane!" :
                    "Pamiętaj, że możesz w każdej chwili zrezygnować ze swojej rezerwacji. Do zobaczenia wkrótce!"}
                </p>
            </div>
       </Modal>
    )
};

export default ReservationInfoScreen;