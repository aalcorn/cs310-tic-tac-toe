package edu.jsu.mcis;

public class TicTacToeModel {
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        /* INSERT YOUR CODE HERE */
        grid = new Mark[width][width];
        /* Initialize grid by filling every square with empty marks */
        /* INSERT YOUR CODE HERE */
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++)
                grid[i][j] = Mark.EMPTY; 
        }
        //grid[0][1] = Mark.X; Use this to see where it puts the mark
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
        
        /* INSERT YOUR CODE HERE */
        boolean result = false;
        if (isValidSquare(row,col) && !isSquareMarked(row,col)) {
            if (xTurn) {
                grid[row][col] = Mark.X;
            }
            else {
                grid[row][col] = Mark.O;
            }
            result = true;
            xTurn = !xTurn; 
        }
        return result;
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        
        /* INSERT YOUR CODE HERE */
        if ((row < width) && (row >= 0) && (col < width) && (col >= 0)) {
            return true;
        }
        else {
            return false;
        }
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
        
        /* INSERT YOUR CODE HERE */
        if (grid[row][col] == Mark.EMPTY) {
            return false;
        }
        else {
            return true;
        }
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
        
        /* INSERT YOUR CODE HERE */
        Mark theMark = grid[row][col];

        return theMark;
            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
        /* INSERT YOUR CODE HERE */
        if (isMarkWin(Mark.X)) {
            return Result.X;
        }
        else if (isMarkWin(Mark.O)) {
            return Result.O;
        }
        else if (isTie()) {
            return Result.TIE;
        }
        else {
            return Result.NONE;
        }

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        /* INSERT YOUR CODE HERE */
        //check vertically
        int counter = 0;
        for (int i = 0; i < width; i++) {
            counter = 0;
            for (int j = 0; j < width; j++) {
                if (getMark(i,j) == mark) {
                    counter++;
                }
            }
            if (counter == width) {
                return true;
            }
        }
        //check horizontally
        for (int a = 0; a < width; a++) {
            counter = 0;
            for (int b = 0; b < width; b++){
                if (getMark(b,a) == mark) {
                    counter++;
                }
                if (counter == width) {
                    return true;
                }
            }
        }

        //check diagonally from top left to bottom right
        counter = 0;
        for (int c = 0; c < width; c++) {
            if (getMark(c,c) == mark) {
                counter++;
            }
        }
        if (counter == width) {
            return true;
        } 

        //check diagonally from top right to bottom left;
        counter = 0;
        for(int d = 0; d < width; d++) {
            if (getMark(d,width-d-1) == mark) {
                counter++;
            }
        }
        if (counter == width) {
            return true;
        } 
        return false; /* remove this line! */
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        /* INSERT YOUR CODE HERE */
        if (isMarkWin(Mark.X) || isMarkWin(Mark.O)) {
            return false;
        } 
        for (int i = 0; i < width; i++) {
            for(int j = 0;j < width; j++) {
                if (getMark(i,j) == Mark.EMPTY) {
                    return false;
                }
            }
        }

        return true;
        
    }

    public boolean isGameover() {
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
}