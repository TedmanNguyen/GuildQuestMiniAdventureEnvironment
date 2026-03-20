package models;

import models.tiles.TileCharacter;

/**
 * Uses the builder pattern for creating new Characters
 */

public class Characters {
    // public static int characterId = 1;
    private final int id;
    private TileCharacter tileCharacter;
    private String characterName;
    private Inventory inventory;
    public int x = 0;
    public int y = 0;

    private Characters (Builder builder){
        this.id = builder.id;
        this.characterName = builder.characterName;
        inventory = builder.inventory;
        x = builder.x;
        y = builder.y;
        // The following line converts an integer to a Character. It won't work if the ID exceeds 9.
        tileCharacter = new TileCharacter(Character.forDigit(builder.id, 10));
    }

    public void setName(String newName){
        characterName = newName;
    }

    public int getXPosition(){
        return x;
    }

    public int getYPosition()
    {
        return y;
    }

    public String getCharacterName(){
        return characterName;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return tileCharacter.toString();
    }

    public TileCharacter tileCharacter() { return tileCharacter; }

    public void getCharacterInfo(){
        System.out.println("Character info: \n" + characterName );
    }
    public Inventory getInventory() {
        return inventory;
    }

    public int countItem(char letter) {
    return (int) inventory.getItems().stream()
        .filter(i -> i.toString().equals(String.valueOf(letter)))
        .count();
    }


    public static class Builder {
        // public static int characterId = 1;
        private TileCharacter tileCharacter;
        private int id;
        private String characterName;
        private Inventory inventory;
        private Integer x = 0;
        private Integer y = 0;

        public Builder(String name, int characterId) {
            this.characterName = name;
            id = characterId;
            this.inventory = null;
            this.x = null;
            this.y = null;
        }

        public Builder withPosition(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder withInventory() {
            inventory = new Inventory();
            return this;
        }

        public Characters build() {
            return new Characters(this);
        }
    }
}   
