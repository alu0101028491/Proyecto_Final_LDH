package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class filters the movies based on the specific genres that the user wants to watch.
 *  Note that movies may have several genres.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their genres </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
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
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if movie has one of the genres that the user wants to filter - False otherwise
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }

}
