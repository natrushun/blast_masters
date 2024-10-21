package model.board;

import java.util.Arrays;
import java.util.List;

/**
 * Enum representing image URLs for various elements in the game.
 */
public enum Image {

    BOMB_IMG("src/main/resources/assets/bomb.png"),

    BOMB_EXPLODE_IMG(Arrays.asList(
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
    )),
    FLAME_RIGHT_IMG("src/main/resources/assets/flame_right.png"),
    FLAME_DOWN_IMG("src/main/resources/assets/flame_down.png"),
    FLAME_UP_IMG("src/main/resources/assets/flame_up.png"),
    FLAME_LEFT_IMG("src/main/resources/assets/flame_left.png"),
    PLAYER1_IMG(Arrays.asList(
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
    )),
    PLAYER1_IMMORTAL_IMG(Arrays.asList(
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1e1.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1e2.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1e3.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1e4.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1n1.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1n2.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1n3.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1s1.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1s2.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1s3.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1w1.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1w2.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1w3.png",
            "src/main/resources/assets/entities/bomberman1/immortalityBonusActive/bomberman1w4.png"
    )),
    PLAYER2_IMG(Arrays.asList(
            "src/main/resources/assets/entities/bomberman2/bomberman2e1.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2e2.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2e3.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2e4.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2n1.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2n2.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2n3.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2s1.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2s2.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2s3.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2w1.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2w2.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2w3.png",
            "src/main/resources/assets/entities/bomberman2/bomberman2w4.png",
            "src/main/resources/assets/rip.png"
    )),

