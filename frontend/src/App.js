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
            posts: null,
            visible: false,
            post: null,
            createPostLink: null
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
            .then(resourceJson => this.setState({
                posts: resourceJson.content,
            }));
        fetch("/api/", {mode:"no-cors", method:"GET"})
            .then(response => response.json())
            .then(resourceJson => this.setState({
                createPostLink: resourceJson.links.find(
                    (link) => link.rel === 'createPost')
            }));
    }

    render() {
        if(!this.state.posts) {
            return(
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost link={this.state.createPostLink}/>
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
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost link={this.state.createPostLink}/>
                        <Search data={this.state.posts} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>
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
                        actions={<LoggingDialogs/>}
                    />
                    <div className="md-grid">
                        <NewBlogPost link={this.state.createPostLink}/>
                        <Search data={this.state.posts} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>
                    </div>
                    <div className="md-grid">
                        <BlogPosts data={this.state.posts}/>
                    </div>
                </div>
            );
        }
    }

    onAutocomplete = (result) => {
        for(let item of this.state.posts) {
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
