package ui;
import java.util.ArrayList;
import models.Characters;

public class CharacterManager {
    private ArrayList<Characters> characters;
    private Characters currentCharacter;

    public CharacterManager() {
        characters = new ArrayList<>();
        currentCharacter = null;
    }

    public void createCharacter(int id, String name, int x, int y) {
        characters.add(new Characters.Builder(name, id)
                                     .withPosition(x, y)
                                     .withInventory()
                                     .build());
    }

    public void printCharacters() {
        System.out.println("CHARACTERS");
        System.out.println("--------------------");
        for (int i = 0; i < characters.size(); ++i) {
            System.out.print(i + ": ");
            characters.get(i).getCharacterInfo();
        }
    }
    public int characterAmount(){
        return characters.size();
    }
    public boolean isEmpty() {
        return characters.isEmpty();
    }

    public ArrayList<Characters> getCharacters() {
        return characters;
    }

    public Characters getById(int id) {
        for (Characters c : characters) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}
