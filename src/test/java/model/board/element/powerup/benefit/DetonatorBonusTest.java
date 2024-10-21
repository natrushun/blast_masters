package model.board.element.powerup.benefit;

import model.board.Image;
import model.board.element.character.Player;
import org.junit.Before;
import org.junit.Test;
import view.ui.PlayerDataPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static model.board.Image.PLAYER1_IMMORTAL_IMG;
import static model.board.Size.PLAYER_HEIGHT;
import static model.board.Size.PLAYER_WIDTH;
import static model.board.Velocity.PLAYER_VEL;
import static org.junit.Assert.*;

public class DetonatorBonusTest {

    Player player;
    DetonatorBonus detonatorBonus;
    PlayerDataPanel playerDataPanel;
    @Before
    public void setUp() {
        List<String> player1ImageUrls = Image.PLAYER1_IMG.getImageUrls();
        List<java.awt.Image> player1Images = new ArrayList<>();
        List<String> player1ImmortalImgUrls = PLAYER1_IMMORTAL_IMG.getImageUrls();
        List<java.awt.Image> player1ImmortalImages = new ArrayList<>();
        for (String url : player1ImageUrls) {
            player1Images.add(new ImageIcon(url).getImage());
        }
        for (String url : player1ImmortalImgUrls) {
            player1ImmortalImages.add(new ImageIcon(url).getImage());
        }
        player = new Player(0, 0, PLAYER_WIDTH.getSize(), PLAYER_HEIGHT.getSize(), PLAYER_VEL.getVelocity(),
                player1Images, player1ImmortalImages, true, true, "Player1", null);

        detonatorBonus = new DetonatorBonus(0,0,0,0,0,null,false,false,player);
        playerDataPanel = new PlayerDataPanel(false);
        player.setPlayerDataPanel(playerDataPanel);
    }

    @Test
    public void useTest() {
        detonatorBonus.use();
        assertTrue(player.hasDetonator());
    }

}