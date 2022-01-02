import {useRef, useState} from "react";

const RegisterForm = (props) =>{
    const [isRegistered, setIsRegistered] = useState(false);
    const inputName = useRef();
    const inputSurname = useRef();
    const inputEmail = useRef();
    const inputPassword = useRef();
    const inputPassword2 = useRef();
    const inputPhone = useRef();
    const [isPasswdError, setIsPasswdError] = useState(false);

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
                console.log("Registration not gucci");
            }
            else{
                console.log("Registration gucci, check mail plx!")
                console.log("Token: ", response.text())
                setIsRegistered(true);
            }
        });

        setIsRegistered(true);

        // TODO: fetch will work but need some repair
    }


    // const RegForm = () =>{
        return(
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
                </div>
                <div>
                    <label htmlFor="password2">Powtórz hasło</label>
                    <input type="password" id="password2" required ref={inputPassword2} className={isPasswdError ? props.styles.error : ""}/>
                </div>
                <div>
                    <label htmlFor="phone">Numer telefonu</label>
                    <input type="tel" id="phone" pattern = "[0-9]{9}" required ref={inputPhone}/>
                </div>
                <button>
                    Zarejestruj
                </button>
            </form>
        )
    // }
/*
    return(
        <Fragment>
        {isRegistered ?
                    <div>
                        <h3>Sukces!</h3>
                        <p>Sprawdź swoją skrzynkę pocztową!</p>
                    </div>
            : <RegForm/>}
        </Fragment>
    )*/
}

export default RegisterForm;