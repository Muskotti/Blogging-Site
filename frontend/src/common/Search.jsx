import {Autocomplete, FontIcon} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    render() {
        let titles = this.props.data.map(item => (
            item.title
        ))

        return (
            <div  className="md-grid">
                <Autocomplete
                    id="floating-center-title"
                    lineDirection="center"
                    placeholder={"Search"}
                    resize={{min: 640, max: 1000}}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                    data={titles}
                    filter={Autocomplete.caseInsensitiveFilter}
                    onAutocomplete={this.props.onAutocomplete}
                    onChange={this.props.onChange}
                />
            </div>
        );
    }
}