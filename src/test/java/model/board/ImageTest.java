package model.board;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class ImageTest {

    @Test
    public void testGetImageUrl() {
        assertEquals("BOMB_IMG should return the correct single URL", "src/main/resources/assets/bomb.png", Image.BOMB_IMG.getImageUrl());
        assertNull( "BOMB_EXPLODE_IMG should return null for getImageUrl", Image.BOMB_EXPLODE_IMG.getImageUrl());
    }

    @Test
    public void testGetImageUrls() {
        assertNull("BOMB_IMG should return null for getImageUrls", Image.BOMB_IMG.getImageUrls());
        List<String> expectedUrls = Arrays.asList(
                "src/main/resources/assets/bombexplode/be1.png",
                "src/main/resources/assets/bombexplode/be2.png",
                "src/main/resources/assets/bombexplode/be3.png",
                "src/main/resources/assets/bombexplode/be4.png",
                "src/main/resources/assets/bombexplode/be5.png",
                "src/main/resources/assets/bombexplode/be6.png",
                "src/main/resources/assets/bombexplode/be7.png",
                "src/main/resources/assets/bombexplode/be8.png",
                "src/main/resources/assets/bombexplode/be9.png",
                "src/main/resources/assets/bombexplode/be10.png",
                "src/main/resources/assets/bombexplode/be11.png",
                "src/main/resources/assets/bombexplode/be12.png",
                "src/main/resources/assets/bombexplode/be13.png",
                "src/main/resources/assets/bombexplode/be14.png",
                "src/main/resources/assets/bombexplode/be15.png"
        );
        assertEquals("BOMB_EXPLODE_IMG should return the correct list of URLs", expectedUrls, Image.BOMB_EXPLODE_IMG.getImageUrls());
    }

    @Test
    public void testPlayer1ImageUrls() {
        List<String> player1Urls = Arrays.asList(
                "src/main/resources/assets/entities/bomberman1/bomberman1e1.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1e2.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1e3.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1e4.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1n1.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1n2.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1n3.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1s1.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1s2.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1s3.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1w1.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1w2.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1w3.png",
                "src/main/resources/assets/entities/bomberman1/bomberman1w4.png",
                "src/main/resources/assets/rip.png"
        );
        assertEquals("PLAYER1_IMG should return the correct list of URLs for player 1", player1Urls, Image.PLAYER1_IMG.getImageUrls());
        assertNull( "PLAYER1_IMG should return null for getImageUrl", Image.PLAYER1_IMG.getImageUrl());
    }
}