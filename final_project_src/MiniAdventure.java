public abstract class MiniAdventure {

    protected Realm realm;
    protected Characters player1;
    protected Characters player2;
    protected boolean gameOver;

    MiniAdventure(Realm realm, Characters player1, Characters player2 ){
        this.realm = realm;
        this.player1 = player1;
        this.player2 = player2;
        this.gameOver = false;
    }

    public abstract void launch(

    );


}
