import {Autocomplete, FontIcon} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    render() {
        let titles = this.props.data.map(item => (
            item.title
        ))

        return (
            <Autocomplete
                id="floating-center-title"
                lineDirection="center"
                placeholder={"Search"}
                leftIcon={<FontIcon>search</FontIcon>}
                className="md-cell--12"
                data={titles}
                filter={Autocomplete.caseInsensitiveFilter}
                onAutocomplete={this.props.onAutocomplete}
                onChange={this.props.onChange}
            />
        )
    }
}