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
                    <CardsTest title={item.title} author={item.author} content={item.content}/>
                </div>
            )
        );
    }
}