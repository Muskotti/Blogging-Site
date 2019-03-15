import {Card, CardText, CardTitle, CardActions, Button} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {
    render() {
        const style = { minWidth: 500, maxWidth: 640};
        return (
            <div>
                <Card style={style} className="md-block-centered">
                    <CardTitle title={"Title"} subtitle={"By: MIKA"}/>
                    <CardText style={{textAlign: 'left'}}>
                        <p>LOLOLOOLOLOLLOL</p>
                        <CardActions>
                            <Button icon secondary swapTheming>favorite</Button>
                            <Button flat>Comment</Button>
                        </CardActions>
                    </CardText>
                </Card>
            </div>
        )
    }
}