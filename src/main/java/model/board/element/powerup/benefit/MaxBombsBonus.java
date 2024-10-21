package model.board.element.powerup.benefit;

import model.board.element.character.Player;
import model.board.element.powerup.Bonus;

import java.awt.*;


public class MaxBombsBonus extends Bonus {

    public MaxBombsBonus(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible, Player owner) {
        super(x, y, width, height, velocity, image, alive, visible, owner);
    }

    @Override
    public void use() {
        this.owner.incrementNumberOfPlaceableBombs();

    }
}
