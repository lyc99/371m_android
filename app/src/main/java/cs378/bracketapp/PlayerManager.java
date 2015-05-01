package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by justin on 4/6/2015.
 */
public class PlayerManager extends Activity
{

    public String[] playerNames;
    private int numPlayers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_layout);
        Intent activityFromBracketScreen = getIntent();
        // Initialize the array based on number of players.
        numPlayers = activityFromBracketScreen.getIntExtra("numberOfPlayers", -1);
        playerNames = new String[numPlayers];
        // Hide excess EditText fields
        LinearLayout layout = (LinearLayout)findViewById(R.id.player_layout);
        for(int i = 0; i < numPlayers; i++)
        {
            EditText tempText = (EditText) layout.getChildAt(i);
            tempText.setVisibility(View.VISIBLE);
        }


    }

    public void onSendPlayerData(View view) {
        Intent getSingleEliminationIntent = new Intent(this, SingleElimination.class);
        LinearLayout layout = (LinearLayout)findViewById(R.id.player_layout);
        Bundle playerBundle = new Bundle();
        // Places all of the player names into a String Array
        for(int i = 0; i < numPlayers; i++)
        {
            EditText tempText = (EditText) layout.getChildAt(i);
            playerNames[i] = tempText.getText().toString();
        }
        playerBundle.putStringArray("playerNames", playerNames);
        playerBundle.putInt("numberOfPlayers", numPlayers);
        getSingleEliminationIntent.putExtra("playerBundle", playerBundle);
        //Intent intent = new Intent(getApplicationContext(), SingleElimination.class);
//            intent.putExtra("selected", single_elimination_spinner.getSelectedItem().toString());
        startActivity(getSingleEliminationIntent);

    }
}
