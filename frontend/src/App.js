import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import CardsTest from './common/CardsTest'
import LoggingDialogs from "./common/LoggingDialogs";
import Search from "./common/Search"
import NewBlogPost from "./common/NewBlogPost"

class App extends Component {
    render() {
        return (
            <div className="App">
                <Toolbar
                    themed
                    title="Blogging site"
                    children={<Search/>}
                    actions={<LoggingDialogs/>}
                />
                <div className="md-grid">
                    <CardsTest/>
                    <NewBlogPost/>
                </div>
            </div>
        );
    }
}

export default App;
