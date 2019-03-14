import React, { PureComponent } from 'react';
import { Button, DialogContainer, TextField } from 'react-md';

export default class LoggingDialogs extends PureComponent {
  state = { visible: false };

  show = () => {
    this.setState({ visible: true });
  };

  hide = () => {
    this.setState({ visible: false });
  };

  render() {
    const { visible } = this.state;

    const actions = [];
    actions.push(<Button flat secondary swapTheming onClick={this.hide}>Cancel</Button>);
    actions.push(<Button flat primary swapTheming onClick={this.hide}>Confirm</Button>);

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
          />
          <TextField
              id="password"
              label="Enter your password"
              type="password"
          />
        </DialogContainer>
      </div>
    );
  }
}