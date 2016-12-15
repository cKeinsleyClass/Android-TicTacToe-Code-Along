package edu.rosehulman.keinslc.tictactoe;

import android.content.DialogInterface;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] mTicTacToeButtons;
    private Button mNewGameButton;
    private TextView mGameStateTextView;
    private TicTacToeGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new TicTacToeGame(this);

        // Generate the 2D array
        mTicTacToeButtons = new Button[TicTacToeGame.NUM_ROWS][TicTacToeGame.NUM_COLUMNS];
        // mTicTacToeButtons[0][0] = (Button)findViewById(R.idbutton00);
        for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
            for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
                // Gets the id for each button by building an ID
                // (name, type, packageName of this)
                int id = getResources().getIdentifier("button" + row + col, "id", getPackageName());
                mTicTacToeButtons[row][col] = (Button) findViewById(id);
                mTicTacToeButtons[row][col].setOnClickListener(this);
            }
        }

        mNewGameButton = (Button) findViewById(R.id.newGameButton);
        mNewGameButton.setOnClickListener(this);
        mGameStateTextView = (TextView) findViewById(R.id.textView);


    }

    @Override
    public void onClick(View view) {
        // Need to figure out which button was pressed
        Log.d("TTT", "on clicked called");
        if (view.getId() == R.id.newGameButton) {
            Log.d("TTT", "new game pressed");
            mGame.resetGame();
        } else {
            // Loop over buttons checking to see which one we clicked
            for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
                for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
                    if(view.getId() == mTicTacToeButtons[row][col].getId()){
                        mGame.pressedButtonAtLocation(row, col);
                    }
                }
            }
        }
        updateView();
    }

    // Updates the text in the view and buttons to what the game model provides
    private void updateView() {
        mGameStateTextView.setText(mGame.stringForGameState());
        for (int row = 0; row < TicTacToeGame.NUM_ROWS; row++) {
            for (int col = 0; col < TicTacToeGame.NUM_COLUMNS; col++) {
                mTicTacToeButtons[row][col].setText(mGame.stringForButtonAtLocation(row, col));
            }
        }
    }
}
