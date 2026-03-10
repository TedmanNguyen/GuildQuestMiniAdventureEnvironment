public class Characters {
    private int characterId;
    private String characterName;
    private  String characterClass;
    private int characterLevel;
    private Inventory inventory;


    public Characters (int characterId, String characterName, String characterClass){
        this.characterId = characterId;
        this.characterName = characterName;
        this.characterClass = characterClass;
    }
    public void setName(String newName){
        characterName = newName;
    }
    public void setCharacterClass(String newClass){
        characterClass = newClass;
    }
    public void setCharacterLevel(int newLevel){
        characterLevel = newLevel;
    }
    public int getId(){
        return characterId;
    }
    public void getCharacterInfo(){
        System.out.println("Character info: \n" + characterName + "\n" + characterClass + "\n" + characterLevel);
    }
    public Inventory getInventory() {
        return inventory;
    }
}
