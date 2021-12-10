import {
    useRef
} from "react";

import classes from "./Header.module.css";



const Header=(props)=>{
    const name = useRef("Unknown");

    const changeName = (event) =>{
        event.preventDefault();   // reload prevention
        name.current.value = "";
        props.setInputNameFunction(name.current.value);
    }

    return (
        <form onSubmit={changeName}>
            <label htmlFor="name">Type name:</label>
            <input className={classes.upper} ref={name} type="text" id="name" required/>
        </form>
    );
};

export default Header;