package recommendation_system.ratings;

import recommendation_system.movies.Movie;
import recommendation_system.edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import recommendation_system.raters.EfficientRater;
import recommendation_system.raters.Rater;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System.Logger;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 9th, 2019
 *
 *  Description:  -------------------STEP ONE--------
 *                 In this exercise, you will use the provided classes
 *                 Movie.java, Rating.java, and Rater.java and read in
 *                 and store information about movies and ratings of movies
 *                 by different movie raters to answer simple questions
 *                 about both movies and ratings.
 *
 ****************************************************************/

public class FirstRatings {

    //private static Logger logger = Logger.getLogger(FirstRatings.class.getName());
    private Logger logger = System.getLogger(FirstRatings.class.getName());

    /**
     * This method process every record from the CSV file
     * and return an ArrayList of type Movie with all of the movie data from the file.
     * @param fileName A file of movie information
     * @return ArrayList of type Movie with all of the movie data from the file
     */
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {

            Movie m = new Movie(i.get("id"), i.get("title"), i.get("year"),
                    i.get("genre"), i.get("director"), i.get("country"),
                    i.get("poster"), Integer.parseInt(i.get("minutes")));

            movies.add(m);
        }
        return movies;
    }

    /**
     * Determine the maximum number of movies by any director,
     * and who the directors are that directed that many movies.
     * Remember that some movies may have more than one director.
     */
    public void testLoadMovies() {

        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");

        logger.log(Logger.Level.INFO, "The size of movie list is = " + movies.size());

        //System.out.println("The size of movie list is = " + movies.size());
        //        for (Movie i : movies) {
        //            if (i.getGenres().contains("Comedy")) {
        //                System.out.println("Comedy = : " + i);
        //            }
        //        }
        //        for (Movie i : movies) {
        //            if (i.getMinutes() >= 150) {
        //                System.out.println("MIN >= 150 : " + i);
        //            }
        //        }

        HashMap<String, ArrayList<Movie>> mapDirector = new HashMap<>();
        for (Movie i : movies) {
            //One movie may have many directors.
            String director = i.getDirector();
            //make a list of directos' name split into a string array
            String[] directors = director.split(", ");

            for (String j : directors) {
                //System.out.println("movie " + i.getID() + " : " + j);
                if (!mapDirector.containsKey(j)) {
                    ArrayList<Movie> a = new ArrayList<>();
                    a.add(i);
                    mapDirector.put(j, a);
                } else {
                    ArrayList<Movie> a = mapDirector.get(j);
                    a.add(i);
                    mapDirector.put(j, a);
                }
            }
        }
        //        System.out.println("Print HashMap--directorList");
        //        for (String i : directorList.keySet()) {
        //            System.out.println(i + " : " + directorList.get(i).size() + " : " + directorList.get(i));
        //        }

        int maxNumMovieByDirector = 0;
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() > maxNumMovieByDirector) {
                maxNumMovieByDirector = mapDirector.get(key).size();
            }
        }

        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() == maxNumMovieByDirector) {
                logger.log(Logger.Level.INFO, "The director who produce the most movie is : "
                        + maxNumMovieByDirector + " : " + key);
            }
        }
    }

    /**
     * Load Raters - Raters is class for storing the data about one rating of an item.
     * @param fileName Ratings file
     * @return ArrayList of Raters
     */
    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> raters = new ArrayList<>();// why no need <PlainRater>?
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {

            double rating = Double.parseDouble(i.get("rating"));

            int count = 0;
            for (Rater j : raters) {
                if (j.getID().contains(i.get("rater_id"))) {
                    j.addRating(i.get("movie_id"), rating);
                    count++;
                    break;
                }
            }
            if (count == 0) {
                // HERE IS THE ONLY CHANGE I MADE FOR THE INTERFACE
                // WHY NO NEED CHANGE OTHERS SUCH AS LINE 120.
                EfficientRater m = new EfficientRater(i.get("rater_id"));
                m.addRating(i.get("movie_id"), rating);
                raters.add(m);// here is not correct when i did not write implement the rater interface in EfficientRater
            }
        }
        return raters;
    }

    public void testLoadRaters(String filename) {
        ArrayList<Rater> raters = loadRaters(filename);
        logger.log(Logger.Level.INFO, "The size of rater list is = " + raters.size());

        //Add code to find the number of ratings for a particular rater
        // you specify in your code. For example, if you run this code
        // on the rater whose rater_id is 2
        for (Rater i : raters) {
            if (i.getID().equals("2")) {

                logger.log(Logger.Level.INFO, "USER # " + i.getID() + " : "
                        + i.numRatings() + " ratings");

                ArrayList<String> rating = i.getItemsRated();
                for (String j : rating) {
                    logger.log(Logger.Level.INFO, "movie_id: " + j + " ");
                    logger.log(Logger.Level.INFO, i.getRating(j) + " rating");
                }
            }
        }
        //Add code to find the maximum number of ratings by any rater.
        // Determine how many raters have this maximum number of ratings
        // and who those raters are.If you run this code on the file ratings_short.csv,
        // you will see rater 2 has three ratings, the maximum number of ratings of
        // all the raters,and that there is only one rater with three ratings.
        int max = 0;
        for (Rater i : raters) {
            if (i.numRatings() > max) {
                max = i.numRatings();
            }
        }
        for (Rater i : raters) {
            if (i.numRatings() == max) {
                logger.log(Logger.Level.INFO, "The maximum rate is from USER # " +
                        i.getID() + " : " + i.numRatings() + " ratings");
            }
        }
        // Add code to find the number of ratings a particular movie has.
        // If you run this code on the file ratings_short.csv for the movie “1798709”,
        // you will see it was rated by four raters.
        int count = 0;
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            if (rating.contains("1798709")) {
                count++;
                logger.log(Logger.Level.INFO, count + " : " + "id = " + i.getID() + rating);
            }
        }
        logger.log(Logger.Level.INFO, "The total # of " + "1798709" + " that been rated is " + count);

        // Add code to determine how many different movies have been rated
        // by all these raters. If you run this code on the file ratings_short.csv,
        // you will see there were four movies
        ArrayList<String> differentMovie = new ArrayList<>();
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            for (String j : rating) {
                if (!differentMovie.contains(j)) {
                    differentMovie.add(j);
                }
            }
        }
        logger.log(Logger.Level.INFO, "The total # of movie is " + differentMovie.size());
    }

    public static void main(String[] args) {
        FirstRatings a = new FirstRatings();
        System.out.println("-------------------MOVIES-------------------");
        a.testLoadMovies();
        System.out.println();
        System.out.println();
        System.out.println("-------------------RATERS-------------------");
        a.testLoadRaters("data/ratings_short.csv");
    }
}