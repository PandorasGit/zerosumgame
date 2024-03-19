package problems;

public class TicTacToe implements Game<char[][], int[]>{


    //e.g. 3 means 3 x 3 board

    private final int BOARD_SIZE;
    private final char[][] board;
    private final boolean[][] marked;
    private Marks turn;

    public enum  Marks {X, O}

    public TicTacToe(int BOARD_SIZE, Marks turn) {
        this.BOARD_SIZE = BOARD_SIZE;
        this.turn = turn;
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.marked = new boolean[BOARD_SIZE][BOARD_SIZE];
        for(int row=0; row<BOARD_SIZE;row++){
            for(int column=0; column<BOARD_SIZE;column++){
                board[row][column] = ' ';
                marked[row][column] = false;
            }
        }
    }

    public boolean isTerminal(char[][] board){
        for(int row=0; row<BOARD_SIZE;row++){
            for(int column=0; column<BOARD_SIZE;column++){
                if(!marked[row][column]){
                    return false;
                }
            }
        }
    }


    public char[][] execute(int[] position, char[][] board) {
        if(turn == Marks.X){
            board[position[0]][position[1]] = Marks.X.toString().charAt(0);
        } else {
            board[position[0]][position[1]] = Marks.O.toString().charAt(0);
        }
        marked[position[0]][position[1]] = true;
        switchTurn();
        return board;
    }

    public char[][] undo(int[] position, char[][] board) {
        board[position[0]][position[1]] = ' ';
        marked[position[0]][position[1]] = false;
        switchTurn();
        return board;
    }

    private void switchTurn() {
        if(turn == Marks.X){
            turn = Marks.O;
        } else {
            turn = Marks.X;
        }
    }
}
