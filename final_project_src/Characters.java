public class Characters {
    public static int characterId = 0;
    private String characterName;
    private Inventory inventory;
    public int x = 0;
    public int y = 0;

    public Characters (int characterId, String characterName, int spawnx, int spawny){
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

    public string toString(){
        return String.valueOf(characterId);
    }

    public void getCharacterInfo(){
        System.out.println("Character info: \n" + characterName);
    }
}
