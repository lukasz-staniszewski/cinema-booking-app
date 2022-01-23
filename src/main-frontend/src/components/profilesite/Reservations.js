import {useContext, useEffect, useState} from "react";
import AuthContext from "../store/auth-context";
import jwt_decode from "jwt-decode";
import ReservationItem from "./ReservationItem";
import classes from "./Reservations.module.css"

const Reservations = (props) =>{
    const authCtx = useContext(AuthContext);
    const [userReservations, setUserReservations] = useState([]);
    const [isShowingPrevious, setIsShowPrevious] = useState(false);

    const mailFromJWT = () =>{
        if (authCtx.isUserLogged) {
            const decoded = jwt_decode(authCtx.token);
            return decoded.sub;
        }
        else{
            console.log("User is not logged to get mail from token!");
        }
    }

    const toPreviousHandler = () =>{
        if(!isShowingPrevious){
            setIsShowPrevious(true);
        }
    };

    const toCurrentHandler = () =>{
      if(isShowingPrevious){
          setIsShowPrevious(false);
      }
    };

    const fetchUserReservations = async () =>{
        try{
            props.setIsLoadingReservations(true);
            const response_fetch = await fetch (`/reservations/email?email=${mailFromJWT()}`, {headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': authCtx.token
                }});
            if(!response_fetch.ok){
                throw new Error("Fetch not ok!");
            }
            else{
                let response = await response_fetch.json()
                response.map((reservation)=>{
                    return reservation.seats.sort((a,b)=>{
                        return a.rowNumber - b.rowNumber || a.seatInRowNumber - b.seatInRowNumber})
                })
                setUserReservations(response);
                props.setIsLoadingReservations(false);
            }
        }
        catch (err){
            console.log(err);
            console.log("Cant connect to db!")
        }
    };

    useEffect( () =>{
        fetchUserReservations();
        // eslint-disable-next-line
    },[authCtx.isUserLogged]);

    const filteredReservaions = userReservations.filter(item =>
        item.isToCancel===isShowingPrevious
    );

    return (<div>
        <h2 className={classes.h2}>Twoje rezerwacje</h2>
        <div className={classes.card}>
            <div className={classes.filters}>
                <button onClick={toPreviousHandler} className={isShowingPrevious && classes.isActive}>Stare rezerwacje</button>
                <button onClick={toCurrentHandler} className={!isShowingPrevious && classes.isActive}>Aktualne rezerwacje</button>
            </div>
            <div className={classes["reservation-list"]}>
            {filteredReservaions && filteredReservaions.map((el, index)=>{
                {return <ReservationItem key={index} details={el}/>}
            })}
                {filteredReservaions.length===0 && <p className={classes.noresinfo}>Brak rezerwacji</p>}
            </div>
        </div>
    </div>)
};
export default Reservations;