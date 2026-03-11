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
        characters.add(new Characters(id, name, x, y));
    }

    public void printCharacters() {
        System.out.println("CHARACTERS");
        System.out.println("--------------------");
        for (int i = 0; i < characters.size(); ++i) {
            System.out.print(i + ": ");
            characters.get(i).getCharacterInfo();
        }
    }

    public boolean isEmpty() {
        return characters.isEmpty();
    }
}
