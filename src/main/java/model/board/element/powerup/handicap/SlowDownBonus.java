package model.board.element.powerup.handicap;

import model.board.element.character.Player;
import model.board.element.powerup.BonusWithTimer;

import java.awt.*;

public class SlowDownBonus extends BonusWithTimer {
    public SlowDownBonus(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible, Player owner) {
        super(x, y, width, height, velocity, image, alive, visible, owner);
    }

    @Override
    public void use() {
        this.owner.useSlowDownBonus();
    }
}
