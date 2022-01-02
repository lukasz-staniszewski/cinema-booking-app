import {useContext} from "react";

import Modal from "./Modal";
import AuthForm from "../auth/AuthForm";
import AuthContext from "../components/store/auth-context.js";

const AuthScreen = (props) =>{
    const authCtx = useContext(AuthContext);

    return (
        <Modal onClose = {props.onClose}>
            <AuthForm onClose = {props.onClose}/>
        </Modal>)
}

export default AuthScreen;