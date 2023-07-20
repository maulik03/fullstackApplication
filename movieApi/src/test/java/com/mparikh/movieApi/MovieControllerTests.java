package com.mparikh.movieApi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mparikh.movieApi.controller.MovieController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mparikh.movieApi.model.Movie;
import com.mparikh.movieApi.repository.MovieRepository;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MovieController.class)
@ActiveProfiles("test")
public class MovieControllerTests {
    private MockMvc mockMvc;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieController movieController;

    private List<Movie> movies = new ArrayList<>();

    private Movie movie = new Movie();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie.setTitle("Avengers: Endgame");
        movie.setDirector("Anthony Russo, Joe Russo");
        movies.add(movie);
    }

    @Test
    public void testGetAllMovies() throws Exception {
        doReturn(movies).when(movieRepository).findAll();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testCreateMovies() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(movie);
        doReturn(movie).when(movieRepository).save(movie);
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/movie").contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testGetMovieById() throws Exception {
        Long id = 1L;
        movie.setId(id);
        doReturn(java.util.Optional.ofNullable(movie)).when(movieRepository).findById(id);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/movie/" + id).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }

    @Test
    public void testUpdateMovies() throws Exception {
        Long id = 1L;
        movie.setId(id);
        movie.setTitle("Avengers: Infinity War");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(movie);
        doReturn(Optional.ofNullable(movie)).when(movieRepository).findById(id);
        doReturn(movie).when(movieRepository).save(movie);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/movie/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn();
    }
}
