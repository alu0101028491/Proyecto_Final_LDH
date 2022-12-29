package recommendation_system.ratings;

import recommendation_system.movies.Movie;
import recommendation_system.raters.Rater;
import java.util.ArrayList;
import java.lang.System.Logger;

/**
 * <p>
 *  Class to do many of the calculations focusing on computing averages on movie ratings. - Phase 2
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Get the number of films to analyze. </li>
 *  <li> Get the number of raters to analyze. </li>
 *  <li> Get the average rating given to a movie by its raters. </li>
 *  <li> Get the rating of movies with their average. </li>
 *  <li> Obtain the title of a movie by its ID and vice versa. </li>
 *  </ul>
 *  </p>
 *
 *  @since 26/12/22
 *  @version 1.0
 */
public class SecondRatings {

    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private static System.Logger staticLoggerSecondRatings = System.getLogger(SecondRatings.class.getName());


    /**
     * Default builder
     */
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    /**
     * Builder overload
     * @param movieFile Database of our movies
     * @param ratingFile Database of our ratings
     */
    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings a = new FirstRatings();
        myMovies = a.loadMovies(movieFile);
        myRaters = a.loadRaters(ratingFile);
    }

    /**
     * @return int - Number of films to analyze
     */
    public int getMovieSize() {
        return myMovies.size();
    }

    /**
     * @return int - Number of Raters to analyze
     */
    public int getRaterSize() {
        return myRaters.size();
    }

    /**
     * @param movieID A String variable representing the IMDB ID of the movie
     * @param minimalRaters Minimum number of raters required to make an average
     * @return double - Average rating given to a movie by its raters
     */
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
            }
        }
        if (count >= minimalRaters && count > 0)
            return total / count;

        return 0.0;
    }

    /**
     * @param minimalRaters Minimum number of raters required to make an average
     * @return ArrayList<Rating> - Rating of movies with their average
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        for (Movie i : myMovies) {
            double ave = getAverageByID(i.getID(), minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i.getID(), ave));
        }
        return ratingList;
    }

    /**
     * @param movieID A String variable representing the IMDB ID of the movie
     * @return String - Movie’s title
     */
    public String getTitle(String movieID) {
        for (Movie i : myMovies) {
            if (i.getID().equals(movieID)) {
                return i.getTitle();
            }
        }
        return "The Movie ID was not found!";
    }

    /**
     * @param title A String variable for the movie’s title
     * @return String - Variable representing the IMDB ID of the movie
     */
    public String getID(String title) {
        for (Movie i : myMovies) {
            if (i.getTitle().equals(title)) {
                return i.getID();
            }
        }
        return "NO SUCH TITLE";
    }

    public static void main(String[] args) {
        SecondRatings secondRatings = new SecondRatings("data/ratedmovies_short.csv",
                "data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0068646", 2));
        staticLoggerSecondRatings.log(Logger.Level.INFO, "---TEST---");
        staticLoggerSecondRatings.log(Logger.Level.INFO, secondRatings.getAverageRatings(2));
        //[[6414, 0.0], [68646, 0.0], [113277, 0.0], [1798709, 8.25], [790636, 0.0]]
    }

}
