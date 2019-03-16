import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {

    postDate = () => {
        let date = new Date(this.props.time);
        return (
            date.getHours() + ":" + date.getMinutes() + " " + date.getDay() + "." + date.getMonth() + "." + date.getFullYear()
        )
    }

    render() {
        const style = { minWidth: 500, maxWidth: 640, marginBottom: 20};
        return (
                <Card style={style} className="md-block-centered">
                    <CardTitle style={{textAlign: 'left'}} title={this.props.title} subtitle={"By: " + this.props.author + " - " + this.postDate()}/>
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