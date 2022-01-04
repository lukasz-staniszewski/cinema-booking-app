import {Fragment, useRef, useState} from "react";

const RegisterForm = (props) =>{
    // eslint-disable-next-line
    const [isRegistered, setIsRegistered] = useState(false);
    const inputName = useRef();
    const inputSurname = useRef();
    const inputEmail = useRef();
    const inputPassword = useRef();
    const inputPassword2 = useRef();
    const inputPhone = useRef();
    const [isPasswdError, setIsPasswdError] = useState(false);
    const [isRegisterError, setIsRegisterError] = useState(false);

    const submitFormHandler = (event) =>{
        event.preventDefault();         // button has default actions which we dont like
        const enteredName = inputName.current.value;
        const enteredSurname = inputSurname.current.value;
        const enteredEmail = inputEmail.current.value;
        const enteredPassword = inputPassword.current.value;
        const enteredPassword2 = inputPassword2.current.value;
        const enteredPhone = inputPhone.current.value;


        if (enteredPassword!==enteredPassword2){
            setIsPasswdError(true);
            return;
        }
        else {
            setIsPasswdError(false);
        }


        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                name: enteredName,
                surname: enteredSurname,
                email: enteredEmail,
                phone: enteredPhone,
                password: enteredPassword
            })
        };
        fetch('registration', requestOptions).then((response)=>{
            if (!response.ok){
                setIsRegisterError(true);
                setIsRegistered(false);
            }
            else{
                setIsRegisterError(false);
                setIsRegistered(true);
            }
        });

    }

        return(
            <Fragment>
                {isRegisterError && <p className={props.styles.registerError}>Wystąpił błąd w trakcie rejestracji, spróbuj ponownie później!</p>}
                {isRegistered && <p className={props.styles.checkMailInfo}>Udało się dokonać rejestracji. Sprawdź swoją skrzynkę pocztową!</p>}
                <form className={props.styles.form} onSubmit={submitFormHandler}>
                    <div>
                        <label htmlFor="name">Imię</label>
                        <input id="name" required ref={inputName}/>
                    </div>
                    <div>
                        <label htmlFor="surname">Nazwisko</label>
                        <input id="surname" required ref={inputSurname}/>
                    </div>
                    <div>
                        <label htmlFor="email">Adres e-mail</label>
                        <input type="email" id="email" required ref={inputEmail}/>
                    </div>
                    <div>
                        <label htmlFor="password">Hasło</label>
                        <input type="password" id="password" required ref={inputPassword} className={isPasswdError ? props.styles.error : ""}/>
                        {isPasswdError && <p className={props.styles.textError}>Wprowadzone hasła muszą się zgadzać!</p>}
                    </div>
                    <div>
                        <label htmlFor="password2">Powtórz hasło</label>
                        <input type="password" id="password2" required ref={inputPassword2} className={isPasswdError ? props.styles.error : ""}/>
                        {isPasswdError && <p className={props.styles.textError}>Wprowadzone hasła muszą się zgadzać!</p>}
                    </div>
                    <div>
                        <label htmlFor="phone">Numer telefonu</label>
                        <input type="tel" id="phone" pattern = "[0-9]{9}" required ref={inputPhone}/>
                    </div>
                    <button>
                        Zarejestruj
                    </button>
                </form>
            </Fragment>
        )
}

export default RegisterForm;