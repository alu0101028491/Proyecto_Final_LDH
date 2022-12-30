package recommendation_system;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import recommendation_system.recommenders.RecommendationRunner;

@DisplayName("Tests for RecomendationRunner")
@Nested
public class RecommendationRunnerTest {

        private RecommendationRunner recommenderTest;

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
}
