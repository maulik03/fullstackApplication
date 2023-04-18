import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditMovie() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [movie, setMovie] = useState({
    title: "",
    director: ""
  });

  const { title, director } = movie;

  const handleInputChage = (key,value) => {
    setMovie({ ...movie, [key]:value });
  };

  useEffect(() => {
    loadMovie();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/v1/movie/${id}`, movie);
    navigate("/");
  };

  const loadMovie = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/movie/${id}`);
    setMovie(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit a Movie</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="title" className="form-label">
                Movie Name
              </label>
              <input
                type={"text"}
                className="form-control"
                name="title"
                value={title}
                onChange={(e) => handleInputChage('title', e.target.value)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="director" className="form-label">
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