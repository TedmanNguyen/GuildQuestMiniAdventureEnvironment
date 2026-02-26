package hw2.user;

import hw2.ui.InputManager;

public class CharacterInputHandler {
	private final User user;

    public CharacterInputHandler(User user) {
        this.user = user;
    }

    public void getAddCharacterInput() {
        String name = InputManager.getLineInput("Enter a name: ",
                                                 InputManager.stringNotEmpty);
        String heroClass = InputManager.getLineInput("Enter a hero class: ",
                                                      InputManager.stringNotEmpty);
        user.createCharacter(name, heroClass);
    }

    public void getChangeCharacterInput() {
        if (user.characterManager.isEmpty()) return;

        user.characterManager.printCharacters();
        int i = InputManager.getIndexInput(
            "Enter the number associated with the character you want to select: ",
            0, user.characterManager.numCharacters());
        user.changeCharacter(i);
    }
}
