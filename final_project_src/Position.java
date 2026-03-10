import java.util.Objects;

public class Position{
    private final int row;
    private final int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    //returns new position based off the translation
    public Position translate(int dRow, int dCol){
        return new Position (row + dRow, col + dCol);
    }


    public Position move(char input){
        switch(Character.toLowerCase(input)){
            case 'w': return translate(-1,0);
            case 'a': return translate(0,-1);
            case 's': return translate(1,0);
            case 'd': return translate(0,1);
            default: return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public int distanceTo(Position other) {
        return Math.abs(row - other.row) + Math.abs(col - other.col);
    }
}