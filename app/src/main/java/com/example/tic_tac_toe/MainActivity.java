package com.example.tic_tac_toe;
/*
The MainActivity for the Tic-Tac-Toe app, contains the Mainactivity class. In onCreate a game is
set for the user. The user can click buttons in the layout, which make the game play out accordingly
through the methods included in the MainActivity class tileClicked and resetClicked.
A method is applied for cancelling all buttons if necessary. The game will be saved in an instance.
 */

// list of imports
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// the MainActivity class is instantiated. Within, the Game class is instantiated straight away
public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tries to see if an instance has been saved, sets the game to a saved instance
        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("Game");

            // create a copy of the tileboard (which has been set private in game)
            TileState[][] board = game.getBoard();

            // define all buttons
            Button button1_1 = findViewById(R.id.button1_1);
            Button button1_2 = findViewById(R.id.button1_2);
            Button button1_3 = findViewById(R.id.button1_3);
            Button button2_1 = findViewById(R.id.button2_1);
            Button button2_2 = findViewById(R.id.button2_2);
            Button button2_3 = findViewById(R.id.button2_3);
            Button button3_1 = findViewById(R.id.button3_1);
            Button button3_2 = findViewById(R.id.button3_2);
            Button button3_3 = findViewById(R.id.button3_3);
            TextView messageText = findViewById(R.id.textView2);
            CharSequence messageDisplayed = savedInstanceState.getCharSequence(
                    "messageDisplayed");

            // set buttons to saved instance
            if (board[0][0] == TileState.CROSS) {
                button1_1.setText("x");
            } else if (board[0][0] == TileState.CIRCLE) {
                button1_1.setText("o");
            }
            if (board[0][1] == TileState.CROSS) {
                button1_2.setText("x");
            } else if (board[0][1] == TileState.CIRCLE) {
                button1_2.setText("o");
            }
            if (board[0][2] == TileState.CROSS) {
                button1_3.setText("x");
            } else if (board[0][2] == TileState.CIRCLE) {
                button1_3.setText("o");
            }
            if (board[1][0] == TileState.CROSS) {
                button2_1.setText("x");
            } else if (board[1][0] == TileState.CIRCLE) {
                button2_1.setText("o");
            }
            if (board[1][1] == TileState.CROSS) {
                button2_2.setText("x");
            } else if (board[1][1] == TileState.CIRCLE) {
                button2_2.setText("o");
            }
            if (board[1][2] == TileState.CROSS) {
                button2_3.setText("x");
            } else if (board[1][2] == TileState.CIRCLE) {
                button2_3.setText("o");
            }
            if (board[2][0] == TileState.CROSS) {
                button3_1.setText("x");
            } else if (board[2][0] == TileState.CIRCLE) {
                button3_1.setText("o");
            }
            if (board[2][1] == TileState.CROSS) {
                button3_2.setText("x");
            } else if (board[2][1] == TileState.CIRCLE) {
                button3_2.setText("o");
            }
            if (board[2][2] == TileState.CROSS) {
                button3_3.setText("x");
            } else if (board[2][2] == TileState.CIRCLE) {
                button3_3.setText("o");
            }
            
            // sets a saved 
            messageText.setText(messageDisplayed);

            if (game.won() !=  GameState.IN_PROGRESS){
                cancelButtons();
            }
        } else {
            // set a new game if no instance was saved
            game = new Game();
        }
    }

    // method indicating what happens when a button (tile) is clicked
    public void tileClicked(View view) {
        // get an id, instantiate a row and a column and get the clickedButton and messageText,
        // also resets the messageText to contain no text
        int id = view.getId();
        int row = 0;
        int column = 0;
        Button clickedButton = (Button) view;
        TextView messageText = findViewById(R.id.textView2);
        messageText.setText("");

        // switch goes over id to find the corresponding row and column for a button
        switch(id) {
            case R.id.button1_1:
                row = 0;
                column = 0;
                break;
            case R.id.button1_2:
                row = 0;
                column = 1;
                break;
            case R.id.button1_3:
                row = 0;
                column = 2;
                break;
            case R.id.button2_1:
                row = 1;
                column = 0;
                break;
            case R.id.button2_2:
                row = 1;
                column = 1;
                break;
            case R.id.button2_3:
                row = 1;
                column = 2;
                break;
            case R.id.button3_1:
                row = 2;
                column = 0;
                break;
            case R.id.button3_2:
                row = 2;
                column = 1;
                break;
            case R.id.button3_3:
                row = 2;
                column = 2;
                break;
        }

        // instantiate a tilestate, which is set in the game,
        TileState state = game.choose(row, column);

        // switch over the tilestate, give a corresponding reaction to each tilestate
        switch(state) {
            case CROSS:
                clickedButton.setText("x");
                break;
            case CIRCLE:
                clickedButton.setText("o");
                break;
            case INVALID:
                messageText.setText("Invalid Move");
                break;
            case BLANK:
                break;
        }

        // instantiate a gamestate, which is set in the game
        GameState progress = game.won();

        // switch over the gamestate, give a corresponding reaction to the state of the game
        switch(progress) {
            case IN_PROGRESS:
                break;
            case PLAYER_ONE:
                messageText.setText("Player one has won!");
                break;
            case PLAYER_TWO:
                messageText.setText("Player two has won!");
                break;
            case DRAW:
                messageText.setText("The game results in a draw.");
                break;
        }

        // if the game is over, all puttons will be cancelled
        if (progress != GameState.IN_PROGRESS) {
            cancelButtons();
        }
    }

    // method for when the reset button has been clicked, creates a new game, instantiates all
    // buttons and sets the text to contain no text and make them clickable, also empties the
    // text message
    public void resetClicked(View view) {
        game = new Game();
        TextView messageText = findViewById(R.id.textView2);
        Button button1_1 = findViewById(R.id.button1_1);
        Button button1_2 = findViewById(R.id.button1_2);
        Button button1_3 = findViewById(R.id.button1_3);
        Button button2_1 = findViewById(R.id.button2_1);
        Button button2_2 = findViewById(R.id.button2_2);
        Button button2_3 = findViewById(R.id.button2_3);
        Button button3_1 = findViewById(R.id.button3_1);
        Button button3_2 = findViewById(R.id.button3_2);
        Button button3_3 = findViewById(R.id.button3_3);
        button1_1.setText("");
        button1_2.setText("");
        button1_3.setText("");
        button2_1.setText("");
        button2_2.setText("");
        button2_3.setText("");
        button3_1.setText("");
        button3_2.setText("");
        button3_3.setText("");
        button1_1.setClickable(true);
        button1_2.setClickable(true);
        button1_3.setClickable(true);
        button2_1.setClickable(true);
        button2_2.setClickable(true);
        button2_3.setClickable(true);
        button3_1.setClickable(true);
        button3_2.setClickable(true);
        button3_3.setClickable(true);
        messageText.setText("");
    }

    // method for cancelling all buttons, instantiates all buttons and cancels them afterwards
    public void cancelButtons() {
        Button button1_1 = findViewById(R.id.button1_1);
        Button button1_2 = findViewById(R.id.button1_2);
        Button button1_3 = findViewById(R.id.button1_3);
        Button button2_1 = findViewById(R.id.button2_1);
        Button button2_2 = findViewById(R.id.button2_2);
        Button button2_3 = findViewById(R.id.button2_3);
        Button button3_1 = findViewById(R.id.button3_1);
        Button button3_2 = findViewById(R.id.button3_2);
        Button button3_3 = findViewById(R.id.button3_3);
        button1_1.setClickable(false);
        button1_2.setClickable(false);
        button1_3.setClickable(false);
        button2_1.setClickable(false);
        button2_2.setClickable(false);
        button2_3.setClickable(false);
        button3_1.setClickable(false);
        button3_2.setClickable(false);
        button3_3.setClickable(false);
    }

    // method for saving the instance, saves the gamestate and the displayed message
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
        TextView messageDisplayed = findViewById(R.id.textView2);
        outState.putCharSequence("messageDisplayed", messageDisplayed.getText());
    }
}
