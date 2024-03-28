package solutions;

import core_algorithms.Minimax;
import problems.Game;
import problems.TicTacToe;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToePLayer extends Minimax<char[][], int[]>{
//TODO

    // while(not terminal)
        // Print current board
        // Let Human Player Input (ROW, COLUMN)
        // RUN MINIMAX SEARCH TO FIND THE BEST MOVE
        //
    // announce result (win or draw)
    public TicTacToePLayer(Game<char[][], int[]> game, boolean pruning) {
        super(game, pruning);
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3, TicTacToe.Marks.O);
        TicTacToePLayer t = new TicTacToePLayer(game, false);
        t.search();
    }

    public void search() {
        System.out.println("Let's play Tic Tac Toe! It is your turn!\n");
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe(3, TicTacToe.Marks.O);
        Minimax<char[][], int[]> minimax = new Minimax<char[][], int[]>(game, false);
        while (!game.isTerminal(game.getBoard())){
            int[] playerMove = new int[2];

            game.print(game.getBoard());
            System.out.println("What row would you like to mark in? \n");
            playerMove[0] = Integer.parseInt(scanner.nextLine());
            System.out.printf("What column would you like to mark in row %d? \n", playerMove[0]);
            playerMove[1] = Integer.parseInt(scanner.nextLine());

            game.execute(playerMove, game.getBoard());
            game.print(game.getBoard());
            if (!game.isTerminal(game.getBoard())){
                game.execute(minimax.minimaxSearch(game.getBoard()), game.getBoard());
            }

        }
        game.print(game.getBoard());
        if(game.utility(game.getBoard()) == 1){
            System.out.println("AI WINS");
        } else if (game.utility(game.getBoard()) == -1){
            System.out.println("Human victory");
        } else {
            System.out.println("Tie");
        }
    }

}
