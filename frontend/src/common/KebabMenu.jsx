import React, { PureComponent } from 'react';
import {MenuButton, ListItem} from 'react-md';
import NewBlogPost from "./NewBlogPost";

export default class KebabMenu extends PureComponent {

    constructor(props) {
        super(props);

        this.state = {
            show: false,
        }
    }

    render() {
        return (
            <div>
                <MenuButton
                    id={this.props.id}
                    icon
                    className={this.props.className}
                    menuItems={[
                        <ListItem key={'new-blog-post'} primaryText="New Post" onClick={this.showNewBlogPost}/>,
                        <ListItem key={'logout'} primaryText="Logout" onClick={this.logout}/>,
                    ]}
                >
                    more_vert
                </MenuButton>
                <NewBlogPost key={'new-blog-post'} link={this.props.link} updatePage={this.props.updatePage} show={this.state.show} hide={this.hide}/>
            </div>
        )
    }

    logout = () => {
        this.props.setLoginStatus(false)
    }

    hide = () => {
        this.setState({show: false})
    }

    showNewBlogPost = () => {
        this.setState({show: true})
    }
}