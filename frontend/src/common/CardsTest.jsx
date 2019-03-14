import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React from "react";

const style = { maxWidth: 320 };

const CardsTest = () => (
    <Card style={style} className="md-block-centered">
        <CardTitle title="A Block post"/>
        <CardText>
            <p>
                Here is some interesting text.
            </p>
            <CardActions>
                <Button icon primary>favorite</Button>
                <Button flat>Comment</Button>
            </CardActions>
        </CardText>
    </Card>
);

export default CardsTest;