import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import Header from './components/Header'

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            message: null,
            name: "Unknown"
        };
        this.setName = this.setName.bind(this)
    }

    setName(g_name){
        this.setState({name: g_name});
        this.makeFetch(g_name);
    }

    makeFetch(g_name){
        fetch(`/cinema_client/?client_id=${g_name}`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(res => {
                return res.json();
            })
            .then(myJson => {
                this.setState({message: myJson})
            })
    }


    componentDidMount() {
        this.makeFetch(this.state.name);
    }


    render() {

        const messages = this.state.message;
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <Header setName={this.setName}/>
                    <div className="ui main text container">
                        <h3>if you do /cinema_client/?client_id={this.state.name} in backend you get:</h3>
                        {messages ? messages.content : 'Cant fetch from backend...'}
                    </div>
                </header>
            </div>
        );
    }
}

export default App;