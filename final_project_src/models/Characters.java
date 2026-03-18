package models;

import models.tiles.TileCharacter;

public class Characters {
    public static int characterId = 1;
    private final int id;
    private TileCharacter tileCharacter;
    private String characterName;
    private Inventory inventory;
    public int x = 0;
    public int y = 0;

    public Characters (int characterId, String characterName, int spawnx, int spawny){
        this.id = characterId;
        this.characterName = characterName;
        inventory = new Inventory();
        x = spawnx;
        y = spawny;
        // The following line converts an integer to a Character. It won't work if the ID exceeds 9.
        tileCharacter = new TileCharacter(Character.forDigit(characterId, 10));
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
}
