package recommendation_system.movies;

/**
 * <p>
 *  Plain Old Java Object (POJO) class for storing the data about one movie
 *  An immutable passive data object (PDO) to represent item data
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> A constructor with eight parameters to initialize the private variables </li>
 *  <li> Eight getter methods to return the private information </li>
 *  <li> A toString method for representing movie information as a String </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    /**
     * Builder overload
     * @param anID  A String variable representing the IMDB ID of the movie
     * @param aTitle A String variable for the movie’s title
     * @param aYear An integer representing the year
     * @param theGenres String of one or more genres separated by commas
     */
    public Movie(String anID, String aTitle, String aYear, String theGenres) {
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    /**
     * Builder overload
     * @param anID A String variable representing the IMDB ID of the movie
     * @param aTitle A String variable for the movie’s title
     * @param aYear An integer representing the year
     * @param theGenres String of one or more genres separated by commas
     * @param aDirector String of one or more directors of the movie separated by commas
     * @param aCountry  String of one or more countries the film was made in, separated by
     * commas
     * @param aPoster String that is a link to an image of the movie poster if one exists, or “N/A”
     * @param theMinutes An integer for the length of the movie
     */
    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector,
                 String aCountry, String aPoster, int theMinutes) {
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    /**
     * This method gets the ID of the movie
     * @return String - ID associated with this item
     */
    public String getID() {
        return id;
    }

    /**
     * This method gets the title of the movie
     * @return String - Title of this item
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method gets the year of the movie
     * @return int - Year in which this item was published
     */
    public int getYear() {
        return year;
    }

    /**
     * This method gets the genres of the movie
     * @return String - Genres associated with this item
     */
    public String getGenres() {
        return genres;
    }

    /**
     * This method gets the country of the movie
     * @return String - Countries the film was made in, separated by commas
     */
    public String getCountry() {
        return country;
    }

    /**
     * This methods gets the directors of the movie
     * @return String - Directors of the movie separated by commas
     */
    public String getDirector() {
        return director;
    }

    /**
     * This method get the poster of the movie
     * @return String - A link to an image of the movie poster if one exists, or “N/A”
     */
    public String getPoster() {
        return poster;
    }

    /**
     * This method get the minutes of the movie
     * @return int - An integer for the length of the movie
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * This method gets the movie´s information
     * @return String - Item's information
     */
    public String toString() {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
