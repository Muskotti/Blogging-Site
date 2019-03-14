import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import KebabMenu from './common/KebabMenu';
import CardsTest from './common/CardsTest'

class App extends Component {
    render() {
        return (
            <div className="App">
                <Toolbar
                    themed
                    title="Blogging site"
                    actions={<KebabMenu id="toolbar-themed-kebab-menu" />}
                />
                <div className="App">
                    <CardsTest/>
                    <button id="theme-builder-preview-floating-btn" type="button"
                            className="md-btn md-btn--icon md-btn--floating md-btn--fixed md-btn--fixed-br md-pointer--hover md-paper md-paper--2 md-background--secondary md-background--secondary-hover md-inline-block">
                        <div className="md-ink-container"></div>
                        <i className="md-icon material-icons md-text--inherit">email</i></button>
                </div>
            </div>
        );
    }
}

export default App;
