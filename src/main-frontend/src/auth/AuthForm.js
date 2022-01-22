import {useState} from "react";
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import styles from "./AuthForm.module.css"

const AuthForm = (props) =>{
    const [isLogin, setIsLogin] = useState(true);

    const setIsLoginHandler = () =>{
        setIsLogin((prevState)=>!prevState);
    }

    return(
        <section>
            <button className={styles.quit} onClick={props.onClose}>X</button>
            <h1>{isLogin ? "Logowanie":"Rejestracja"}</h1>
            {isLogin? <LoginForm styles={styles} onClose = {props.onClose}/> : <RegisterForm styles={styles}/>}
            <button className={styles.redirectbtn} type="button" onClick={setIsLoginHandler}>
                {isLogin? "Nie masz konta? Zarejestruj się!":"Masz już konto? Przejdź do logowania!"}
            </button>
        </section>
    )
}

export default AuthForm;