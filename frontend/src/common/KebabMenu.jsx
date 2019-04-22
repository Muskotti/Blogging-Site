import React, { PureComponent } from 'react';
import { MenuButton, Button } from 'react-md';
import NewBlogPost from "./NewBlogPost";

export default class KebabMenu extends PureComponent {

    logout = () => {
        this.props.setLoginStatus(false)
    }

    render() {
        const actions = [];
        actions.push(<NewBlogPost key={'new-blog-post'}/>);
        actions.push(<Button key={'logout'} flat secondary swapTheming onClick={this.logout}>LogOut</Button>);

        return (
            <MenuButton
                id={this.props.id}
                icon
                className={this.props.className}
                menuItems={actions}
            >
                more_vert
            </MenuButton>
        )
    }
}