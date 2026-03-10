public class EmptyTile extends Tile {
    private static String character = " ";

    public EmptyTile() {
        currentCharacter = character;
    }

    public void stepOn(Characters c) {
        currentCharacter = c.toString();
    }

    public void stepOff() {
        currentCharacter = character;
    }

    public String toString() {
        return currentCharacter;
    }
}