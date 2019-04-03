import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import LoggingDialogs from "./common/LoggingDialogs";
import Search from "./common/Search"
import NewBlogPost from "./common/NewBlogPost"
import BlogPosts from "./BlogPosts"
import ApiRequestHandler from "./ApiRequestHandler";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null,
            visible: false,
            post: null,
        };
    }

    show = () => {
        this.setState({ visible: true });
    };

    hide = () => {
        this.setState({visible: false,});
    };

    componentDidMount() {
        fetch("/api/posts/", {mode:"no-cors", method: "GET"})
            .then(response => response.json())
            .then(resourceJson => this.setState({data: resourceJson.content}));
    }

    render() {
        if(!this.state.data) {
            return(
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost/>
                    </div>
                    <p>Loading ...</p>
                </div>
                )
        } else if(this.state.visible) {
            return (
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        children={<Search data={this.state.data} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>}
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost/>
                    </div>
                    <BlogPosts singlePost={this.state.post}/>
                </div>
                )
        } else {
            return (
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        children={<Search data={this.state.data} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>}
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost/>
                    </div>
                    <BlogPosts data={this.state.data}/>
                </div>
            );
        }
    }

    onAutocomplete = (result) => {
        for(let item of this.state.data) {
            if(item.title === result) {
                this.setState({
                    post: item
                })
            }
        }
        this.show()
    }

    onChange = (item) => {
        if(item === '') {
            this.hide()
        }
    }
}

export default App;
