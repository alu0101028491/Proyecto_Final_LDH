package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class is an efficient way to filter movies.
 *  It filters the movies based on the specific genres that the user wants to watch.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their genres </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */
public class GenreFilter implements Filter {

    private String genre;

    /**
     * Builder overload
     * @param genre A string variable that represents the genre of the movies
     */
    public GenreFilter(String genre) {
        this.genre = genre;
    }

    /**
     * Builder overload
     * @param id A string variable representing the genre of the movies
     * Returns a boolean to prove if the criteria (The movie has at least one of the genres that the user wants to filter) is fulfilled
     * @return a boolean to prove if the criteria (The movie has at least one of the genres that the user wants to filter) is fulfilled
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }

}
