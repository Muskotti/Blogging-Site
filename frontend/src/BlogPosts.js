import React, { PureComponent } from "react";

import CardsTest from './common/CardsTest'

export default class BlogPosts extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            data: null
        };
    }

    componentDidMount() {
        fetch('/posts')
            .then(response => response.json())
            .then(data => this.setState({data: data.content}))
    }

    render() {
        return (
            this.state && this.state.data && this.state.data.map((item) =>
                <div key={item.id}>
                    <CardsTest
                        id={item.id}
                        title={item.title}
                        author={item.author}
                        content={item.content}
                        time={item.time}
                        likes={item.likes}
                        likeAction={this.onLikeActionBuilder(item)}
                        dislikeAction={this.onDislikeActionBuilder(item)}
                    />
                </div>
            )
        );
    }

    onDislikeActionBuilder(blogPostItem) {
        let dislikeLinkObject = blogPostItem.links.find(
            (linkObject) => linkObject.rel === 'dislike');
        return () => {
            fetch(dislikeLinkObject.href, {method:'post', mode:'no-cors'});
        };
    }

    onLikeActionBuilder(blogPostItem) {
        let likeLinkObject = blogPostItem.links.find(
            (linkObject) => linkObject.rel === 'like');
        return () => {
            fetch(likeLinkObject.href, {method:'post', mode:'no-cors'});
        };
    }
}