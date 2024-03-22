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
        TicTacToe t = new TicTacToe(3, TicTacToe.Marks.O);
        search(t);
    }

    private void search(TicTacToe game) {
        System.out.println("Let's play Tic Tac Toe! It is your turn!\n");
        Scanner scanner = new Scanner(System.in);
        Minimax minimax = new Minimax(game, true);
        while (!game.isTerminal(game.getBoard())){
            int[] playerMove = new int[2];

            game.print(game.getBoard());
            System.out.println("What row would you like to mark in? \n");
            playerMove[0] = Integer.parseInt(scanner.nextLine());
            System.out.printf("What column would you like to mark in row %d? \n", playerMove[0]);
            playerMove[1] = Integer.parseInt(scanner.nextLine());
            
            game.execute(playerMove, game.getBoard());

            game.execute(minimaxSearch(game.getBoard()), game.getBoard());
        }
    }

}
