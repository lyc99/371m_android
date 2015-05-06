package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class BracketScreen extends Activity {
    Spinner single_elimination_spinner;
    Spinner other_spinner;
    private static final String TAG = "MyActivity";

    //Spinner spinner;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draw_layout);

        single_elimination_spinner = (Spinner) findViewById(R.id.single_elimination_number_of_players_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number_of_players_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        single_elimination_spinner.setAdapter(adapter);
        //single_elimination_spinner.setOnItemSelectedListener(this);

        //now send selected item to another activity
        /*
        single_elimination_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(getApplicationContext(), SingleElimination.class);
                    intent.putExtra("selected", single_elimination_spinner.getSelectedItem().toString());
                    startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */

        other_spinner = (Spinner) findViewById(R.id.other_number_of_players_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.not_supported_array,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        other_spinner.setAdapter(adapter2);

        Intent activityFromMain = getIntent();

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.number_of_players_array,
//                android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        //int numOfPlayers = activityFromMain.getExtras().getInt("numberOfPlayers");

    }



    //when you press ENTER
    public void onSendSingleElimination(View view) {
        Intent getSingleEliminationIntent = new Intent(this, SingleElimination.class);

        Spinner editPlayerNumber = (Spinner) findViewById(R.id.single_elimination_number_of_players_spinner);
        int numOfPlayers = Integer.parseInt(editPlayerNumber.getSelectedItem().toString());
        getSingleEliminationIntent.putExtra("numberOfPlayers", numOfPlayers);

        //getSingleEliminationIntent.putExtra("numberOfPlayers", R.id.number_of_players_single_elimination);

        startActivity(getSingleEliminationIntent);

    }

//    @Override
    public void onSendBracketInformation( View view) {
//        if(check>0) {
//            Intent intent = new Intent(getApplicationContext(), SingleElimination.class);
//            intent.putExtra("selected", single_elimination_spinner.getSelectedItem().toString());
//            if(single_elimination_spinner.getSelectedItem().toString() != "")
//                startActivity(intent);
        Bundle loginBundle = getIntent().getBundleExtra("loginBundle");
        String uid = loginBundle.getString("uid");

        Log.d(TAG, "onSendBracketInfo called");
            Intent getPlayerManagerIntent = new Intent(this, PlayerManager.class);
            Spinner editPlayerNumber = (Spinner) findViewById(R.id.single_elimination_number_of_players_spinner);
            int numOfPlayers = Integer.parseInt(editPlayerNumber.getSelectedItem().toString());
            getPlayerManagerIntent.putExtra("numberOfPlayers", numOfPlayers);
            getPlayerManagerIntent.putExtra("uid", uid);
            Log.d(TAG, "startActivity playerManager");

            startActivity(getPlayerManagerIntent);
//        }
//        check++;
    }

//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
