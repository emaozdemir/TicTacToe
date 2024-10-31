import java.util.Scanner;

public class Subject {
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        char[][] GameBoard = new char[3][3];

        for (int row = 0; row < GameBoard.length; row++) {
            for (int col = 0; col < GameBoard[row].length; col++) {
                GameBoard[row][col] = (char)('0' + row * 3 + col);
                System.out.print(GameBoard[row][col] + "|" + "\t");
            }
            System.out.println();
            for (int i = 0; i < GameBoard[row].length * 3; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        boolean GameOver = false;

        while (!GameOver) {

            System.out.println("Please enter 'X' or 'O' to choose your character: ");
            char playerSymbol = myScanner.next().charAt(0);
            if (playerSymbol != 'X' && playerSymbol != 'O') {
                System.out.println("Invalid input");
                break;
            }

            System.out.println(" Please enter a number from 0 to 8 to place your character ");
            int NumbersOnBoard = myScanner.nextInt();

            switch (NumbersOnBoard) {
                case 0:
                    GameBoard[0][0] = playerSymbol;
                    break;
                case 1:
                    GameBoard[0][1] = playerSymbol;
                    break;
                case 2:
                    GameBoard[0][2] = playerSymbol;
                    break;
                case 3:
                    GameBoard[1][0] = playerSymbol;
                    break;
                case 4:
                    GameBoard[1][1] = playerSymbol;
                    break;
                case 5:
                    GameBoard[1][2] = playerSymbol;
                    break;
                case 6:
                    GameBoard[2][0] = playerSymbol;
                    break;
                case 7:
                    GameBoard[2][1] = playerSymbol;
                    break;
                case 8:
                    GameBoard[2][2] = playerSymbol;
                    break;
                default:
                    System.out.println(":(");
                    return;
            }

            for (int row = 0; row < GameBoard.length; row++) {
                for (int col = 0; col < GameBoard[row].length; col++) {
                    System.out.print(GameBoard[row][col] + "|" + "\t");
                }
                System.out.println();
                for (int i = 0; i < GameBoard[row].length * 3; i++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }
}
