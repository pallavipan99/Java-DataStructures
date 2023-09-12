package conwaygame;

import java.util.ArrayList;

/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many
 * iterations/generations.
 *
 * Rules
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.
 * 
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid; // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
     * Default Constructor which creates a small 5x5 grid with five alive cells.
     * This variation does not exceed bounds and dies off after four iterations.
     */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
     * Constructor used that will take in values to create a grid with a given
     * number
     * of alive cells
     * 
     * @param file is the input file with the initial game pattern formatted as
     *             follows:
     *             An integer representing the number of grid rows, say r
     *             An integer representing the number of grid columns, say c
     *             Number of r lines, each containing c true or false values (true
     *             denotes an ALIVE cell)
     */
    public GameOfLife(String file) {
        // WRITE YOUR CODE HERE
        totalAliveCells = 0;
        StdIn.setFile(file);
        int numRows = StdIn.readInt();
        int numColumns = StdIn.readInt();
        grid = new boolean[numRows][numColumns];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = StdIn.readBoolean();
                if (grid[x][y] == true) {
                    totalAliveCells++;
                }
            }
        }
    }

    /**
     * Returns grid
     * 
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid() {
        return grid;
    }

    /**
     * Returns totalAliveCells
     * 
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells() {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * 
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState(int row, int col) {
        // WRITE YOUR CODE HERE
        return grid[row][col];

    }
    // return true; // update this line, provided so that code compiles

    /**
     * Returns true if there are any alive cells in the grid
     * 
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive() {
        // WRITE YOUR CODE HERE
        if (totalAliveCells >= 1) {
            return true;
        } else {
            return false;
        }
        // return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors(int row, int col) {
        // WRITE YOUR CODE HERE
        int num = 0;
        int begRow = 0;
        int begCol = 0;
        int endRow = 0;
        int endCol = 0;
        int refCol = -1;
        int refRow = -1;

        if (row > 0 && col > 0 && row < grid.length - 1 && col < grid[0].length - 1) {
            begRow = row - 1;
            begCol = col - 1;
            endRow = row + 1;
            endCol = col + 1;
        } else if ((row != 0 && row != grid.length - 1) && (col == grid[0].length - 1 || col == 0)) {
            begRow = row - 1;
            endRow = row + 1;
            if (col == grid[0].length - 1) {
                begCol = col - 1;
                endCol = col;
                refCol = 0;
            } else if (col == 0) {
                begCol = 0;
                endCol = col + 1;
                refCol = grid[0].length - 1;
            }
            if (grid[begRow][refCol]) {
                num++;
            }
            if (grid[begRow + 1][refCol]) {
                num++;
            }
            if (grid[endRow][refCol]) {
                num++;
            }
        } else if (row == 0 && col > 0 && col < grid[0].length - 1) {
            begRow = row;
            endRow = row + 1;
            begCol = col - 1;
            endCol = col + 1;
            refRow = grid.length - 1;

            if (grid[refRow][begCol]) {
                num++;
            }
            if (grid[refRow][begCol + 1]) {
                num++;
            }
            if (grid[refRow][endCol]) {
                num++;
            }

        } else if (row == grid.length - 1 && col > 0 && col < grid[0].length - 1) {
            begRow = row - 1;
            endRow = row;
            begCol = col - 1;
            endCol = col + 1;
            refRow = 0;
            if (grid[refRow][begCol]) {
                num++;
            }
            if (grid[refRow][begCol + 1]) {
                num++;
            }
            if (grid[refRow][endCol]) {
                num++;
            }

        } else {
            if (row == 0 && col == 0) {
                begRow = 0;
                endRow = row + 1;
                begCol = 0;
                endCol = col + 1;
                refRow = grid.length - 1;
                refCol = grid[0].length - 1;
            } else if (row == 0 && col == grid[0].length - 1) {
                begRow = 0;
                endRow = row + 1;
                begCol = col - 1;
                endCol = col;
                refRow = grid.length - 1;
                refCol = 0;
            } else if (row == grid.length - 1 && col == 0) {
                begRow = row - 1;
                endRow = row;
                begCol = col;
                endCol = col + 1;
                refRow = 0;
                refCol = grid[0].length - 1;
            } else if (row == grid.length - 1 && col == grid[0].length - 1) {
                begRow = row - 1;
                endRow = row;
                begCol = col - 1;
                endCol = col;
                refRow = 0;
                refCol = 0;
            }
            if (grid[refRow][refCol]) {
                num++;
            }
            if (grid[begRow][refCol]) {
                num++;
            }
            if (grid[endRow][refCol]) {
                num++;
            }
            if (grid[refRow][begCol]) {
                num++;
            }
            if (grid[refRow][endCol]) {
                num++;
            }
        }
        for (int x = begRow; x <= endRow; x++) {
            for (int y = begCol; y <= endCol; y++) {
                if (grid[x][y]) {
                    if (x == row && y == col) {
                        num--;
                    }
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * Creates a new grid with the next generation of the current grid using
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid() {
        // WRITE YOUR CODE HERE
        boolean newGrid[][] = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < newGrid.length; x++) {
            for (int y = 0; y < newGrid[0].length; y++) {
                if ((grid[x][y] == true) && (numOfAliveNeighbors(x, y) == 2 || numOfAliveNeighbors(x, y) == 3)) {
                    newGrid[x][y] = true;
                } else if ((grid[x][y] == false) && (numOfAliveNeighbors(x, y) == 3)) {
                    newGrid[x][y] = true;
                }
            }
        }

        return newGrid;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration() {
        // WRITE YOUR CODE HERE
        boolean[][] newGrid = computeNewGrid();
        totalAliveCells = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y] = newGrid[x][y];
                if (grid[x][y] == true) {
                    totalAliveCells++;
                }
            }
        }

    }

    /**
     * Updates the current grid with the grid computed after multiple (n)
     * generations.
     * 
     * @param n number of iterations that the grid will go through to compute a new
     *          grid
     */
    public void nextGeneration(int n) {
        // WRITE YOUR CODE HERE
        for (int x = 0; x < n; x++) {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * 
     * @return the number of communities in the grid, communities can be formed from
     *         edges
     */
    public int numOfCommunities() {
        // WRITE YOUR CODE HERE
        int num = 0;
        int begRow = 0;
        int begCol = 0;
        int endRow = 0;
        int endCol = 0;
        int refCol = -1;
        int refRow = -1;

        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(grid.length, grid[0].length);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int f = 0;

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int root = (x * grid[0].length);
                if (x > 0 && y > 0 && x < grid.length - 1 && y < grid[0].length - 1) {
                    begRow = x - 1;
                    begCol = y - 1;
                    endRow = x + 1;
                    endCol = y + 1;
                    f++;
                } else if ((x != 0 && x != grid.length - 1) && (y == grid[0].length - 1 || y == 0)) {
                    begRow = x - 1;
                    endRow = x + 1;
                    if (y == grid[0].length - 1) {
                        begCol = y - 1;
                        endCol = y;
                        refCol = 0;
                    } else if (y == 0) {
                        begCol = 0;
                        endCol = y + 1;
                        refCol = grid[0].length - 1;
                    }
                    if (grid[begRow][refCol] && grid[x][y]) {
                        uf.union(x, y, begRow, refCol);

                    }
                    if (grid[begRow + 1][refCol] && grid[x][y]) {
                        uf.union(x, y, begRow + 1, refCol);

                    }
                    if (grid[endRow][refCol] && grid[x][y]) {
                        uf.union(x, y, endRow, refCol);

                    }
                    f++;
                } else if (x == 0 && y > 0 && y < grid[0].length - 1) {
                    begRow = x;
                    endRow = x + 1;
                    begCol = y - 1;
                    endCol = y + 1;
                    refRow = grid.length - 1;

                    if (grid[refRow][begCol] && grid[x][y]) {
                        uf.union(x, y, refRow, begCol);

                    }
                    if (grid[refRow][begCol + 1] && grid[x][y]) {
                        uf.union(x, y, refRow, begCol + 1);

                    }
                    if (grid[refRow][endCol] && grid[x][y]) {
                        uf.union(x, y, refRow, endCol);

                    }

                } else if (x == grid.length - 1 && y > 0 && y < grid[0].length - 1) {
                    begRow = x - 1;
                    endRow = x;
                    begCol = y - 1;
                    endCol = y + 1;
                    refRow = 0;
                    if (grid[refRow][begCol] && grid[x][y]) {
                        uf.union(x, y, refRow, begCol);

                    }
                    if (grid[refRow][begCol + 1] && grid[x][y]) {
                        uf.union(x, y, refRow, begCol + 1);

                    }
                    if (grid[refRow][endCol] && grid[x][y]) {
                        uf.union(x, y, refRow, endCol);

                    }

                } else {
                    if (x == 0 && y == 0) {
                        begRow = 0;
                        endRow = x + 1;
                        begCol = 0;
                        endCol = y + 1;
                        refRow = grid.length - 1;
                        refCol = grid[0].length - 1;
                    } else if (x == 0 && y == grid[0].length - 1) {
                        begRow = 0;
                        endRow = x + 1;
                        begCol = y - 1;
                        endCol = y;
                        refRow = grid.length - 1;
                        refCol = 0;
                    } else if (x == grid.length - 1 && y == 0) {
                        begRow = x - 1;
                        endRow = x;
                        begCol = y;
                        endCol = y + 1;
                        refRow = 0;
                        refCol = grid[0].length - 1;
                    } else if (x == grid.length - 1 && y == grid[0].length - 1) {
                        begRow = x - 1;
                        endRow = x;
                        begCol = y - 1;
                        endCol = y;
                        refRow = 0;
                        refCol = 0;
                    }
                    if (grid[refRow][refCol] && grid[x][y]) {
                        uf.union(x, y, refRow, refCol);

                    }
                    if (grid[begRow][refCol] && grid[x][y]) {
                        uf.union(x, y, begRow, refCol);

                    }
                    if (grid[endRow][refCol] && grid[x][y]) {
                        uf.union(x, y, endRow, refCol);

                    }
                    if (grid[refRow][begCol] && grid[x][y]) {
                        uf.union(x, y, refRow, begCol);

                    }
                    if (grid[refRow][endCol] && grid[x][y]) {
                        uf.union(x, y, refRow, endCol);

                    }
                }

                for (int i = begRow; i <= endRow; i++) {
                    for (int z = begCol; z <= endCol; z++) {
                        if (grid[i][z] && grid[x][y]) {
                            uf.union(x, y, i, z);

                        }
                    }
                }

            }
        }

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y]) {
                    if (!arr.contains(uf.find(x, y))) {
                        arr.add(uf.find(x, y));
                    } else if (x == 0 & y == 0) {
                        arr.add(uf.find(x, y));
                    }

                }
            }
        }
        return arr.size();
    }
}
