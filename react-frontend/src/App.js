import React from 'react';
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./pages/Navbar";
import Home from "./pages/home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddMovie from "./movies/AddMovie";
import EditMovie from "./movies/EditMovie";
import ViewMovie from "./movies/ViewMovie";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addmovie" element={<AddMovie />} />
          <Route exact path="/editmovie/:id" element={<EditMovie />} />
          <Route exact path="/viewmovie/:id" element={<ViewMovie />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;