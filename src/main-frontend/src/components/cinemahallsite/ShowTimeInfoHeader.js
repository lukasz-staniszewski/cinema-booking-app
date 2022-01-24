import styles from "./ShowTimeInfoHeader.module.css";

const ShowTimeInfoHeader = (props) =>{
    return(
        <div className={styles.showtimeinfo}>
            <div>
                <div>
                    <i className="fas fa-map-marked-alt"/>
                    <p>Sala {props.showtimeInfo.cinemaHallNumber}</p>
                </div>
                <div>
                    <p>{props.filmInfo.date},</p>
                    <p>godz. {props.showtimeInfo.hour}</p>
                </div>
            </div>
            <div>
                <div>
                    <h2>{props.filmInfo.name}</h2>
                    <h5>{props.filmInfo.director}</h5>
                </div>
                <div className={styles.space}/>
                <div>
                    <ul className={styles.filminfo}>
                        {props.filmInfo.type && <li>{props.filmInfo.type}</li>}
                        {props.filmInfo.productionYear && <li>{props.filmInfo.productionYear}</li>}
                        {props.filmInfo.length && <li>{props.filmInfo.length} min.</li>}
                    </ul>
                </div>
            </div>
        </div>
    );
}
export default ShowTimeInfoHeader;