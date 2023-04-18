import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewMovie() {
  const [movie, setMovie] = useState({
    title: "",
    director: ""
  });

  const { id } = useParams();

  useEffect(() => {
    loadMovie();
  }, []);

  const loadMovie = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/movie/${id}`);
    setMovie(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Movie Details</h2>

          <div className="card">
            <div className="card-header">
              Details of a movie id : {movie.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Movie Name:</b>
                  {movie.title}
                </li>
                <li className="list-group-item">
                  <b>Movie Director:</b>
                  {movie.director}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}