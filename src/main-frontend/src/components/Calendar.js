import {useState} from "react";
import classes from "./Calendar.module.css";
const Calendar = (props) => {

    const [enteredDate, setEnteredDate] = useState("");
    const changeHandler = (event) => {
        setEnteredDate(event.target.value)
        props.changeDateFunction(event.target.value);
    }
    return (
      <form align={"center"}>
          <input className={classes.calendar} onChange={changeHandler} type="date" id={"calendar"} min={"2021-12-12"} max={"2021-12-31"} value={enteredDate}/>
      </form>
    );
}
export default Calendar;