public class Characters {
    public static int characterId = 0;
    private String characterName;
    private Inventory inventory;
    public int x = 0;
    public int y = 0;

<<<<<<< HEAD

    public Characters (int characterId, String characterName, String characterClass){
        this.characterId = characterId;
=======
    public Characters (int characterId, String characterName, int spawnx, int spawny){
>>>>>>> 8782494892866c2eb6d01c1424faa688ef0b5085
        this.characterName = characterName;
        x = spawnx;
        y = spawny;
        characterId++;
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

    public String toString(){
        return String.valueOf(characterId);
    }

    public void getCharacterInfo(){
        System.out.println("Character info: \n" + characterName);
    }
    public Inventory getInventory() {
        return inventory;
    }
}
