import classes from "./CancelButton.module.css"
import {useContext} from "react";
import AuthContext from "../store/auth-context";
const CancelButton = (props) =>{
    const authCtx = useContext(AuthContext);
    console.log(props);
    const cancelReservation = async () =>{
        if (!props.isToCancel){
            try{
                const response_fetch = await fetch (`/reservations/reservation/${props.reservationId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': authCtx.token
                    }});

                if(!response_fetch.ok){
                    throw new Error("Fetch not ok!");
                }
                else{
                    window.location.reload(true);
                    console.log('DELETED!');
                }
            }
            catch (err){
                console.log(err);
                console.log("Cant connect to db!")
            }
        }
    }

    return(
        <button className={`${classes.cancelbutton} ${props.isToCancel? classes.ispast : ""}`}
            onClick={cancelReservation}
        >
            Anuluj <i className="fas fa-times-circle"/>
        </button>
    )
};
export default CancelButton;