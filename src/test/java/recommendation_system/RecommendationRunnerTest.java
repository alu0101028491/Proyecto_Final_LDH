package recommendation_system;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import recommendation_system.recommenders.RecommendationRunner;

@DisplayName("Tests for RecomendationRunner")
@Nested
public class RecommendationRunnerTest {

        private RecommendationRunner recommenderTest;
        
    	private Logger loggerRecommendationRunnerTest = System.getLogger(RecommendationRunner.class.getName());


        @BeforeEach
        void setUp() {
        	recommenderTest = new RecommendationRunner();
        	recommenderTest.setFilename("indexTest.html");
        	recommenderTest.setPathToFile(Paths.get("src/test/resources/"+recommenderTest.getFilename()));
        }

        @Test
        @DisplayName("getters and setters methods")
        void testGettersSetters() {
            assertAll("Verify getters filePath and pathToFile",
                    () -> assertEquals("indexTest.html", recommenderTest.getFilename()),
                    () -> assertEquals(Paths.get("src/test/resources/indexTest.html"), recommenderTest.getPathToFile())
            );
            
            recommenderTest.setFilename("indexTestSetter.html");
        	recommenderTest.setPathToFile(Paths.get("src/test/resources/" + recommenderTest.getFilename()));
            
            assertAll("Verify setters filePath and pathToFile",
            		() -> assertEquals("indexTestSetter.html", recommenderTest.getFilename()),
            		() -> assertEquals(Paths.get("src/test/resources/indexTestSetter.html"), recommenderTest.getPathToFile())
            );
        }
        
