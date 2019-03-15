import {TextField, FontIcon} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    render() {
        return (
            <div  className="md-grid">
                <TextField
                    id="floating-center-title"
                    lineDirection="center"
                    placeholder={"Search"}
                    resize={{min: 640, max: 1000}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                />
            </div>
        );
    }
}