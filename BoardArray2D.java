package HW7_161044041;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;

/**
 * BoardArray2D.java - A class to represent a board with two dimensional Java array.
 * @author  Mertcan Elçi - 161044041
 * @see AbstractBoard
 */

public class BoardArray2D extends AbstractBoard {
    /**
     * 2D array that holds the contents of the game board.
     */
    private int[][] board;
    /**
     * X axis of the blank cell.
     */
    private int x;
    /**
     * Y axis of the blank cell.
     */
    private int y;
    /**
     * Number of rows in the board.
     */
    private int row;
    /**
     * Number of columns in the board.
     */
    private int column;
    /**
     * Number of successful moves made by move()
     * @see BoardArray1D#move(int)
     */
    private int totalMoves;
    /**
     * Last successful move made by move()
     * @see BoardArray1D#move(int)
     */
    private String previousMove;

    /**
     * Class constructor to set variables to default values.
     */
    BoardArray2D() {
        totalMoves = x = y = 0;
        previousMove = "S";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    final void print() {
        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {

                if(board[i][j] == -1)
                    System.out.print("  ");

                else if(board[i][j] < 10 && board[i][j] != -1)
                    System.out.printf("0%d", board[i][j]);

                else if(board[i][j] == 0)
                    System.out.print("00");

                else
                    System.out.printf("%d", board[i][j]);

                if(j + 1 == getColumn())
                    System.out.print("\n");

                else
                    System.out.print("\t");

            }
        }
        System.out.print("\n");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        StringBuilder str =  new StringBuilder();

        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                if(cell(i, j) == -1)
                    str.append("b");

                else
                    str.append(cell(i, j));
            }
        }

        return str.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void readFromFile(String filename) {
        FileInputStream in;

        setColumn(0);
        setRow(0);

        try {
            in = new FileInputStream(filename);

            int c;
            int a;
            int z;
            int i = 0;
            int j = 0;
            int line = 0;
            int length = 0;

            while ((c = in.read()) != -1) {
                if (c == 32) {
                    //empty
                }

                else if(c == 13 || c == 10) {
                    line++;
                    i++;
                }

                else {
                    if(i == 0)
                        length++;
                }
            }

            length /= 2;
            line++;
            i = 0;
            board = new int[line][length];
            setRow(line);
            setColumn(length);

            in.close();
            in = new FileInputStream(filename);

            int rowCount = 0;
            int columnCount = 0;
            while ((c = in.read()) != -1) {
                a = in.read();
                if(c == 10 || c == 13 || a == 10 || a == 13) {
                    rowCount++;
                    if(getColumn() == 0)
                        setColumn(columnCount);

                    j = 0;
                    i++;
                }

                else if ( c == 32 ||  a == 32) {

                }

                else if(a == 98) {
                    board[i][j] = -1;
                    setX(j);
                    setY(i);
                    j++;
                    columnCount++;
                }

                else if(a == 48 && c == 48) {
                    board[i][j] = 0;
                    j++;
                    columnCount++;
                }

                else {
                    z = (getNumericValue(c) * 10) + getNumericValue(a);
                    board[i][j] = z;
                    j++;
                    columnCount++;
                }
            }
            in.close();
            setRow(rowCount + 1);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Loaded From File!\n");
        previousMove = "Load";
        totalMoves = 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    final void writeToFile(String filename) {
        FileOutputStream out;

        try {
            int b = 0;
            out = new FileOutputStream(filename);

            for(int i = 0; i < getRow(); i++) {
                for(int j = 0; j < getColumn(); j++) {
                    if(board[i][j] == 0) {
                        out.write('0');
                        out.write('0');
                    }

                    else if(board[i][j] == -1) {
                        out.write('b');
                        out.write('b');
                    }

                    else {
                        if(board[i][j] < 10 && board[i][j] != -1) {
                            out.write(48);
                            b = board[i][j] + 48;
                            out.write(b);
                            b = 0;
                        }

                        else {
                            b = (board[i][j] / 10) + 48;
                            out.write(b);
                            b = (board[i][j] % 10) + 48;
                            out.write(b);
                            b = 0;
                        }
                    }

                    if(j == getColumn() - 1) {
                        out.write(10);
                    }

                    else {
                        out.write(32);
                        out.write(32);
                    }
                }
            }
            out.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Saved to File!\n");
        previousMove = "Save";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void reset() {
        int number = 1;

        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {

                if(board[i][j] == 0) {
                    //empty
                }

                else {
                    board[i][j] = number;
                    number++;
                }

                if(number == getRow() * getColumn()) {
                    board[i][j + 1] = -1;
                    x = j + 1;
                    y = i;
                    System.out.print("Table Reset to Solution\n");
                    return;
                }
            }
        }

        totalMoves = 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    void setSize() {
        int boardSize;
        int number = 1;
        Scanner Input = new Scanner(System.in);

        System.out.print("Please enter the board size\n");
        boardSize = Input.nextInt();

        board = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = number;
                number++;

                if(number == boardSize * boardSize + 1) {
                    board[i][j] = -1;
                    break;
                }
            }
        }

        setX(boardSize - 1);
        setY(boardSize - 1);
        setRow(boardSize);
        setColumn(boardSize);
        totalMoves = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int move(int direction) {
        if(direction == 0 && x != getColumn() - 1 && board[y][x + 1] != 0) {
            board[y][x] = board[y][x + 1];
            board[y][x + 1] = -1;

            x += 1;
            System.out.print("Moved Right\n");
            previousMove = "Right";
            totalMoves++;
            return 1;
        }

        else if(direction == 1 && x != 0 && board[y][x - 1] != 0) {
            board[y][x] = board[y][x - 1];
            board[y][x - 1] = -1;

            x -= 1;
            System.out.print("Moved Left\n");
            previousMove = "Left";
            totalMoves++;
            return 1;
        }

        else if(direction == 2 && y != 0 && board[y - 1][x] != 0) {
            board[y][x] = board[y - 1][x];
            board[y - 1][x] = -1;

            y -= 1;
            System.out.print("Moved Up\n");
            previousMove = "Up";
            totalMoves++;
            return 1;
        }

        else if(direction == 3 && y != getRow() - 1 && board[y + 1][x] != 0) {
            board[y][x] = board[y + 1][x];
            board[y + 1][x] = -1;

            y += 1;
            System.out.print("Moved Down\n");
            previousMove = "Down";
            totalMoves++;
            return 1;
        }

        else {
            System.out.print("Wrong Move\n");
            return 0;
        }
    }

    @Override
    int moveRandom(int direction) {
        if(direction == 0 && x != getColumn() - 1 && board[y][x + 1] != 0) {
            board[y][x] = board[y][x + 1];
            board[y][x + 1] = -1;

            x += 1;
            totalMoves++;
            return 1;
        }

        else if(direction == 1 && x != 0 && board[y][x - 1] != 0) {
            board[y][x] = board[y][x - 1];
            board[y][x - 1] = -1;

            x -= 1;
            return 1;
        }

        else if(direction == 2 && y != 0 && board[y - 1][x] != 0) {
            board[y][x] = board[y - 1][x];
            board[y - 1][x] = -1;

            y -= 1;
            return 1;
        }

        else if(direction == 3 && y != getRow() - 1 && board[y + 1][x] != 0) {
            board[y][x] = board[y + 1][x];
            board[y + 1][x] = -1;

            y += 1;
            return 1;
        }

        else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    final boolean isSolved() {
        int check = 0;
        int checkCounter = 1;

        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                if(board[i][j] == checkCounter) {
                    check++;
                    checkCounter++;
                }

                else if(board[i][j] == 0)
                {
                    //empty
                }

                else
                    break;

                if(check == (getRow() * getColumn()) - 1)
                    return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    final int numberOfMoves() { return totalMoves; }
    /**
     * {@inheritDoc}
     */
    @Override
    final String lastMove() { return previousMove; }

    /**
     * {@inheritDoc}
     */
    @Override
    final int cell(int m, int n) {
        if(m >= getColumn() || n >= getRow() || m < 0 || n < 0) {
            System.out.println("Wrong Index, Terminating....\n");
            System.exit(0);
        }

        return board[m][n];
    }
    /**
     * {@inheritDoc}
     */
    @Override
    final boolean equals(BoardArray2D m) {

        if(getColumn() != m.getColumn() || getRow() != m.getRow() || getX() != m.getX() || getY() != m.getY()) {
            return false;
        }

        for(int i = 0; i < getRow(); i++)
            for(int j = 0; j < getColumn(); j++)
                if(board[i][j] != m.cell(i, j))
                    return false;

        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    final boolean equals(BoardArray1D m) {
        if(getColumn() != m.getColumn() || getRow() != m.getRow() || getX() != m.getX() || getY() != m.getY()) {
            return false;
        }

        int i = 0, j = 0, a = 0;
        while(a < board.length) {

            if(board[i][j] != m.cell(a))
                return false;

            j++;
            a++;

            if(j == getColumn())
                i++;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    final int getRow() { return row; };
    /**
     * {@inheritDoc}
     */
    @Override
    final int getColumn() { return column; };
    /**
     * {@inheritDoc}
     */
    @Override
    final int getX() { return x; };
    /**
     * {@inheritDoc}
     */
    @Override
    final int getY() { return y; };
    /**
     * {@inheritDoc}
     */
    @Override
    final int getLength() {
        int result = 0;

        for(int i = 0; i < getRow(); i++)
            result += board[i].length;

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void setRow(int a) { row = a; };
    /**
     * {@inheritDoc}
     */
    @Override
    void setColumn(int a) { column = a; };
    /**
     * {@inheritDoc}
     */
    @Override
    void setX(int a) { x = a; };
    /**
     * {@inheritDoc}
     */
    @Override
    void setY(int a) { y = a; };
}
