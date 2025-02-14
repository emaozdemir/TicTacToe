import java.util.Scanner;
import java.util.Random;

public class TicTaToeOld
{
    public static void main(String[]args){
  
        Scanner reader = new Scanner(System.in);
        TTTBoard board = new TTTBoard();

        System.out.println(board);

        Random random = new Random();
        char player;
        
        if(random.nextInt(2) == 1)
        {
            player = 'O';
        }
        else
        {
            player = 'X';
        }
        System.out.println("Would you like to play against the computer?");
        String computer = reader.nextLine();

        while(board.getWinner() == '-' && !board.full())
        {
            int row = 0;
            int column = 0;
            boolean success = false;
            System.out.println(player + "'s turn.");

            if(computer.equals("No")||player=='X')
            {
                System.out.print("Enter the row and column[1-3, space, 1-3]");

                row = reader.nextInt();
                column = reader.nextInt();
                success = board.placeXorO(player, row, column);
            }
            else
            {
                while(!success)
                {
                    row = random.nextInt(3) +1;
                    column = random.nextInt(3) +1;
                    success = board.placeXorO(player, row, column);
                }
            }
            if(!success)
            {
                System.out.println("Error: cell already occupied");
            }
            else
            {
                System.out.println(board);
                if(player == 'X')
                {
                    player = 'O';
                }
                else
                {
                    player = 'X';
                }
            }
        }
        char winner = board.getWinner();
        if(winner != '-')
        {
            System.out.println(winner + "'s wins!");
        }
        else
        {
            System.out.println("It's a draw!");
        }

    }
}
