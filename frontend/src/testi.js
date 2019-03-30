import React, { PureComponent } from "react";

export default class testi extends PureComponent {
    blogPostList = (number) => {
        //<li>{number}</li>
        console.log(number);
    };

    render() {
        const numbers = [1, 2, 3, 4, 5];
        return (
            <div>
                <ul>{this.blogPostList(numbers)}</ul>
            </div>
        );
    }
}