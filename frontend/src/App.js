import React, { Component } from 'react';
import { NavigationDrawer } from 'react-md';
import logo from './logo.svg';
import './App.scss';

class App extends Component {
    state = {};

    componentDidMount() {
        setInterval(this.hello, 250);
    }

    hello = () => {
        fetch('/hello')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    render() {
        return (
            <NavigationDrawer
                toolbarTitle="react-md with create-react-app v2"
                drawerTitle="react-app"
            >
                <div className="App">
                    <header className="App-header">
                        <div className="App">
                            <header className="App-header">
                                <img src={logo} className="App-logo" alt="logo"/>
                                <h1 className="App-title">{this.state.message}</h1>
                            </header>
                            <p className="App-intro">
                                To get started, edit <code>src/App.js</code> and save to reload.
                            </p>
                        </div>
                    </header>
                </div>
            </NavigationDrawer>
        );
    }
}

export default App;
