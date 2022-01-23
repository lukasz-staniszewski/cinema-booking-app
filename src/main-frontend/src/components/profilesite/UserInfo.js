import classes from "./UserInfo.module.css"
import classes2 from "./../../pages/ProfileSite.module.css"
import {useContext, useEffect, useState} from "react";
import AuthContext from "../store/auth-context";
import jwt_decode from "jwt-decode";
const UserInfo = () =>{
    const authCtx = useContext(AuthContext);
    const [userAccount, setUserAccount] = useState([]);

    const mailFromJWT = () =>{
        if (authCtx.isUserLogged) {
            const decoded = jwt_decode(authCtx.token);
            return decoded.sub;
        }
        else{
            console.log("User is not logged to get mail from token!");
        }
    }

    const fetchUserDetails = async () =>{
        try{
            const response_fetch = await fetch (`/clients/client/email?email=${mailFromJWT()}`, {headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': authCtx.token
                    }});
            if(!response_fetch.ok){
                throw new Error("Fetch not ok!");
            }
            else{
                let response = await response_fetch.json()
                console.log(response)
                setUserAccount(response);
            }
        }
        catch (err){
            console.log(err);
            console.log("Cant connect to db!")
        }
    };

    useEffect( () =>{
        fetchUserDetails();
        // eslint-disable-next-line
    },[]);

    return (
        <div className={`${classes2.rounded} ${classes.wrapper}`}>
            <div className={classes.logo}>
                <i className="fas fa-user-alt"/>
            </div>
            <div className={classes.userinfo}>
                <div className={classes.data}>
                    <p>{userAccount.name}</p>
                    <p>{userAccount.surname}</p>
                </div>
                <div className={classes.mail}>
                    <p>{userAccount.email}</p>
                </div>
            </div>
        </div>
    )
};

export default UserInfo;