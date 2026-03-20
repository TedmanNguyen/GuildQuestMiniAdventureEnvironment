package models.domain_primitives;

public class BoardSize {
    private int rows;
    private int columns;

    public BoardSize(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("The number rows and columns for a grid cannot be negative.");
        this.rows = rows;
        this.columns = columns;
    }

    public int rows() { return rows; }
    public int columns() { return columns; }
    public boolean inBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }
}
