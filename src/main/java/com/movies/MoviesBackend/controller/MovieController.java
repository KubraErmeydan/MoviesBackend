package com.movies.MoviesBackend.controller;

import com.movies.MoviesBackend.entities.Actor;
import com.movies.MoviesBackend.entities.Movie;
import com.movies.MoviesBackend.entities.Review;
import com.movies.MoviesBackend.entities.User;
import com.movies.MoviesBackend.repository.MoviesRepository;
import com.movies.MoviesBackend.repository.UserRepository;
import com.movies.MoviesBackend.service.ActorService;
import com.movies.MoviesBackend.service.MovieService;
import com.movies.MoviesBackend.utility.FileUpload;
import com.movies.MoviesBackend.enums.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/admin/movies")
    public String getAllMovies(Model model){
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/movies/all";
    }

    @RequestMapping("/movies")
    public String getHomeMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "user/home";
    }

    @RequestMapping("/movies/{id}")
    public String getMoviePreview(@PathVariable("id") String id, Model model){
        try {
            //System.out.println("MOVIE ID: "+id);
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movie", movie);
            model.addAttribute("new_review", new Review());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user/movie/movie_preview";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addMovieForm(Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("movieForm", new Movie());
        return "admin/movie/movie_form";
    }

    @RequestMapping(value = "/admin/movies/add", method = RequestMethod.POST)
    public String addMovie(Movie movie,
                           BindingResult bindingResult,
                           @RequestParam(value = "file") MultipartFile file,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(movie);
            return "admin/movie/movie_form";
        }
        try {
            // Film ekleme işlemleri
            String path = FileUpload.saveImage(ImageType.MOVIE_POSTER, movie.getName(), file);
            movie.setPoster(path);
            moviesRepository.save(movie);
        } catch (Exception e) {
            e.printStackTrace();
            // Hata durumunda yönlendirme
            //redirectAttributes.addFlashAttribute("error", "Movie creation failed");
            redirectAttributes.addFlashAttribute(movie);
            return "admin/movie/movie_form";
        }
        return "redirect:/movies/all";
    }

    // shows update form
    @RequestMapping(value = "/admin/movies/edit/{id}", method = RequestMethod.GET)
    public String updateMovieForm(@PathVariable("id") String id, Model model) {
        List<Actor> actors = actorService.getActors();
        model.addAttribute("actors", actors);

        try {
            Movie movie = movieService.getMovieById(id);
            model.addAttribute("movieForm", movie);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "admin/movie/movie_form";
    }


    @RequestMapping(value = "/admin/movies/edit/{id}", method = RequestMethod.POST)
    public String updateMovie(@PathVariable("id") String id, Movie movie, BindingResult result, Model model,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              RedirectAttributes redirectAttributes) {


        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(model);
            return "admin/movie/movie_form";
        }
        try {
            if (!file.isEmpty()) {
                String path = FileUpload.saveImage(ImageType.MOVIE_POSTER, movie.getName(), file);
                movie.setPoster(path);
            } else {
                movie.setPoster(movieService.getMovieById(id).getPoster());
            }
            movieService.saveMovie(movie);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(model);
            return "admin/movie/movie_form";
        }
        return "redirect:/admin/movies/";
    }

    @RequestMapping("/admin/movies/delete/{id}")
    public String deleteMovie(@PathVariable("id") String id, Model model) {
        try {
            Movie movie = movieService.getMovieById(id);
            movieService.deleteMovie(movie);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/admin/movies/";
    }


//    @RequestMapping(value = "/movies/{movie_id}/add-to-watchlist", method = RequestMethod.GET)
//    public String addMovieToWatchlist(@AuthenticationPrincipal User principal,
//                                      @PathVariable("movie_id") String movieId) {
//        try {
//            Movie movie = movieService.getMovieById(movieId);
//            User user = userRepository.profile(principal);
//            user.addMovieToWatchlist(movie);
//            userRepository.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/watch-listed-movies";
//    }
//
//    @RequestMapping(value = "/movies/{movie_id}/remove-from-watchlist", method = RequestMethod.GET)
//    public String removeMovieFromWatchlist(@AuthenticationPrincipal User principal,
//                                           @PathVariable("movie_id") String movieId) {
//        try {
//            Movie movie = movieService.getMovieById(movieId);
//            User user = userRepository.profile(principal);
//            user.removeMovieFromWatchlist(movie.getId());
//            userRepository.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/watch-listed-movies";
//    }
//
//    @RequestMapping(value = "/movies/watch-listed-movies/clear-all", method = RequestMethod.GET)
//    public String clearWatchList(@AuthenticationPrincipal User principal, Model model) {
//        try {
//            User user = userRepository.profile(principal);
//            user.setWatchListedMovies(new HashSet<>());
//            userRepository.save(user);
//            Set<Movie> movies = user.getWatchListedMovies();
//            model.addAttribute("movies", movies);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/watch-listed-movies";
//    }
//
//    @RequestMapping(value = "/movies/watch-listed-movies", method = RequestMethod.GET)
//    public String showWatchListedMovies(@AuthenticationPrincipal User principal, Model model) {
//        try {
//            User user = userRepository.profile(principal);
//            Set<Movie> movies = user.getWatchListedMovies();
//            model.addAttribute("movies", movies);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "watchlist";
//    }
//
//    @RequestMapping(value = "/movies/{movie_id}/add-to-favourite", method = RequestMethod.GET)
//    public String addMovieToFavourite(@AuthenticationPrincipal User principal,
//                                      @PathVariable("movie_id") String movieId) {
//        try {
//            Movie movie = movieService.getMovieById(movieId);
//            User user = userRepository.profile(principal);
//            user.addMovieToFavourite(movie);
//            userRepository.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/favourite-movies";
//    }
//
//
//    @RequestMapping(value = "/movies/{movie_id}/remove-from-favourite", method = RequestMethod.GET)
//    public String removeMovieFromFavourite(@AuthenticationPrincipal User principal,
//                                           @PathVariable("movie_id") String movieId) {
//        try {
//            Movie movie = movieService.getMovieById(movieId);
//            User user = userRepository.profile(principal);
//            user.removeMovieFromFavourite(movie.getId());
//            userRepository.save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/favourite-movies";
//    }
//
//    @RequestMapping(value = "/movies/favourite-movies/clear-all", method = RequestMethod.GET)
//    public String clearFavouriteMovies(@AuthenticationPrincipal User principal, Model model) {
//        try {
//            User user = userRepository.profile(principal);
//            user.setFavouriteMovies(new HashSet<>());
//            userRepository.save(user);
//            Set<Movie> movies = user.getWatchListedMovies();
//            model.addAttribute("movies", movies);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/movies/favourite-movies";
//    }
//
//    @RequestMapping(value = "/movies/favourite-movies", method = RequestMethod.GET)
//    public String showFavouriteMovies(@AuthenticationPrincipal User principal, Model model) {
//        try {
//            User user = userRepository.profile(principal);
//            Set<Movie> movies = user.getFavouriteMovies();
//            model.addAttribute("movies", movies);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "favourite";
//    }
}
