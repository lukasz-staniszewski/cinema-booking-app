import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            message: null
        };
    }

  componentDidMount() {
      fetch("/cinema_client/?client_id=CinemaClient",{headers: {'Content-Type':'application/json', 'Accept': 'application/json'}})
          .then(res=>{
              return res.json();
          })
          .then(myJson=>{
              this.setState({message: myJson})
          })
  }


  render() {
    const messages = this.state.message;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="ui main text container">
              <p>if you do /cinema_client/?client_id=CinemaClient in spring you get:</p>
                <h2>/</h2>
                {messages ? messages.content : 'Loading...'}
            </div>
          </header>
        </div>
    );
  }
}

export default App;