import classes from "./ProfileSite.module.css";
import UserInfo from "../components/profilesite/UserInfo";
import Reservations from "../components/profilesite/Reservations";
const ProfileSite = () =>{
    return (
        <div className={classes.divbackground}>
            <UserInfo/>
            <Reservations/>
            <a href="https://www.freepik.com">Designed by macrovector / Freepik</a>
        </div>
    )
};

export default ProfileSite;