import React, { PureComponent } from 'react';
import { Button, DialogContainer, TextField } from 'react-md';

export default class LoggingDialogs extends PureComponent {

  constructor(props) {
    super(props);

    this.username = React.createRef();
    this.password = React.createRef();
  }

  state = {
    visible: false
  };

  show = () => {
    this.setState({ visible: true });
  };

  hide = () => {
    this.setState({ visible: false });
  };

  logIn = () => {
      this.hide();
      let result = true;
      /* TODO: check loggin from backend
      let obj = {
        "title": this.titleField.current.value,
        "author": this.authorField.current.value,
        "content": this.contentField.current.value,
        "time": new Date().getTime()
      }
      */
       this.props.setLoginStatus(result)
  }

  render() {
    const { visible } = this.state;

    const actions = [];
    actions.push(<Button flat secondary swapTheming onClick={this.hide}>Cancel</Button>);
    actions.push(<Button flat primary swapTheming onClick={this.logIn}>Confirm</Button>);

    return (
      <div>
        <Button flat secondary swapTheming onClick={this.show}>Sign In</Button>
        <DialogContainer
            id="simple-action-dialog"
            visible={visible}
            onHide={this.hide}
            actions={actions}
            title="Sign In"
        >
          <TextField
              id="Username"
              placeholder="Username"
              ref={this.username}
          />
          <TextField
              id="password"
              label="Enter your password"
              type="password"
              ref={this.password}
          />
        </DialogContainer>
      </div>
    );
  }
}