package models;

import models.tiles.Tile;
import models.tiles.TileCharacter;

public abstract class Grid {
    private Tile[][] grid;
    private Characters p1, p2;
    private int rows, cols;
    //Initializes the grid dimensions, stores the two characters, then calls initializeGrid() and
    // placeCharacters() to set everything up.
    public Grid(int rows, int cols, Characters p1, Characters p2) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Tile[rows][cols];
        this.p1 = p1;
        this.p2 = p2;
        initializeGrid();
        placeCharacters();
    }
    //initializeGrid(): Loops through every cell in the 2D array and fills each one with a blank anonymous
    // Tile that knows how to display a character when stepped on, or revert to empty when stepped off.
    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final int row = i;
                final int col = j;
                final TileCharacter emptyChar = new TileCharacter(' ');
                grid[i][j] = new Tile() {
                    {
                        // initialize currentCharacter and position
                        currentCharacter = new TileCharacter(emptyChar);
                        this.position = new Position(row, col);
                    }

                    @Override
                    public void stepOn(Characters c) {
                        currentCharacter.updateCharacter(c.tileCharacter());
                    }

                    @Override
                    public void stepOff() {
                        currentCharacter.updateCharacter(emptyChar);
                    }

                    @Override
                    public String toString() {
                        return currentCharacter.toString();
                    }
                };
            }
        }
    }

    //placeCharacters(): Reads each character's starting (x, y)
    // position and calls stepOn() on their respective tile, so they appear on the grid from the start.
    private void placeCharacters() {
        if (p1 != null) {
            int x = p1.getXPosition();
            int y = p1.getYPosition();
            if (inBounds(y, x)) {
                grid[y][x].stepOn(p1);
            }
        }
        if (p2 != null) {
            int x = p2.getXPosition();
            int y = p2.getYPosition();
            if (inBounds(y, x)) {
                grid[y][x].stepOn(p2);
            }
        }
    }
    //inBounds(): Checks that a given row and column fall within the valid grid dimensions,
    // returning true if so and false otherwise.
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    //displayGrid(): Iterates through every tile row by row,
    // printing each tile's toString() to the console to render the grid visually.
    public void displayGrid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(grid[i][j].toString());
            }
            System.out.println();
        }
    }

}