
public abstract class AbstractGrid<T> {
    protected final int rows;
    protected final int cols;
    protected final Object[][] cells;

    public AbstractGrid(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Grid dimensions must be positive.");
        }

        this.rows = rows;
        this.cols = cols;
        this.cells = new Object[rows][cols];

        initializeGrid();
    }

    /**
     * Each subclass decides what the default tile/value should be.
     * Example:
     * - MazeGrid -> FLOOR
     * - RelicGrid -> EMPTY
     */
    protected abstract T getDefaultValue();

    /**
     * Optional hook for subclasses to customize startup map generation.
     * Default behavior: fill with default value.
     */
    protected void initializeGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = getDefaultValue();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isInBounds(Position pos) {
        return pos != null
                && pos.getRow() >= 0
                && pos.getRow() < rows
                && pos.getCol() >= 0
                && pos.getCol() < cols;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @SuppressWarnings("unchecked")
    public T getCell(Position pos) {
        if (!isInBounds(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
        }
        return (T) cells[pos.getRow()][pos.getCol()];
    }

    public T getCell(int row, int col) {
        return getCell(new Position(row, col));
    }

    public void setCell(Position pos, T value) {
        if (!isInBounds(pos)) {
            throw new IndexOutOfBoundsException("Position out of bounds: " + pos);
        }
        cells[pos.getRow()][pos.getCol()] = value;
    }

    public void setCell(int row, int col, T value) {
        setCell(new Position(row, col), value);
    }

    public boolean isEdge(Position pos) {
        if (!isInBounds(pos)) return false;

        int r = pos.getRow();
        int c = pos.getCol();

        return r == 0 || r == rows - 1 || c == 0 || c == cols - 1;
    }

    public void fill(T value) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = value;
            }
        }
    }

    /**
     * Useful for movement logic shared by games.
     */
    public Position getNextPosition(Position current, char input) {
        return switch (Characters.toLowerCase(input)) {
            case 'w' -> current.translate(-1, 0);
            case 's' -> current.translate(1, 0);
            case 'a' -> current.translate(0, -1);
            case 'd' -> current.translate(0, 1);
            default -> current;
        };
    }

    /**
     * Each concrete grid can define what "walkable" means.
     * Example:
     * - Maze wall = not walkable
     * - Relic tile = walkable
     * - Hazard may or may not be walkable depending on rules
     */
    public abstract boolean isWalkable(Position pos);

    /**
     * Optional text rendering for debugging.
     * Your actual minigame can override visual rendering separately.
     */
    public void printDebugGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(getCell(r, c) + " ");
            }
            System.out.println();
        }
    }
}