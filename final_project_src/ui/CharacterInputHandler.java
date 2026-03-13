package ui;

public class CharacterInputHandler {
    private final GuildQuest game;

    public CharacterInputHandler(GuildQuest game) {
        this.game = game;
    }

    public void getAddCharacterInput() {
        int id = InputManager.getIntInput("Enter Character ID (int): ");
        String name = InputManager.getLineInput("Enter a name: ", InputManager.stringNotEmpty);
        
        // placeholder, need to followup with team on where want to place character
        int x = 0;
        int y = 0;
        
        game.getCharacterManager().createCharacter(id, name, x, y);
    }
}
