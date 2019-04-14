package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tries to see if an instance has been saved
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
            TextView errorText = findViewById(R.id.textView2);
            CharSequence messageDisplayed = savedInstanceState.getCharSequence("messageDisplayed");

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

            errorText.setText(messageDisplayed);

            if (game.won() !=  GameState.IN_PROGRESS){
                cancelButtons();
            }
        } else {
            game = new Game();
        }
    }

    public void tileClicked(View view) {
        int id = view.getId();
        Button clickedButton = (Button) view;
        TextView errorText = findViewById(R.id.textView2);
        int row = 0;
        int column = 0;

        errorText.setText("");

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

        TileState state = game.choose(row, column);

        switch(state) {
            case CROSS:
                clickedButton.setText("x");
                break;
            case CIRCLE:
                clickedButton.setText("o");
                break;
            case INVALID:
                errorText.setText("Invalid Move");
                break;
            case BLANK:
                break;
        }

        GameState progress = game.won();

        switch(progress) {
            case IN_PROGRESS:
                break;
            case PLAYER_ONE:
                errorText.setText("Player one has won!");
                break;
            case PLAYER_TWO:
                errorText.setText("Player two has won!");
                break;
            case DRAW:
                errorText.setText("The game results in a draw.");
                break;
        }

        if (progress != GameState.IN_PROGRESS) {
            cancelButtons();
        }
    }

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

    public void resetClicked(View view) {
        game = new Game();
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

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);

        TextView messageDisplayed = findViewById(R.id.textView2);
        outState.putCharSequence("messageDisplayed", messageDisplayed.getText());
    }
}
