import React, { Component } from 'react';
import {Toolbar} from 'react-md';
import './App.scss';

import LoggingDialogs from "./common/LoggingDialogs";
import Search from "./common/Search"
import BlogPosts from "./BlogPosts"
import KebabMenu from  "./common/KebabMenu"
import ApiRequestHandler from "./ApiRequestHandler";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            posts: null,
            visible: false,
            post: null,
            createPostLink: null,
            loggedIn: false,
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
                        actions={<LoggingDialogs setLoginStatus={this.setLoginStatus}/>}
                    />
                    <p>Loading ...</p>
                </div>
                )
        } else if(this.state.visible) {
            if(this.state.loggedIn) {
                return (
                    <div className="App">
                        <Toolbar
                            themed
                            title="Blogging site"
                            actions={<KebabMenu id="toolbar-kebab-menu" setLoginStatus={this.setLoginStatus} link={this.state.createPostLink} updatePage={this.updatePage}/>}
                        />
                        <div className="md-grid">
                            <Search data={this.state.posts} onAutocomplete={this.onAutocomplete}
                                    onChange={this.onChange}/>
                        </div>
                        <div className="md-grid">
                            <BlogPosts singlePost={this.state.post} deletePost={this.deletePost}/>
                        </div>
                    </div>
                )
            } else {
                return (
                    <div className="App">
                        <Toolbar
                            themed
                            title="Blogging site"
                            actions={<LoggingDialogs setLoginStatus={this.setLoginStatus}/>}
                        />
                        <div className="md-grid">
                            <Search data={this.state.posts} onAutocomplete={this.onAutocomplete}
                                    onChange={this.onChange}/>
                        </div>
                        <div className="md-grid">
                            <BlogPosts singlePost={this.state.post}/>
                        </div>
                    </div>
                )
            }
        } else if(this.state.loggedIn) {
            return (
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        actions={<KebabMenu id="toolbar-kebab-menu" setLoginStatus={this.setLoginStatus} link={this.state.createPostLink} updatePage={this.updatePage}/>}
                    />
                    <div className="md-grid">
                        <Search data={this.state.posts} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>
                    </div>
                    <div className="md-grid">
                        <BlogPosts data={this.state.posts} deletePost={this.deletePost}/>
                    </div>
                </div>
            )
        } else {
            return (
                <div className="App">
                    <Toolbar
                        themed
                        title="Blogging site"
                        actions={<LoggingDialogs setLoginStatus={this.setLoginStatus}/>}
                    />
                    <div className="md-grid">
                        <Search data={this.state.posts} onAutocomplete={this.onAutocomplete} onChange={this.onChange}/>
                    </div>
                    <div className="md-grid">
                        <BlogPosts data={this.state.posts}/>
                    </div>
                </div>
            );
        }
    }

    setLoginStatus = (result) => {
        this.setState({loggedIn: result})
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

    deletePost = (id) => {
        this.setState({posts: this.state.posts.filter(function(item){
            if(item.id !== id) {
                return item
            }
            })
        })
    }

    updatePage = (json) => {
        fetch("/api/posts/"+json.id, {mode:"no-cors", method: "GET"})
            .then(response => response.json())
            .then(resourceJson => this.setState( prevState => ({
                posts: [...prevState.posts, resourceJson]
            })));

    }
}

export default App;
