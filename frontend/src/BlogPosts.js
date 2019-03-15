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
            .then(data => this.setState({data}))
    }

    NumberList() {
        return (
            this.state.data.map((item) =>
                <CardsTest ref={item}/>
            )
        );
    }

    render() {
        return (
            <div>
                { this.state && this.state.data &&
                    this.NumberList()
                }
            </div>
        );
    }
}