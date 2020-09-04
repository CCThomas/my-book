import React, { Component } from 'react';

class Dashboard extends Component {
  state = {
    isLoading: true
  }

  async componentDidMount() {
    var username = "";
    if (this.props.username != undefined) {
      username = this.props.username;
    }

    const response = await fetch('http://0.0.0.0:8081?username=' + username);
    const text = await response.text();
    this.setState({ text: text, isLoading: false });
  }

  render() {
    const {isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
        <header>
          <div>
            <h1>{this.state.text}</h1>
          </div>
        </header>
      </div>
    );
  }
}

export default Dashboard;
