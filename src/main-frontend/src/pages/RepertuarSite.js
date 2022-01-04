import Calendar from "../components/Calendar";
import FilmList from "../components/FilmList";
import {useCallback, useEffect, useState} from "react";
import classes from "./RepertuarSite.module.css";
import NoFilmInfo from "../components/NoFilmInfo";

const RepertuarSite = () =>{
    const[data, setData] = useState([]);
    let today = new Date();
    let todays_date = today.getFullYear()+'-'+('0' + today.getMonth()+1).slice(-2)+'-'+('0' + today.getDate()).slice(-2);
    const[date, setDate] = useState(todays_date);

    const setDateHandler = (newDate) => {
        setDate(newDate);
    }

    const flattenObj = (obj) => {
        if (!obj) {
            throw Error(
                `flattenObj function expects an Object, received ${typeof obj}`
            );
        }
        return Object.keys(obj).reduce((acc, curr) => {
            const objValue = obj[curr];
            const ret =
                objValue && objValue instanceof Object
                    ? flattenObj(objValue)
                    : { [curr]: objValue };
            return Object.assign(acc, ret);
        }, {});
    };

    const changeShowtimesJson = (inpJson) =>{
        let inpJsonFlatten = [...inpJson.map((item) => flattenObj(item))];
        return inpJsonFlatten.reduce((acc, {movieId, name, date, description, length, productionYear, type, director, hour, showtimeId, cinemaHallNumber}) => {
            const existing = acc.find(i => i.movieId === movieId)
            if (existing) {
                existing.showtimeInfo.push({hour: hour, showtimeId: showtimeId, cinemaHallNumber: cinemaHallNumber})
            } else {
                acc.push({movieId, name, date, description, length, productionYear, type, director, showtimeInfo: [{hour: hour, showtimeId: showtimeId, cinemaHallNumber: cinemaHallNumber}]})
            }
            return acc
        }, []);
    }

    const fetchFilmHandler = useCallback(async () => {
        try{
            const response_fetch = await fetch(`show_times/date?date=${date}`, {headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }});

            if(!response_fetch.ok){
                throw new Error("Fetch not ok!");
            }

            else {
                const new_message_json = await response_fetch.json();
                let newMessageJsonModified = changeShowtimesJson(new_message_json);
                setData(newMessageJsonModified);
            }
        }
        catch (err){
            console.log(err);
            console.log("Cant connect do database!");
        }
        // eslint-disable-next-line
        }, [date]);


        useEffect(()=>{
            fetchFilmHandler();
        }, [fetchFilmHandler])

        return (
            <div className={classes['repertuar-container']}>
                <Calendar className={classes.calendar} changeDateFunction={setDateHandler}/>
                {data.length > 0 ? <FilmList films={data}/> : <NoFilmInfo/>}
            </div>
        );
};

export default RepertuarSite;



