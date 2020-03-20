package HW7_161044041;

import java.util.Scanner;
import java.util.Random;

/**
 * Test.java - Class that includes main method to test classes.
 * @author  Mertcan Elci - 161044041
 */

public class Test {
    /**
     * Checks if the boards that are in the array creates a sequence that leads to solution.
     * @param x Array of AbstractBoard objects.
     */
    public static void sequence(AbstractBoard [] x) {
        int counter = 0;

        System.out.print("Start of Sequence\n");
        for (AbstractBoard abstractBoard : x) {
            abstractBoard.print();
        }

        for (AbstractBoard abstractBoard : x) {
            if (abstractBoard.isSolved())
                counter = 1;

            if(x[0].getLength() != abstractBoard.getLength()) {
                System.out.print("Sequence not found!\n");
                return;
            }
        }

        if(counter == 0) {
            System.out.print("Sequence not found!\n");
            return;
        }

        counter = 1;
        for(int i = 0; i < x.length - 1; i++) {
            if((x[i].getX() + 1 == x[i + 1].getX() || x[i].getX() - 1 == x[i + 1].getX()) && x[i].getY() == x[i + 1].getY()) {
                counter++;
                if(x[i + 1].isSolved()) {
                    System.out.printf("Sequence found! Number of boards in the Sequence: %d\n", counter);
                    return;
                }
            }

            else if((x[i].getY() + 1 == x[i + 1].getY() || x[i].getY() - 1 == x[i + 1].getY()) && x[i].getX() == x[i + 1].getX()) {
                counter++;
                if(x[i + 1].isSolved()) {
                    System.out.printf("Sequence found! Number of boards in the Sequence: %d\n", counter);
                    return;
                }
            }

            else
                counter = 0;
        }

        System.out.print("Sequence not found!");
    }

    public static void main(String[] args) {
        Scanner Input = new Scanner(System.in);
        Scanner filenameInput = new Scanner(System.in);

        int input;
        int cont = 1;

        boolean u;
        boolean boardCreated = false;
        
        String filename;

        BoardArray2D gameBoard = new BoardArray2D();

        while(true) {

            System.out.print("Choose an action(Set the gameboard first by loading from file or Set(8) action):\n");
            System.out.print("0 - Exit the program\n");
            System.out.print("1 - Print the game board.\n");
            System.out.print("2 - Print the game board as a string\n");
            System.out.print("3 - Check if the game board is solved\n");
            System.out.print("4 - Move\n");
            System.out.print("5 - Last Move\n");
            System.out.print("6 - Number of Moves\n");
            System.out.print("7 - Reset the board to the solution state\n");
            System.out.print("8 - Set/Change the size of the board\n");
            System.out.print("9 - Save\n");
            System.out.print("10 - Load\n");
            System.out.print("11 - Sequence Finder\n");
            input = Input.nextInt();

            switch(input) {
                case 0:
                    System.out.println("Terminating....");
                    System.exit(0);

                case 1:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    gameBoard.print();
                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 2:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    System.out.println("Board as string: " + gameBoard);
                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 3:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }


                    u = gameBoard.isSolved();
                    System.out.printf("%s\n\n", u ? "Solved" : "Not Solved");

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 4:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    System.out.print("Choose a direction(Enter 0 to go back):\n");
                    System.out.print("1 - Right\n");
                    System.out.print("2 - Left\n");
                    System.out.print("3 - Up\n");
                    System.out.print("4 - Down\n");
                    input = Input.nextInt();

                    if(input == 0)
                        break;

                    switch(input) {
                        case 1:
                            gameBoard.move(0);
                            break;

                        case 2:
                            gameBoard.move(1);
                            break;
                            
                        case 3:
                            gameBoard.move(2);
                            break;

                        case 4:
                            gameBoard.move(3);
                            break;   

                        default:
                            System.out.print("Wrong input!\n");
                            break;

                    }

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 5:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    System.out.println("Last move: " + gameBoard.lastMove());

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 6:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }


                    System.out.println("Total Moves Made: " + gameBoard.numberOfMoves());

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 7:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    gameBoard.reset();

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 8:
                    gameBoard.setSize();
                    Random rand = new Random();
                    int moveCount = rand.nextInt(20) + 5;
                    while(moveCount != 0) {
                        int direction = rand.nextInt(4);
                        if(gameBoard.moveRandom(direction) == 1)
                            moveCount--;
                    }
                    boardCreated = true;

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;  

                case 9:
                    if(!boardCreated) {
                        System.out.print("Game board isn't created yet!\n");
                        break;
                    }

                    System.out.print("Enter a file name to save the board: ");
                    filename = filenameInput.nextLine();
                    gameBoard.writeToFile(filename);

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                case 10:
                    System.out.print("Enter a file name to load the board: ");
                    filename = filenameInput.nextLine();
                    gameBoard.readFromFile(filename);

                    boardCreated = true;
                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    System.out.print("\n\n\n\n\n\n\n\n\n\n");
                    break;

                
                case 11:
                    System.out.print("This method can work for any size of arrays and boards\n");
                    System.out.print("This section will ask you to load 4(for ease of use) boards from files in order. You can use provided files or your own files.\n");
                    AbstractBoard [] a = new AbstractBoard [4];

                    BoardArray2D boardOne = new BoardArray2D();
                    System.out.print("Enter a file name to load the boardOne: ");
                    filename = filenameInput.nextLine();
                    boardOne.readFromFile(filename);
                    a[0] = boardOne;

                    boardOne = new BoardArray2D();
                    System.out.print("Enter a file name to load the boardTwo: ");
                    filename = filenameInput.nextLine();
                    boardOne.readFromFile(filename);
                    a[1] = boardOne;

                    boardOne = new BoardArray2D();
                    System.out.print("Enter a file name to load the boardThree: ");
                    filename = filenameInput.nextLine();
                    boardOne.readFromFile(filename);
                    a[2] = boardOne;

                    boardOne = new BoardArray2D();
                    System.out.print("Enter a file name to load the boardFour: ");
                    filename = filenameInput.nextLine();
                    boardOne.readFromFile(filename);
                    a[3] = boardOne;

                    sequence(a);

                    System.out.print("Enter '0' to continue...\n");
                    while(cont != 0) {
                        cont = Input.nextInt();
                    }
                    cont = 1;
                    break;
                
                default:
                    System.out.print("Wrong Choice, Try Again\n\n");
                    break;
            }
        }
    }
}
