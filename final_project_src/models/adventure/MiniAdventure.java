package models.adventure;

import models.Characters;
import models.Position;
import models.Realm;

import java.util.List;
import java.util.ArrayList;

public abstract class MiniAdventure {
    protected static List<MiniAdventure> adventures = new ArrayList<>();

    protected Realm realm;
    protected List<Characters> players;
    protected Characters player1;
    protected Characters player2;
    protected Characters currentPlayer;
    protected boolean gameOver;

    protected MiniAdventure(Realm realm, Characters player1, Characters player2 ){
        this.realm = realm;
        this.player1 = player1;
        this.player2 = player2;
        this.players = new ArrayList<>();
        if (player1 != null) this.players.add(player1);
        if (player2 != null) this.players.add(player2);
        this.gameOver = false;
    }

    protected MiniAdventure(List<Characters> players) {
        this.realm = null;
        this.players = players;
        this.player1 = (players != null && !players.isEmpty()) ? players.get(0) : null;
        this.player2 = (players != null && players.size() > 1) ? players.get(1) : null;
        this.gameOver = false;
    }

    // public void launch(){
    //     Scanner scanner = new Scanner(System.in);

    //     initializeAdventure();

    //     while (!gameOver){
    //         displayGrid();
    //         System.out.println("Current Player:" + currentPlayer.getCharacterName());
    //         System.out.println("Enter next move: ");
    //         String input = scanner.nextLine();

    //         processInput(input);

    //         if (isGameOver()){
    //             gameOver = true;
    //         }
    //         else{
    //             switchTurns();
    //         }
    //     }
    // }

    public abstract void play(Characters startingPlayer);
    protected abstract boolean inBounds(Position pos);

    // protected void switchTurns(){
    //     if (currentPlayer == player1){
    //         currentPlayer = player2;
    //     }
    //     else{
    //         currentPlayer = player1;
    //     }
    // }

    // protected abstract void initializeAdventure();
    // protected abstract void displayGrid();
    // protected abstract void processInput(String input);
    // protected abstract boolean isGameOver();

    protected void endAdventure(){
        System.out.println("Mini-Adventure over!");
    }

    public static void printAdventures() {
        System.out.println("ADVENTURES");
        System.out.println("--------------------");
        for (int i = 0; i < adventures.size(); ++i)
            System.out.println(i + ": " + adventures.get(i));
        System.out.println();
    }



}
