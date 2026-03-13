package models;
import java.util.Scanner;
public abstract class MiniAdventure {

    protected Realm realm;
    protected Characters player1;
    protected Characters player2;
    protected Characters currentPlayer;
    protected boolean gameOver;

    MiniAdventure(Realm realm, Characters player1, Characters player2 ){
        this.realm = realm;
        this.player1 = player1;
        this.player2 = player2;
        this.gameOver = false;
    }

    public void launch(){
        Scanner scanner = new Scanner(System.in);

        initializeAdventure();

        while (!gameOver){
            displayGrid();
            System.out.println("Current Player:" + currentPlayer.getCharacterName());
            System.out.println("Enter next move: ");
            String input = scanner.nextLine();

            processInput(input);

            if (isGameOver()){
                gameOver = true;
            }
            else{
                switchTurns();
            }
        }
    }

    protected void switchTurns(){
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }

    protected abstract void initializeAdventure();
    protected abstract void displayGrid();
    protected abstract void processInput(String input);
    protected abstract boolean isGameOver();

    protected void endAdventure(){
        System.out.println("Mini-Adventure over!");
    }



}
