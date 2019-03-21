import {Card, CardText, CardTitle, CardActions, Button, IconSeparator} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            likes: 0
        }
    }

    like = () => {
        this.props.likeAction();
        this.setState((state) => {
            return {likes: state.likes + 1}
        });
    }

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
                            <IconSeparator label={this.props.likes + this.state.likes} style={{color: 'White', fontFamily: 'Roboto'}}>
                                <Button icon secondary swapTheming onClick={this.like}>favorite</Button>
                            </IconSeparator>
                            <Button flat>Comment</Button>
                        </CardActions>
                    </CardText>
                </Card>
        )
    }
}