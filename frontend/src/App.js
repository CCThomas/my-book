import React, { Component } from 'react';

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  async componentDidMount() {
    const response = await fetch('http://0.0.0.0:8081?username=React JS');
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

export default App;
