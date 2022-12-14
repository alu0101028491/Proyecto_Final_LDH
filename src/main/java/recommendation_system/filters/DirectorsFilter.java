package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class filters the movies directed by the specific directors that the user wants to watch.
 *  Note that each movie may have several directors.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on the directors who directed them </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class DirectorsFilter implements Filter {

    private String directors;

    /**
     * Builder overload
     * @param directors A string variable representing the directors of the movie
     */
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }

    /**
     * This method proves if the movie has any of the directors that the user want
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if movie has one of the directors the user wants to filter - False otherwise
     */
    @Override
    public boolean satisfies(String id) {
        String[] dir = directors.split(",");

        for (String i : dir) {
            if (MovieDatabase.getDirector(id).contains(i)) {
                return true;
            }
        }
        return false;
    }

}
