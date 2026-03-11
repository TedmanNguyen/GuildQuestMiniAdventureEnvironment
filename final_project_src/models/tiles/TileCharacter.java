package models.tiles;

public class TileCharacter {
    private Character character;

    public TileCharacter(Character character) {
        this.character = character;
    }

    public TileCharacter(TileCharacter character) {
        this.character = character.character;
    }

    public Character character() { return character; }

    public TileCharacter updateCharacter(TileCharacter c) {
        character = c.character;
        return this;
    }

    public TileCharacter updateCharacter(Character c) {
        character = c;
        return this;
    }

    public String toString() {
        return character.toString();
    }
}
