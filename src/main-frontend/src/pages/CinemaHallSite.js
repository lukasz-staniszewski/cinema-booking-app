import { useState } from "react";

import styles from "./CinemaHallSite.module.css";

const CinemaHallSite = ()=>{
    const rows = 12;
    const columns = 10;
    const DUMMY_DATA = ["1 1","11 0", "11 1", "11 2"]
    const [choosePlace, setChoosePlace] = useState([]);

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
        if(DUMMY_DATA.includes(`${row} ${column}`)) {
            return styles.occupied;
        }
        else if (choosePlace.includes(`${row} ${column}`)){
            return styles.active;
        }
        return "";
    }

    console.log(choosePlace);

    return (
        <div className={styles.hall}>
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
                            disabled={DUMMY_DATA.includes(`${row} ${column}`) ? true :false}
                        >
                            r: {row},c: {column}{" "}
                        </button>
                    ))}
                </div>
            ))}
        </div>
    );
}

export default CinemaHallSite;