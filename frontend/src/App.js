import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import Dashboard from "./components/Dashboard"

export default function App() {
  return (
    <Router>
      <div>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/book">Book</Link>
          </li>
        </ul>

        <hr />
        <Switch>
          <Route exact path="/">
            <Dashboard key="home"/>
          </Route>
          <Route path="/book">
            <Dashboard key="book" username="Book Page"/>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}