        @Test
        @DisplayName("Correct Output print recommendations")
        void testOutput() {
        	
        	String testOutputNormal = "outid size = 10" + System.getProperty("line.separator")
        			+ "<style>" + System.getProperty("line.separator")
        			+ "h2,h3{" + System.getProperty("line.separator")
        			+ "  text-align: center;" + System.getProperty("line.separator")
        			+ "  height: 50px;" + System.getProperty("line.separator")
        			+ "  line-height: 50px;" + System.getProperty("line.separator")
        			+ "  font-family: Arial, Helvetica, sans- serif;" + System.getProperty("line.separator")
        			+ "  background-color: black;" + System.getProperty("line.separator")
        			+ "   color:  #ff6600 }" + System.getProperty("line.separator")
        			+ " table {" + System.getProperty("line.separator")
        			+ "   border-collapse: collapse;" + System.getProperty("line.separator")
        			+ "   margin: auto;}" + System.getProperty("line.separator")
        			+ "table, th, td {" + System.getProperty("line.separator")
        			+ "    border: 2px solid white;" + System.getProperty("line.separator")
        			+ "    font-size: 15px;" + System.getProperty("line.separator")
        			+ "    padding: 2px 6px 2px 6px; }" + System.getProperty("line.separator")
        			+ " td img{" + System.getProperty("line.separator")
        			+ "    display: block;" + System.getProperty("line.separator")
        			+ "    margin-left: auto;" + System.getProperty("line.separator")
        			+ "    margin-right: auto; }" + System.getProperty("line.separator")
        			+ "th {" + System.getProperty("line.separator")
        			+ "    height: 40px;" + System.getProperty("line.separator")
        			+ "    font-size: 18px;" + System.getProperty("line.separator")
        			+ "  background-color: black;" + System.getProperty("line.separator")
        			+ " color: white;" + System.getProperty("line.separator")
        			+ "text-align: center; }" + System.getProperty("line.separator")
        			+ " tr:nth-child(even) {" + System.getProperty("line.separator")
        			+ "     background-color: #f2f2f2; }" + System.getProperty("line.separator")
        			+ "  tr:nth-child(odd) {" + System.getProperty("line.separator")
        			+ "background-color: #cccccc; }" + System.getProperty("line.separator")
        			+ " tr:hover {" + System.getProperty("line.separator")
        			+ " background-color: #666666; " + System.getProperty("line.separator")
        			+ "  color:white;}" + System.getProperty("line.separator")
        			+ "table td:first-child {" + System.getProperty("line.separator")
        			+ " text-align: center; }" + System.getProperty("line.separator")
        			+ " tr {" + System.getProperty("line.separator")
        			+ " font-family: Arial, Helvetica, sans-serif; }" + System.getProperty("line.separator")
        			+ ".rating{" + System.getProperty("line.separator")
        			+ "    color:#ff6600;" + System.getProperty("line.separator")
        			+ "    padding: 0px 10px;" + System.getProperty("line.separator")
        			+ "   font-weight: bold; }" + System.getProperty("line.separator")
        			+ "</style>" + System.getProperty("line.separator")
        			+ "<h2>Wei Xu Brings Best Movies for You! Enjoy!^^</h2>" + System.getProperty("line.separator")
        			+ "<table id = \"rater\">" + System.getProperty("line.separator")
        			+ "<tr>" + System.getProperty("line.separator")
        			+ "<th>Rank</th>" + System.getProperty("line.separator")
        			+ "<th>Poster</th>" + System.getProperty("line.separator")
        			+ "<th>Title & Rating</th>" + System.getProperty("line.separator")
        			+ "<th>Genre</th>" + System.getProperty("line.separator")
        			+ "<th>Country</th>" + System.getProperty("line.separator")
        			+ "</tr>" + System.getProperty("line.separator")
        			+ "<tr><td>1</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt816692\">Interstellar</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.3/10</td><td>Adventure, Drama, Sci-Fi</td><td>USA, UK</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>2</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMzM5NjUxOTEyMl5BMl5BanBnXkFtZTgwNjEyMDM0MDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2278388\">The Grand Budapest Hotel</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.2/10</td><td>Adventure, Comedy, Drama</td><td>USA, Germany, UK</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>3</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTYzNDc2MDc0N15BMl5BanBnXkFtZTgwOTcwMDQ5MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1065073\">Boyhood</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.1/10</td><td>Drama</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>4</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTk0MDQ3MzAzOV5BMl5BanBnXkFtZTgwNzU1NzE3MjE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2267998\">Gone Girl</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.8/10</td><td>Crime, Drama, Mystery</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>5</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BNjE5MzYwMzYxMF5BMl5BanBnXkFtZTcwOTk4MTk0OQ@@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2013&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1454468\">Gravity</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.8/10</td><td>Sci-Fi, Thriller</td><td>USA, UK</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>6</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjIxMjgxNTk0MF5BMl5BanBnXkFtZTgwNjIyOTg2MDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2013&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt993846\">The Wolf of Wall Street</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.7/10</td><td>Biography, Comedy, Crime</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>7</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BOTA5NDYxNTg0OV5BMl5BanBnXkFtZTgwODE5NzU1MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1872181\">The Amazing Spider-Man 2</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Adventure, Fantasy</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>8</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTcwNzAxMDU1M15BMl5BanBnXkFtZTgwNDE2NTU1MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2294449\">22 Jump Street</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Comedy, Crime</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>9</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjEwMDk2NzY4MF5BMl5BanBnXkFtZTgwNTY2OTcwMDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1877832\">X-Men: Days of Future Past</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Adventure, Sci-Fi</td><td>USA, UK, Canada</td></tr> " + System.getProperty("line.separator")
        			+ "<tr><td>10</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMzA2NDkwODAwM15BMl5BanBnXkFtZTgwODk5MTgzMTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1843866\">Captain America: The Winter Soldier</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.5/10</td><td>Action, Adventure, Sci-Fi</td><td>USA</td></tr> " + System.getProperty("line.separator")
        			+ "</table>" + System.getProperty("line.separator")
        			+ "<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>" + System.getProperty("line.separator");
        	
        	
        	
        	ByteArrayOutputStream baosNormal = new ByteArrayOutputStream();
        	ByteArrayOutputStream baosNoRecommendation = new ByteArrayOutputStream();
        	PrintStream ps = new PrintStream(baosNormal);
        	PrintStream old = System.out;
        	System.setOut(ps);
        	
        	recommenderTest.printRecommendationsFor("67");
        	
        	System.out.flush();
        	ps = new PrintStream(baosNoRecommendation);
        	System.setOut(ps);
        	
        	recommenderTest.printRecommendationsFor("62");
        	
        	System.out.flush();
        	System.setOut(old);        	
        	
        	
            assertAll("Verify output is fine",
                    () -> assertEquals(testOutputNormal, baosNormal.toString()),
                    () -> assertTrue(baosNoRecommendation.toString().contains("Sorry, there are no movie recommend for you based on your rating!"))
            );
            
            ///Delete test html file generated for output console
            try {
				Files.delete(recommenderTest.getPathToFile());
			} catch (IOException e) {
				loggerRecommendationRunnerTest.log(Logger.Level.ERROR, e);
			}
        }
        
