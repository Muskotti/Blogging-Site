import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {
    render() {
        const style = { minWidth: 500, maxWidth: 640, marginBottom: 20};
        return (
                <Card style={style} className="md-block-centered">
                    <CardTitle title={this.props.title} subtitle={this.props.author}/>
                    <CardText style={{textAlign: 'left'}}>
                        <p>{this.props.content}</p>
                        <CardActions expander>
                            <Button icon secondary swapTheming>favorite</Button>
                            <Button flat>Comment</Button>
                        </CardActions>
                    </CardText>
                </Card>
        )
    }
}