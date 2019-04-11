package com.example.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void tileClicked(View view) {
        int id = view.getId();
        Button clickedButton = findViewById(id);
        int row = 0;
        int column = 0;

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
                // do something different
                break;
        }
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

    }
}