        @Test
        @DisplayName("Correct HTML generation")
        void testHtmlFileCorrectGeneration() {
        	
        	try {
				recommenderTest.writeHtmlHead();
				
				
				assertAll("Verify output is fine",
						() -> assertTrue(Files.exists(recommenderTest.getPathToFile())),
	                    () -> assertEquals(6, Files.lines(recommenderTest.getPathToFile()).count())
	            );
				
				recommenderTest.writeCss();
				
				assertEquals(47, Files.lines(recommenderTest.getPathToFile()).count());
				
				String htmlFileTest = "<!DOCTYPE html>\n"
						+ "<html lang=\"es\">\n"
						+ "<head>\n"
						+ "  <meta charset=\"UTF-8\">\n"
						+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
						+ "  <title>LPH-Final</title>  <style>\n"
						+ "    h2,h3{\n"
						+ "      text-align: center;\n"
						+ "      height: 50px;\n"
						+ "      line-height: 50px;\n"
						+ "      font-family: Arial, Helvetica, sans- serif;\n"
						+ "      background-color: black;\n"
						+ "       color:  #ff6600 }\n"
						+ "     table {\n"
						+ "       border-collapse: collapse;\n"
						+ "       margin: auto;}\n"
						+ "    table, th, td {\n"
						+ "        border: 2px solid white;\n"
						+ "        font-size: 15px;\n"
						+ "        padding: 2px 6px 2px 6px; }\n"
						+ "     td img{\n"
						+ "        display: block;\n"
						+ "        margin-left: auto;\n"
						+ "        margin-right: auto; }\n"
						+ "    th {\n"
						+ "        height: 40px;\n"
						+ "        font-size: 18px;\n"
						+ "      background-color: black;\n"
						+ "     color: white;\n"
						+ "    text-align: center; }\n"
						+ "     tr:nth-child(even) {\n"
						+ "         background-color: #f2f2f2; }\n"
						+ "      tr:nth-child(odd) {\n"
						+ "    background-color: #cccccc; }\n"
						+ "     tr:hover {\n"
						+ "     background-color: #666666; \n"
						+ "      color:white;}\n"
						+ "    table td:first-child {\n"
						+ "     text-align: center; }\n"
						+ "     tr {\n"
						+ "     font-family: Arial, Helvetica, sans-serif; }\n"
						+ "    .rating{\n"
						+ "        color:#ff6600;\n"
						+ "        padding: 0px 10px;\n"
						+ "       font-weight: bold; }\n"
						+ "    </style>\n"
						+ "</head><body>\n"
						+ "  <h2>Wei Xu, Samir, Dario y Kevin le recomendamos las mejores peliculas</h2>\n"
						+ "  <table id = \"rater\">\n"
						+ "  <tr>\n"
						+ "  <th>Rank</th>\n"
						+ "  <th>Poster</th>\n"
						+ "  <th>Title & Rating</th>\n"
						+ "  <th>Genre</th>\n"
						+ "  <th>Country</th>\n"
						+ "  </tr>\n"
						+ "  <tr>\n"
						+ "      <tr><td>1</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt816692\">Interstellar</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.3/10</td><td>Adventure, Drama, Sci-Fi</td><td>USA, UK</td></tr>\n"
						+ "      <tr><td>2</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMzM5NjUxOTEyMl5BMl5BanBnXkFtZTgwNjEyMDM0MDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2278388\">The Grand Budapest Hotel</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.2/10</td><td>Adventure, Comedy, Drama</td><td>USA, Germany, UK</td></tr>\n"
						+ "      <tr><td>3</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTYzNDc2MDc0N15BMl5BanBnXkFtZTgwOTcwMDQ5MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1065073\">Boyhood</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;9.1/10</td><td>Drama</td><td>USA</td></tr>\n"
						+ "      <tr><td>4</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTk0MDQ3MzAzOV5BMl5BanBnXkFtZTgwNzU1NzE3MjE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2267998\">Gone Girl</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.8/10</td><td>Crime, Drama, Mystery</td><td>USA</td></tr>\n"
						+ "      <tr><td>5</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BNjE5MzYwMzYxMF5BMl5BanBnXkFtZTcwOTk4MTk0OQ@@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2013&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1454468\">Gravity</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.8/10</td><td>Sci-Fi, Thriller</td><td>USA, UK</td></tr>\n"
						+ "      <tr><td>6</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjIxMjgxNTk0MF5BMl5BanBnXkFtZTgwNjIyOTg2MDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2013&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt993846\">The Wolf of Wall Street</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.7/10</td><td>Biography, Comedy, Crime</td><td>USA</td></tr>\n"
						+ "      <tr><td>7</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BOTA5NDYxNTg0OV5BMl5BanBnXkFtZTgwODE5NzU1MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1872181\">The Amazing Spider-Man 2</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Adventure, Fantasy</td><td>USA</td></tr>\n"
						+ "      <tr><td>8</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMTcwNzAxMDU1M15BMl5BanBnXkFtZTgwNDE2NTU1MTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt2294449\">22 Jump Street</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Comedy, Crime</td><td>USA</td></tr>\n"
						+ "      <tr><td>9</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMjEwMDk2NzY4MF5BMl5BanBnXkFtZTgwNTY2OTcwMDE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1877832\">X-Men: Days of Future Past</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.6/10</td><td>Action, Adventure, Sci-Fi</td><td>USA, UK, Canada</td></tr>\n"
						+ "      <tr><td>10</td><td><img src = \"https://ia.media-imdb.com/images/M/MV5BMzA2NDkwODAwM15BMl5BanBnXkFtZTgwODk5MTgzMTE@._V1_SX300.jpg\" width=\"50\" height=\"70\"></td> <td>2014&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt1843866\">Captain America: The Winter Soldier</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;8.5/10</td><td>Action, Adventure, Sci-Fi</td><td>USA</td></tr>\n"
						+ "  </table>\n"
						+ "  <h3>*El Rango de las peliculas está basado en otras calificaciones similares. Disfrute.*</h3>";
				
				List<String> htmlFileTestList = Arrays.asList(htmlFileTest.split("\n"));
				
				recommenderTest.printRecommendationsFor("67");
				
				assertAll("Verify HTML is fine",
						() -> assertEquals(69, Files.lines(recommenderTest.getPathToFile()).count()),
	                    () -> assertEquals(htmlFileTestList, Files.readAllLines(recommenderTest.getPathToFile()))
	            );
				
			} catch (IOException e) {
				loggerRecommendationRunnerTest.log(Logger.Level.ERROR, e);
			}
        }
}
