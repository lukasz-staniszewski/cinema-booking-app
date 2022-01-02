import {useContext, useRef} from "react";
import AuthContext from "../components/store/auth-context.js";
import {useNavigate} from 'react-router-dom';

const LoginForm = (props) =>{
    const emailInputRef = useRef();
    const passwordInputRef = useRef();
    const authCtx = useContext(AuthContext);
    const navigate = useNavigate()

    const submitFormHandler = (event) =>{
        event.preventDefault();         // button has default actions which we dont like

        const enteredEmail = emailInputRef.current.value;
        const enteredPassword = passwordInputRef.current.value;
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                username: enteredEmail,
                password: enteredPassword
            })
        };
        fetch('login', requestOptions).then((response)=>{
            if (!response.ok){
                console.log("Login not gucci");
            }
            else {
                return response
            }}).then((data)=>{
                console.log("Login gucci!")
                authCtx.login(data.headers.get('Authorization'));
                navigate('/');
                props.onClose();
        });
    }

    return(
        <form className={props.styles.form} onSubmit={submitFormHandler}>
            <div>
                <label htmlFor="email">Adres e-mail</label>
                <input type="email" id="email" required ref={emailInputRef}/>
            </div>
            <div>
                <label htmlFor="password">Hasło</label>
                <input type="password" id="password" required ref={passwordInputRef}/>
            </div>
            <button>
                Zaloguj
            </button>
        </form>
    )
}

export default LoginForm;