import Modal from "./Modal";
import AuthForm from "../auth/AuthForm";

const AuthScreen = (props) =>{

    return (
        <Modal onClose = {props.onClose}>
            <AuthForm onClose = {props.onClose}/>
        </Modal>)
}

export default AuthScreen;