import {Button, DialogContainer, TextField} from "react-md";
import React, {PureComponent} from "react";

export default class NewBlogPost extends PureComponent {

    constructor(props) {
        super(props);

        this.titleField = React.createRef();
        this.authorField = React.createRef();
        this.contentField = React.createRef();
    }

    state = {visible: false,};

    show = () => {
        this.setState({ visible: true });
    };

    hide = () => {
        this.setState({visible: false,});
    };

    newPost = () => {
        this.hide();

        let link = this.props.link
        link.href = this.removeDomainFromUrl(link.href);

        let obj = {
            "title": this.titleField.current.value,
            "author": this.authorField.current.value,
            "content": this.contentField.current.value,
            "time": new Date().getTime()
        }

        fetch(link.href, {
            method: this.props.link.type,
            headers: {
                "Content-Type": "application/json"
            },
            body:JSON.stringify(obj)
        })
            .then(response => response.json())
            .then(json => this.props.updatePage(json));
    }

    render() {
        const { visible } = this.state;

        const actions = [];
        actions.push(<Button flat primary swapTheming onClick={this.hide}>Cancel</Button>);
        actions.push(<Button flat secondary swapTheming onClick={this.newPost}>Send</Button>);

        return (
            <div>
                <Button flat secondary swapTheming onClick={this.show}>New Post</Button>
                <DialogContainer
                    id="new-blog-post"
                    visible={visible}
                    onHide={this.hide}
                    actions={actions}
                    title="New Blog Post"
                    width={500}
                >
                    <TextField
                        id="title"
                        label={"Title of the post:"}
                        required={true}
                        ref={this.titleField}
                    />
                    <TextField
                        id="author"
                        label={"Authors name:"}
                        required={true}
                        ref={this.authorField}
                    />
                    <TextField
                        id="content"
                        label="Content:"
                        rows={5}
                        required={true}
                        ref={this.contentField}
                    />
                </DialogContainer>
            </div>
        );
    }

    removeDomainFromUrl(url) {
        let indexOfPathBegin = 0;
        let pathBeginString = '/api';

        for (let i = 0; i < url.length - pathBeginString.length; i++) {
            if (url.substring(i, i + pathBeginString.length) === pathBeginString) {
                indexOfPathBegin = i;
                break;
            }
        }

        return url.substring(indexOfPathBegin);
    }
}