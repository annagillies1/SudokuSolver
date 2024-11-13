
public class SudokuSolver {
// specifying the grid size (row and col
    private static final int GRID_SIZE = 9;


    public static void main(String[] args) {

        int[][] board = {
                // a board from a sudoku solver configurator online
                // as it's a 2D array, 0 is in place of blanks
                // these numbers can be changed
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };


        printBoard(board);



        if (solveBoard(board)) {
            System.out.println("Solved!");
        } else {
            System.out.println("Impossible! :(");
        }
        printBoard(board);
    }

    private static void printBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    // helper methods to see if the number exists in a row
private static boolean isNumberInRow(int[][] board, int number, int row){
        for (int i = 0; i < GRID_SIZE; i++){
            // if we find the number exists in the row that we're searching in, return true
            if (board[row][i] == number){
                return true;
            }
        }
        // if the number doesn't exist then return false
        return false;
}
    // helper methods to see if the number exists in a row
    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for (int i = 0; i < GRID_SIZE; i++){
            // if we find the number exists in the column that we're searching in, return true
            if (board[i][column] == number){
                return true;
            }
        }
        // if the number doesn't exist then return false
        return false;
    }
    // helper methods to see if the number exists in a box
    private static boolean isNumberInBox(int[][] board, int number, int row, int column){
        // box formula for any box in the grid
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
        }
    return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    // checking to see if the number chosen is valid to pop in the available space
    // basically implementing the sudoku functionality
    // in the first blank spot, it will keep trying 1-9 until the move is valid

    private static boolean solveBoard(int[][] board) {
        //looping through the entire grid with a nested for loop
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                // checking to find the available spaces (0)
                if (board[row][column] == 0) {
                    // looping from 1-9
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            // recursion - if the board is solved it will return true
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                // even if the number was valid above, it could be a wrong move, so it'll then go through
                                // the next iteration of the for loop
                                board[row][column] = 0;
                            }
                        }
                    }
                    // if the board is unsolvable
                    return false;
                }
            }
        }
        return true;
    }
}