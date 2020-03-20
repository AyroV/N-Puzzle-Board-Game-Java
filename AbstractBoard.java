package HW7_161044041;

/**
 * AbstractBoard.java - An abstract class that holds methods for BoardArray1D and BoardArray2D
 * @author  Mertcan Elci - 161044041
 */

public abstract class AbstractBoard {
    private static int totalBoards = 0;

    /**
     * Class constructor to increase the static variable when a new object initialized to keep track of object count.
     */
    AbstractBoard() {
        totalBoards++;
    }

    /**
     * Prints the contents of board.
     */
    abstract void print();
    /**
     * Converts the contents of board to string
     * @return A line of string that represents the contents of the board. Blank cell is represented with -1.
     */
    public abstract String toString();

    /**
     * Reads the target text file to convert it to int Java array.
     * @param filename Name of the text file to read.
     */
    abstract void readFromFile(String filename);
    /**
     * Writes the contents of board as text to target file.
     * @param filename Name of the text file to write.
     */
    abstract void writeToFile(String filename);

    /**
     * Reset the board to solution state.
     */
    abstract void reset();
    /**
     * Changes the size of the board. Size of the board can be increased or decreased. New board will be created on its
     * solution state.
     */
    abstract void setSize();

    /**
     * Moves the blank cell according to direction. Checks out the illegal moves and prints the direction when
     * the cell is moved.
     * @param direction Direction of the move. 0-Right, 1-Left, 2-Up, 3-Down
     * @return 1 for successful move, otherwise -1.
     */
    abstract int move(int direction);

    abstract int moveRandom(int direction);
    /**
     * Checks if the board is on solution state.
     * @return True if it is on solution state, otherwise False.
     */
    abstract boolean isSolved();

    /**
     * @return Number of successful moves made my object.
     */
    abstract int numberOfMoves();
    /**
     * @return Name of the last successful move made my object.
     */
    abstract String lastMove();
    /**
     * Looks the content of a specified cell
     * @param m y axis of target.
     * @param n x axis of target.
     * @return Cell block content that located in board[m][n]
     */
    abstract int cell(int m, int n);

    /**
     * Compare the object with a BoardArray1D object to check if contents of both boards are equal.
     * Can compare 2D objects with 1D objects.
     * @param m Object to be compared
     * @return True if boards are equal, otherwise False
     */
    abstract boolean equals(BoardArray1D m);
    /**
     * Compare the object with a BoardArray2D object to check if contents of both boards are equal.
     * Can compare 1D objects with 2D objects.
     * @param m Object to be compared
     * @return True if boards are equal, otherwise False
     */
    abstract boolean equals(BoardArray2D m);

    /**
     * @return Number of created objects that extends AbstractBoard
     */
    static int NumberOfBoards() { return totalBoards; }

    /**
     * @return Row variable of the object.
     */
    abstract int getRow();
    /**
     * @return Column variable of the object.
     */
    abstract int getColumn();
    /**
     * @return x variable of the object.
     */
    abstract int getX();
    /**
     * @return y variable of the object.
     */
    abstract int getY();
    /**
     * Checks the number of contents in the board.
     * @return Length of the board.
     */
    abstract int getLength();
    /**
     * Sets the value of the row variable.
     * @param a - New value of row variable.
     */
    abstract void setRow(int a);
    /**
     * Sets the value of the column variable.
     * @param a - New value of column variable.
     */
    abstract void setColumn(int a);
    /**
     * Sets the value of the x variable.
     * @param a - New value of x variable.
     */
    abstract void setX(int a);
    /**
     * Sets the value of the y variable.
     * @param a - New value of y variable.
     */
    abstract void setY(int a);
}
