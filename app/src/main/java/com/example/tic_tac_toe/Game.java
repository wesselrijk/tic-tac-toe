package com.example.tic_tac_toe;
/*
File containing the game class of the app. This is where the game of the app takes place. Methods
are given to check what the state of each tile and the state of the entire game is. There is
also a method that gets the TileState.
 */

// only one import is used here
import java.io.Serializable;

// instantiate the game class, which is made serializable
public class Game implements Serializable {

    // instantiate variables necessary for the game
    final private int BOARD_SIZE = 3; // size of the board
    private TileState[][] board; // two dimensional
    private Boolean playerOneTurn; // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    // constructor for the game, goes over the whole board and fills the tiles in as blank
    // also sets playerOneTurn and gameOver
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j=0; j<BOARD_SIZE; j++) {
                board[i][j] = TileState.BLANK;
            }
        }
        playerOneTurn = true;
        gameOver = false;
    }

    // a method for checking the TileState corresponding to an input row and column
    // that TileState is returned
    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK) {
            movesPlayed++;
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        } else {
            return TileState.INVALID;
        }
    }

    // a method to check the GameState, checks if a player has won, if the game results in a draw
    // or if the game is still ongoing
    public GameState won() {

        // check diagonally for a winning row of 3
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != TileState.BLANK
                || board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != TileState.BLANK ) {
            gameOver = true;// WIN
            if (playerOneTurn) {
                return GameState.PLAYER_TWO;
            } else {
                return GameState.PLAYER_ONE;
            }
        }

        // check horizontally and vertically for a winning row of 3
        for (int i = 0; i<BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != TileState.BLANK
                    || board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != TileState.BLANK) {
                gameOver = true;// WIN
                if (playerOneTurn) {
                    return GameState.PLAYER_TWO;
                } else {
                    return GameState.PLAYER_ONE;
                }
            }
        }

        // check number of moves to see if the game results in a draw
        if (movesPlayed == 9) {
            gameOver = true;
            return GameState.DRAW;
        }

        // return in progress if the game keeps going
        return GameState.IN_PROGRESS;
    }

    // a method for returning the TileState of the board
    public TileState[][] getBoard() {
        return board;
    }
}
