import Modal from "../components/layout/Modal";
import AuthForm from "./AuthForm";

const AuthScreen = (props) =>{

    return (
        <Modal onClose = {props.onClose}>
            <AuthForm onClose = {props.onClose}/>
        </Modal>)
}

export default AuthScreen;