    PLAYER2_IMMORTAL_IMG(Arrays.asList(
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2e1.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2e2.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2e3.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2e4.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2n1.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2n2.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2n3.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2s1.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2s2.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2s3.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2w1.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2w2.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2w3.png",
            "src/main/resources/assets/entities/bomberman2/immortalityBonusActive/bomberman2w4.png"
    )),
    BACKGROUND_IMG_MAP1("src/main/resources/assets/tiles/map1/grass2.png"),
    BOX_IMG_MAP1("src/main/resources/assets/tiles/map1/box_pixel.png"),
    WALL_IMG_MAP1("src/main/resources/assets/tiles/map1/wall_pixel.png"),
    BACKGROUND_IMG_MAP2("src/main/resources/assets/tiles/map2/fire_background.png"),
    BOX_IMG_MAP2("src/main/resources/assets/tiles/map2/fire_box.png"),
    WALL_IMG_MAP2("src/main/resources/assets/tiles/map2/fire_wall.png"),
    BACKGROUND_IMG_MAP3("src/main/resources/assets/tiles/map3/ice_background.png"),
    BOX_IMG_MAP3("src/main/resources/assets/tiles/map3/ice_box.png"),
    WALL_IMG_MAP3("src/main/resources/assets/tiles/map3/ice_wall.png"),
    BOMB_UP_BONUS_IMG("src/main/resources/assets/bonuses/bomb_up.png"),
    BIGGER_RANGE_BONUS_IMG("src/main/resources/assets/bonuses/bigger_range.png"),
    BOX_BONUS_IMG("src/main/resources/assets/bonuses/box.png"),
    ROLLER_BONUS_IMG("src/main/resources/assets/bonuses/roller.png"),
    SLOW_DOWN_BONUS_IMG("src/main/resources/assets/bonuses/slow_down.png"),
    DETONATOR_BONUS_IMG("src/main/resources/assets/bonuses/detonator.png"),
    GHOST_BONUS_IMG("src/main/resources/assets/bonuses/ghost.png"),
    IMMORTALITY_BONUS_IMG("src/main/resources/assets/bonuses/immortality.png"),
    IMMEDIATELY_BONUS_IMG("src/main/resources/assets/bonuses/immediately.png"),
    PACIFIST_BONUS_IMG("src/main/resources/assets/bonuses/pacifist.png"),
    SMALLERRANGE_BONUS_IMG("src/main/resources/assets/bonuses/smallerrange.png"),
    BOMB_UP_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/bomb_up_bonus.png"),
    BIGGER_RANGE_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/bigger_range_bonus.png"),
    BOX_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/box_bonus.png"),
    ROLLER_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/roller_bonus.png"),
    SLOW_DOWN_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/slow_down_bonus.png"),
    DETONATOR_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/detonator_bonus.png"),
    GHOST_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/ghost_bonus.png"),
    IMMORTALITY_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/immortality_bonus.png"),
    IMMEDIATELY_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/immediately_bonus.png"),
    PACIFIST_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/pacifist_bonus.png"),
    SMALLERRANGE_BONUS_BUBBLE("src/main/resources/assets/bonuses/inBubble/smallerrange_bonus.png"),
    STATIC_BONUSES_IMG(Arrays.asList(
            "src/main/resources/assets/bonuses/roller.png",
            "src/main/resources/assets/bonuses/detonator.png"

    )),
    DYNAMIC_BONUSES_IMG(Arrays.asList(
            "src/main/resources/assets/bonuses/slow_down.png",
            "src/main/resources/assets/bonuses/ghost.png",
            "src/main/resources/assets/bonuses/immortality.png",
            "src/main/resources/assets/bonuses/immediately.png",
            "src/main/resources/assets/bonuses/pacifist.png",
            "src/main/resources/assets/bonuses/smallerrange.png"

    )),
    MONSTER_IMG_MAP1(Arrays.asList(
            "src/main/resources/assets/entities/monster1/monstere1.png",
            "src/main/resources/assets/entities/monster1/monstere2.png",
            "src/main/resources/assets/entities/monster1/monstere3.png",
            "src/main/resources/assets/entities/monster1/monstere4.png",
            "src/main/resources/assets/entities/monster1/monstern1.png",
            "src/main/resources/assets/entities/monster1/monstern2.png",
            "src/main/resources/assets/entities/monster1/monstern3.png",
            "src/main/resources/assets/entities/monster1/monsters1.png",
            "src/main/resources/assets/entities/monster1/monsters2.png",
            "src/main/resources/assets/entities/monster1/monsters3.png",
            "src/main/resources/assets/entities/monster1/monsterw1.png",
            "src/main/resources/assets/entities/monster1/monsterw2.png",
            "src/main/resources/assets/entities/monster1/monsterw3.png",
            "src/main/resources/assets/entities/monster1/monsterw4.png"
    )),
    MONSTER_IMG_MAP2(Arrays.asList(
            "src/main/resources/assets/entities/monster2/monstere1.png",
            "src/main/resources/assets/entities/monster2/monstere2.png",
            "src/main/resources/assets/entities/monster2/monstere3.png",
            "src/main/resources/assets/entities/monster2/monstere4.png",
            "src/main/resources/assets/entities/monster2/monstern1.png",
            "src/main/resources/assets/entities/monster2/monstern2.png",
            "src/main/resources/assets/entities/monster2/monstern3.png",
            "src/main/resources/assets/entities/monster2/monsters1.png",
            "src/main/resources/assets/entities/monster2/monsters2.png",
            "src/main/resources/assets/entities/monster2/monsters3.png",
            "src/main/resources/assets/entities/monster2/monsterw1.png",
            "src/main/resources/assets/entities/monster2/monsterw2.png",
            "src/main/resources/assets/entities/monster2/monsterw3.png",
            "src/main/resources/assets/entities/monster2/monsterw4.png"
    )),


