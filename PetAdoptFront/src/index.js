import React from "react";
import { createRoot } from 'react-dom/client';
import { Route, Link, HashRouter as Router, Routes } from "react-router-dom";
import { Navbar, Nav, Container, Button } from "react-bootstrap";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import 'bootstrap/dist/css/bootstrap.min.css';
import { logout } from "./services/auth";
import { Navigate } from "react-router-dom/dist";
import Login from "./components/authorization/Login";
import Register from "./components/authorization/Register";
import Ljubimci from "./components/Ljubimci/Ljubimci";
import AddLjubimac from "./components/Ljubimci/AddLjubimac";



const App = () => {
  if (window.localStorage["jwt"]) {
    return (
      <>
        <Router>
          <Navbar expand bg="dark" variant="dark">

            <Nav>
              <Nav.Link as={Link} to={'/'}>PET ADOPTION</Nav.Link>
              <Nav.Link as={Link} to={'/ljubimci'}>Lubimci</Nav.Link>
              <Button onClick={logout}>Logout</Button>
            </Nav>
          </Navbar>
          <Container style={{ paddingTop: "25px" }}>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="*" element={<NotFound />} />
              <Route path="/ljubimci" element={<Ljubimci />} />
              <Route path="/ljubimci/addLjubimac" element={<AddLjubimac />} />
            </Routes>
          </Container>
        </Router>
      </>
    );
  } else {
    return (
      <>
        <Router>
          <Navbar expand bg="dark" variant="dark">

            <Nav>
            <Nav.Link as={Link} to={'/'}>PET ADOPTION</Nav.Link>
              <Nav.Link as={Link} to={'/ljubimci'}>Lubimci</Nav.Link>
              <Nav.Link as={Link} to="/register">Register</Nav.Link>
              <Nav.Link as={Link} to="/login">Login</Nav.Link>
            </Nav>
          </Navbar>
          <Container style={{ paddingTop: "25px" }}>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/ljubimci" element={<Ljubimci />} />
             
              <Route path="*" element={<Navigate replace to="/login" />} />
            </Routes>
          </Container>
        </Router>
      </>
    );
  }
};

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <App />,
);
