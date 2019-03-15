import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import CardsTest from './common/CardsTest'
import LoggingDialogs from "./common/LoggingDialogs";
import Search from "./common/Search"
import NewBlogPost from "./common/NewBlogPost"
import testi from "./testi"

import {
    Route,
    BrowserRouter,
    NavLink
} from "react-router-dom";

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
                <BrowserRouter>
                    <div>
                        <NavLink to="/testi">Home</NavLink>
                        <Route path={"/testi"} component={testi}/>
                    </div>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
