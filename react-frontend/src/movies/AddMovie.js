import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddMovie() {
  let navigate = useNavigate();

  const [movie, setMovie] = useState({
    title: "",
    director: ""
  });

  const { title, director } = movie;

  const handleInputChage = (key, value) => {
    setMovie({ ...movie, [key]: value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/api/v1/movie", movie);
    navigate("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Add a new Movie</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label>
                Movie Name
              </label>
              <input
                type={"text"}
                className="form-control"
                value={title}
                onChange={(e) => handleInputChage('title', e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Movie Director
              </label>
              <input
                type={"text"}
                className="form-control"
                name="director"
                value={director}
                onChange={(e) => handleInputChage('director', e.target.value)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}