import {Card, CardText, CardTitle, CardActions, Button, Divider} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            likes: 0,
            disable: false,
            expanded: false,
            comments: [],
            isLoading: false,
        }
    }

    like = () => {
        if(this.state.disable === false) {
            this.props.likeAction();
            this.setState((state) => {
                return {likes: state.likes + 1, disable: true}
            });
        } else if (this.state.likes > 0) {
            this.props.dislikeAction();
            this.setState((state) => {
                return {likes: state.likes - 1, disable: false}
            });
        }
    }

    postDate = () => {
        let date = new Date(this.props.time);
        return (
            date.getHours() + ":" + date.getMinutes() + " " + date.getDay() + "." + date.getMonth() + "." + date.getFullYear()
        )
    }

    getComments = () => {
        this.setState({ isLoading: true });
        fetch('/posts/' + this.props.id + '/comments')
            .then(response => response.json())
            .then(data => this.setState({comments: data.content, isLoading: false}))
    }

    comments() {
        if(this.state.isLoading) {
            return <p>Loading comments...</p>
        } else if(this.state.comments.length === 0 && this.state.isLoading === false) {
            return <p>No comments</p>
        } else {
            return this.state.comments.map((item) => <div key={item.id}><p>{item.text}</p><Divider/></div>)
        }
    }

    render() {
        const style = { minWidth: 500, maxWidth: 640, marginBottom: 20};
        return (
            <Card style={style} className="md-block-centered" onExpanderClick={this.getComments}>
                <CardTitle style={{textAlign: 'left'}} title={this.props.title} subtitle={"By: " + this.props.author + " - " + this.postDate()}/>
                <CardText style={{textAlign: 'left'}}>
                    <p>{this.props.content}</p>
                    <Divider/>
                </CardText>
                <CardActions expander>
                    <p style={{margin: '0px', paddingLeft: '8px', paddingRight: '8px'}}>{this.props.likes + this.state.likes}</p>
                    <Button icon secondary={this.state.disable} swapTheming onClick={this.like}>favorite</Button>
                    <Button flat>Comment</Button>
                </CardActions>
                <CardText expandable style={{textAlign: 'left'}}>
                    {this.comments()}
                </CardText>
            </Card>
        )
    }
}