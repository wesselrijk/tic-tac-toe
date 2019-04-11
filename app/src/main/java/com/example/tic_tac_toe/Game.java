package com.example.tic_tac_toe;

import java.io.Serializable;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn; // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    // Constructor for the game
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

    public TileState choose(int row, int column) {
        movesPlayed++;
        if (board[row][column] == TileState.BLANK) {
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
            movesPlayed--;
            return TileState.INVALID;
        }
    }

    public GameState won() {
        // check diagonally for a winning row of 3
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != TileState.BLANK
                || board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != TileState.BLANK ) {
            gameOver = true;// WIN
        }
        // check horizontally and vertically for a winning row of 3
        for (int i = 0; i<BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != TileState.BLANK
                    || board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != TileState.BLANK) {
                gameOver = true;// WIN
            }
        }
        // see if player one or two has won
        if (gameOver) {
            if (playerOneTurn) {
                return GameState.PLAYER_TWO;
            } else {
                return GameState.PLAYER_ONE;
            }
        }
        // check number of moves
        if (movesPlayed == 9) {
            gameOver = true;
            return GameState.DRAW;
        }
        // return in progress if the game keeps going
        return GameState.IN_PROGRESS;
    }

    public TileState[][] getBoard() {
        return board;
    }
}