    MONSTER_IMG_MAP3(Arrays.asList(
            "src/main/resources/assets/entities/monster3/monstere1.png",
            "src/main/resources/assets/entities/monster3/monstere2.png",
            "src/main/resources/assets/entities/monster3/monstere3.png",
            "src/main/resources/assets/entities/monster3/monstere4.png",
            "src/main/resources/assets/entities/monster3/monstern1.png",
            "src/main/resources/assets/entities/monster3/monstern2.png",
            "src/main/resources/assets/entities/monster3/monstern3.png",
            "src/main/resources/assets/entities/monster3/monsters1.png",
            "src/main/resources/assets/entities/monster3/monsters2.png",
            "src/main/resources/assets/entities/monster3/monsters3.png",
            "src/main/resources/assets/entities/monster3/monsterw1.png",
            "src/main/resources/assets/entities/monster3/monsterw2.png",
            "src/main/resources/assets/entities/monster3/monsterw3.png",
            "src/main/resources/assets/entities/monster3/monsterw4.png"
    )),

    GHOST_MONSTER_IMG(Arrays.asList(
            "src/main/resources/assets/entities/ghost/ghost1.png",
            "src/main/resources/assets/entities/ghost/ghost2.png",
            "src/main/resources/assets/entities/ghost/ghost3.png",
            "src/main/resources/assets/entities/ghost/ghost4.png"
    )),

    SEMI_INTELLIGENT_MONSTER_IMG(Arrays.asList(
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_e1.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_e2.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_e3.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_e4.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_n1.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_n2.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_n3.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_s1.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_s2.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_s3.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_w1.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_w2.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_w3.png",
            "src/main/resources/assets/entities/semiintelligent/semiintelligent_monster_w4.png"
    )),

    INTELLIGENT_MONSTER_IMG(Arrays.asList(
            "src/main/resources/assets/entities/intelligent/r1.png",
            "src/main/resources/assets/entities/intelligent/r2.png",
            "src/main/resources/assets/entities/intelligent/r3.png",
            "src/main/resources/assets/entities/intelligent/r4.png",
            "src/main/resources/assets/entities/intelligent/r5.png",
            "src/main/resources/assets/entities/intelligent/r6.png",
            "src/main/resources/assets/entities/intelligent/r7.png",
            "src/main/resources/assets/entities/intelligent/r8.png",
            "src/main/resources/assets/entities/intelligent/u1.png",
            "src/main/resources/assets/entities/intelligent/u2.png",
            "src/main/resources/assets/entities/intelligent/u3.png",
            "src/main/resources/assets/entities/intelligent/u4.png",
            "src/main/resources/assets/entities/intelligent/u5.png",
            "src/main/resources/assets/entities/intelligent/u6.png",
            "src/main/resources/assets/entities/intelligent/u7.png",
            "src/main/resources/assets/entities/intelligent/u8.png",
            "src/main/resources/assets/entities/intelligent/d1.png",
            "src/main/resources/assets/entities/intelligent/d2.png",
            "src/main/resources/assets/entities/intelligent/d3.png",
            "src/main/resources/assets/entities/intelligent/d4.png",
            "src/main/resources/assets/entities/intelligent/d5.png",
            "src/main/resources/assets/entities/intelligent/d6.png",
            "src/main/resources/assets/entities/intelligent/d7.png",
            "src/main/resources/assets/entities/intelligent/d8.png",
            "src/main/resources/assets/entities/intelligent/l1.png",
            "src/main/resources/assets/entities/intelligent/l2.png",
            "src/main/resources/assets/entities/intelligent/l3.png",
            "src/main/resources/assets/entities/intelligent/l4.png",
            "src/main/resources/assets/entities/intelligent/l5.png",
            "src/main/resources/assets/entities/intelligent/l6.png",
            "src/main/resources/assets/entities/intelligent/l7.png",
            "src/main/resources/assets/entities/intelligent/l8.png"
    )),
    PLAYER_DATA_BG_IMG("src/main/resources/assets/PlayerDataPanel.png"),
    PLAYER_DATA_TABLE_IMG("src/main/resources/assets/table.png");


    private final String url;
    private final List<String> urls;

    Image(List<String> envUrls) {
        this.urls = envUrls;
        this.url = null;
    }

    Image(String envUrl) {
        this.url = envUrl;
        this.urls = null;
    }

    public List<String> getImageUrls() {
        return urls;
    }

    public String getImageUrl() {
        return url;
    }
}
