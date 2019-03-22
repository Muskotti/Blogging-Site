import {
    Card,
    CardText,
    CardTitle,
    CardActions,
    Button,
    TextField,
    IconSeparator,
    MenuButton,
    ListItem, DialogContainer
} from "react-md";
import React, {PureComponent} from "react";

export default class CardsTest extends PureComponent {

    constructor(props) {
        super(props);

        this.textField = React.createRef();

        this.state = {
            likes: 0,
            disable: false,
            expanded: false,
            comments: [],
            isLoading: false,
            visible: false,
        }
    }

    show = () => {
        this.setState({ visible: true });
    };

    hide = () => {
        this.setState({visible: false,});
    };

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

    showComments() {
        if(this.state.isLoading) {
            return (
                <CardText expandable style={{textAlign: 'left'}}>
                    <p>Loading ...</p>
                </CardText>
            )
        } else if(this.state.comments.length === 0 && this.state.isLoading === false) {
            return (
                <CardText expandable style={{textAlign: 'left'}}>
                    <p>No comments</p>
                    {this.makeComment()}
                </CardText>
            )
        } else {
            return (
                <CardText expandable style={{textAlign: 'left'}}>
                    {this.state.comments.map((item) => <p key={item.id}>{item.text}</p>)}
                    {this.makeComment()}
                </CardText>
            )
        }
    }

    makeComment() {
        return(
            <div>
                <TextField id={'comment-field'} placeholder={"New comment:"} ref={this.textField}/>
                <IconSeparator label={''}>
                    <Button flat secondary swapTheming onClick={this.postComment}>Comment</Button>
                </IconSeparator>
            </div>
        )
    }

    postComment = () => {
        let obj = {
            "text": this.textField.current.value,
        }
        fetch('/posts/' + this.props.id + '/comment', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }, body:JSON.stringify(obj)})
            .then(response => response.json());
        window.location.reload();
        //TODO: live update
    }

    editPost = () => {
        console.log('asd');
        //TODO: save edit
    }

    deleteBlog = () => {
        fetch('/posts/' + this.props.id + '/delete', {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }, body:JSON.stringify(this.props.id)})
            .then(response => response.json())
            .then(result => console.log(result));
        window.location.reload();
        //TODO: live delete
    }

    render() {
        const style = { minWidth: 500, maxWidth: 640, marginBottom: 20};

        const actions = [];
        actions.push(<Button flat primary swapTheming onClick={this.hide}>Cancel</Button>);
        actions.push(<Button flat secondary swapTheming onClick={this.editPost}>Edit</Button>);

        return (
            <div>
                <Card style={style} className="md-block-centered" onExpanderClick={this.getComments}>
                    <CardTitle style={{textAlign: 'left'}} title={this.props.title} subtitle={"By: " + this.props.author + " - " + this.postDate()}/>
                    <CardText style={{textAlign: 'left'}}>
                        <p>{this.props.content}</p>
                    </CardText>
                    <CardActions expander>
                        <p style={{margin: '0px', paddingLeft: '8px', paddingRight: '8px'}}>{this.props.likes + this.state.likes}</p>
                        <Button icon secondary={this.state.disable} swapTheming onClick={this.like}>favorite</Button>
                        <MenuButton
                            icon
                            swapTheming
                            menuItems={[
                                <ListItem key={1} primaryText="Modify" onClick={this.show}/>,
                                <ListItem key={2} primaryText="Delete" onClick={this.deleteBlog}/>,
                            ]}
                            centered
                        >
                            more_vert
                        </MenuButton>
                    </CardActions>
                    {this.showComments()}
                </Card>
                <DialogContainer
                    id="new-blog-post"
                    visible={this.state.visible}
                    onHide={this.hide}
                    actions={actions}
                    title="Edit Blog Post"
                    width={500}
                >
                    <TextField
                        id="title"
                        label={"Title of the post:"}
                        required={true}
                    />
                    <TextField
                        id="author"
                        label={"Authors name:"}
                        required={true}
                    />
                    <TextField
                        id="content"
                        label="Content:"
                        rows={5}
                        required={true}
                    />
                </DialogContainer>
            </div>
        )
    }
}