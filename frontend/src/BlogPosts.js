import React, { PureComponent } from "react";

import CardsTest from './common/CardsTest'

export default class BlogPosts extends PureComponent {

    render() {
        if(this.props.data) {
            return (
                this.props.data.map((item) =>
                    <div key={item.id} className="md-block-centered md-cell--10" style={{marginBottom: '16px'}}>
                        <CardsTest
                            id={item.id}
                            title={item.title}
                            author={item.author}
                            content={item.content}
                            time={item.time}
                            likes={item.likes}
                            links={item.links}
                            likeAction={this.onLikeActionBuilder(item)}
                            dislikeAction={this.onDislikeActionBuilder(item)}
                        />
                    </div>
                )
            );
        } else if (this.props.singlePost){
            return (
                <div key={this.props.singlePost.id} className="md-block-centered md-cell--10">
                    <CardsTest
                        id={this.props.singlePost.id}
                        title={this.props.singlePost.title}
                        author={this.props.singlePost.author}
                        content={this.props.singlePost.content}
                        time={this.props.singlePost.time}
                        likes={this.props.singlePost.likes}
                        likeAction={this.onLikeActionBuilder(this.props.singlePost)}
                        dislikeAction={this.onDislikeActionBuilder(this.props.singlePost)}
                    />
                </div>
            )
        }
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