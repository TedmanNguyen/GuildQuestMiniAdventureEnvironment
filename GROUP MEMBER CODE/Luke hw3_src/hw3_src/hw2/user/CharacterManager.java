package hw2.user;

import java.util.ArrayList;
import hw2.Character;

public class CharacterManager {
	private ArrayList<Character> characters;
	private Character playingAs;
	
	CharacterManager() {
		characters = new ArrayList<Character>();
		playingAs = null;
	}
	
	public void createCharacter(String name, String heroClass) {
		characters.add(new Character(name, heroClass));
	}
	
	public void changeCharacter(int index) {
		playingAs = characters.get(index);
	}
	
	public void printCharacters() {
		System.out.println("CHARACTERS");
		System.out.println("--------------------");
		for (int i = 0; i < characters.size(); ++i)
			System.out.println(i + ": " + characters.get(i));
	}
	
	public boolean isEmpty() {
		return characters.isEmpty();
	}
	
	public int numCharacters() {
		return characters.size();
	}
	
	public Character getPlayingAs() {
		return playingAs;
	}
}
