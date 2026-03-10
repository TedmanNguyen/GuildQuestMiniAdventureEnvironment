public class WallTile extends Tile {
    private static String character = "|";

    public WallTile() {}

    public void stepOn(Characters c) {
        return;
    }

    public void stepOff() {
        return;
    }

    public String toString() {
        return character;
    }
}