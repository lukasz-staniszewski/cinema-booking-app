import React, {useCallback, useEffect, useState} from 'react';
import FilmList from "./components/FilmList";
import logo from './logo.svg';
import './App.css';
import Header from './components/Header'
import data from "./data.json"

const App = () =>{
    const[name, setName] = useState("Unknown");
    const[message, setMessage] = useState(null);

    const setInputNameHandler = (input_value)=>{
        setName(input_value);
    }

    const fetchNameHandler = useCallback(async () => {
        try{
            const response_fetch = await fetch(`/cinema_client/?client_id=${name}`,
                {headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    }});

            if(!response_fetch.ok){
                throw new Error("Fetch not ok!");
            }
            else {
                const new_message_json = await response_fetch.json();
                setMessage(new_message_json.content);
            }
        }
        catch (err){
            console.log("No backend connection only!");
        }
    }, [name]);


    useEffect(()=>{
        if (name){
            fetchNameHandler();
        }
    }, [fetchNameHandler])

    return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <Header setInputNameFunction={setInputNameHandler}/>
                    <div className="ui main text container">
                        <h3>if you do /cinema_client/?client_id={name} in backend you get:</h3>
                        {message ? message : 'Cant fetch from backend...'}
                    </div>
                </header>
                <FilmList films={data}/>
            </div>
        );
}


export default App;