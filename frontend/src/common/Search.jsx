import {Autocomplete, Card, CardText, CardTitle, FontIcon} from "react-md";
import React, {PureComponent} from "react";

export default class Search extends PureComponent {
    render() {
        let titles = this.props.data.map(item => (
            item.title
        ))

        return (
            <Card className="md-block-centered md-cell--10">
                <CardText>
                <Autocomplete
                    id="floating-center-title"
                    lineDirection="center"
                    placeholder={"Search"}
                    leftIcon={<FontIcon>search</FontIcon>}
                    className="md-cell md-cell--bottom "
                    data={titles}
                    filter={Autocomplete.caseInsensitiveFilter}
                    onAutocomplete={this.props.onAutocomplete}
                    onChange={this.props.onChange}
                />
                </CardText>
            </Card>
        );
    }
}