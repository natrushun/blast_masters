package model.board.element;



public class Empty extends Entity{
    public Empty(double x, double y, int width, int height) {
        super(x, y, width, height, 0, null, false, false);
    }

    @Override
    public String toString() {
        return "E";
    }
}
