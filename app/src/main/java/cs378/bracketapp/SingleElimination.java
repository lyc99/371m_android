package cs378.bracketapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SingleElimination extends ActionBarActivity{

    //sounds we're gonna use
    private SoundPool mSounds;
    private int mBracketClickSoundID;
    private int mBracketWinnerSoundID;

    //Sound settings
    public enum soundSettings {On, Off};
    //Current sound settings
    private soundSettings mSoundSettings = soundSettings.On;

    static final int DIALOG_SOUND_ID = 0;

    public String[] players;
    private static final String TAG = "MyActivity";
    int numOfTextViews = 0;
    int numOfPlayers = 0;
    TextView tv[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getBundleExtra("playerBundle");
        numOfPlayers = extras.getInt("numberOfPlayers");
        players = extras.getStringArray("playerNames");
        Log.d(TAG, "number of players: "+numOfPlayers);
        //we get the number that was selected at drop down

        //set the number of text views we need
        int t = numOfPlayers;
        while(t > 1) {
            numOfTextViews += t;
            t /= 2;
        }
        numOfTextViews += 1;
        //tv will contain all the text views
        tv = new TextView[numOfTextViews];
        int t_index = 0;

        setContentView(R.layout.single_elimination_layout);

        //parent layout
        RelativeLayout parentLayout = (RelativeLayout)findViewById(R.id.single_elimination_layout);
        //layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View v;

        float f_origTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        float f_origLeft = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        float f_box_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
        float f_box_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
        float f_topMar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        float f_leftMar = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        int origTop = (int)f_origTop; int origLeft = (int)f_origLeft;
        int box_width = (int)f_box_width; int box_height = (int)f_box_height;
        int counter = 1; int topMar = (int)f_topMar; int leftMar = (int)f_leftMar;

        int num = numOfPlayers/2;
        for(int i=0; i < num; i++) {
            //add the text layout to the parent layout
            v = layoutInflater.inflate(R.layout.outerbox, parentLayout, false);
            //get the outer box
            LinearLayout outer = (LinearLayout)v.findViewById(R.id.outerBox);

            //Set position of the box
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(box_width,box_height);
            params.leftMargin = leftMar;
            params.topMargin = topMar;
            topMar += box_height;
            parentLayout.addView(outer, params);
            //parentLayout.addView(outer);
            //we are going to inflate in "outer" too
            LayoutInflater layoutInflater1 = getLayoutInflater();
            View v1;
            for(int j=0; j<2; j++) {
                v1 = layoutInflater1.inflate(R.layout.box, outer, false);
                //TextView textView = (TextView)v1.findViewById(R.id.box);
                tv[t_index] = (TextView)v1.findViewById(R.id.box);

                //setting the name using the string array from bundle
                tv[t_index].setText("   "+players[t_index]);
                Log.d(TAG, "   Player"+counter);

                tv[t_index].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                //set Id of textView
                tv[t_index].setId(t_index);

                //outer.addView(textView);
                outer.addView(tv[t_index]);

                //increment index
                t_index++;

                counter++;
            }
        }
        int temp = box_height; int left_lastbox=0; int top_lastbox=0;
        //now make the rest of the bracket
        topMar = 150; int prevTop = origTop; int prevLeft = origLeft;
        float f_h = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        float f_l = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

        int h_inc = prevTop + (box_height - (int)f_h);
        int newTop = prevTop + (box_height - (int)f_h);
        int mul = 2;
        while(num >= 1) {
            int newLeft = prevLeft + box_width + (int)f_l;

            prevTop = newTop;
            prevLeft = newLeft;

            //add outer boxes
            temp += box_height;
            for(int i=0; i<num/2; i++) {
                //add the text layout to the parent layout
                v = layoutInflater.inflate(R.layout.outerbox, parentLayout, false);
                //get the outer box
                LinearLayout outer = (LinearLayout)v.findViewById(R.id.outerBox);

                //Set position of the box
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(box_width,box_height);
                params.leftMargin = newLeft;
                params.topMargin = newTop;

                left_lastbox = newLeft;
                top_lastbox = newTop;

                newTop += (box_height * mul);
                parentLayout.addView(outer, params);

                //now add textviews to the outer box
                LayoutInflater layoutInflater1 = getLayoutInflater();
                View v1;
                for(int j=0; j<2; j++) {
                    v1 = layoutInflater1.inflate(R.layout.box, outer, false);

                    tv[t_index] = (TextView)v1.findViewById(R.id.box);

                    tv[t_index].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    //set Id of textView
                    tv[t_index].setId(t_index);

                    outer.addView(tv[t_index]);

                    //increment index
                    t_index++;

                }
            }
            num = num / 2;

            newTop = prevTop + (h_inc *(mul/2));
            mul *= 2;
        }
        //now place the last box
        v = layoutInflater.inflate(R.layout.outerbox, parentLayout, false);
        //get the outer box
        LinearLayout outer = (LinearLayout)v.findViewById(R.id.outerBox);

        //Set position of the box
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(box_width,box_height/2);
        params.leftMargin = left_lastbox + 20+ box_width;
        params.topMargin = top_lastbox + box_height/4;

        parentLayout.addView(outer, params);

        //we are going to inflate in "outer" too
        LayoutInflater layoutInflater1 = getLayoutInflater();
        View v1;

        v1 = layoutInflater1.inflate(R.layout.box, outer, false);

        tv[t_index] = (TextView)v1.findViewById(R.id.box);

        tv[t_index].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        //set Id of textView

        tv[t_index].setId(t_index);

        //outer.addView(textView);
        outer.addView(tv[t_index]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.single_elimination_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar item clicks here
        switch(item.getItemId()) {
//            case R.id.action_settings:
//                return true;

            case R.id.click_sounds:
                showDialog(DIALOG_SOUND_ID);
                return true;
        }

        return false;
    }
    protected Dialog onCreateDialog(int id) {
        Log.d(TAG, "In onCreateDialog");
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch (id) {
            case DIALOG_SOUND_ID:
                builder.setTitle("Choose your sound setting: ");
                final CharSequence[] levels = {
                        "Sound On",
                        "Sound Off"};
                final int selected = getSoundSettings().ordinal();
                Log.d(TAG, "selected setting value: "+selected+", setting: "+getSoundSettings());

                builder.setSingleChoiceItems(levels, selected,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                dialog.dismiss(); //close dialog

                                setSoundSettings(soundSettings.values()[item]);
                                Log.d(TAG, "sound setting: "+getSoundSettings());

                                //Display selected sound setting
                                Toast.makeText(getApplicationContext(), levels[item], Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog = builder.create();
                break; //this case

        }
        if(dialog == null)
            Log.d(TAG, "No! Dialog is null");
        else
            Log.d(TAG, "Dialog created: "+ id + ", dialog: "+dialog);

        return dialog;
    }

    //Getters and Setters
    public soundSettings getSoundSettings() {return mSoundSettings;}
    public void setSoundSettings(soundSettings s) {
        mSoundSettings = s;
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        mBracketClickSoundID = mSounds.load(this, R.raw.bracket_click, 1);
        mBracketWinnerSoundID = mSounds.load(this, R.raw.bracket_winner, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "in onPause");

        if(mSounds != null) {
            mSounds.release();
            mSounds = null;
        }
    }

    public void onNameProgressClick(View view) {
        int size = numOfTextViews;
        int remain = numOfPlayers;

        TextView selectedView = (TextView) view;
        String playerName = selectedView.getText().toString();
        int playerId = selectedView.getId();
        boolean doNothing = false;

        int calculatedId = -1;

        if (playerId < tv.length-3) {

            //get the text view id (or index in "tv") that the name should progress to
            calculatedId = getPlayerIndex(playerId, remain);

            if(calculatedId != -1) {
                if(tv[calculatedId].getText() != "" && playerName != tv[calculatedId].getText()) {
                    Log.d(TAG, "stored text: " +tv[calculatedId].getText()+" playerName: "+playerName);
                    //we have to set the text to the new player and get rid of the old player's names
                    String oldPlayer = (String)tv[calculatedId].getText();
                    Log.d(TAG, "oldPlayer: "+oldPlayer);
                    //get rid of all the oldPlayer names from the bracket
                    boolean done = false;
                    int newCalculatedId = calculatedId;
                    String pn = (String) tv[newCalculatedId].getText();
                    while(done == false) {
                        if(pn != "" && newCalculatedId != -1) {
                            pn = (String) tv[newCalculatedId].getText();
                            Log.d(TAG, "pn: "+pn+" newCalculatedId: "+newCalculatedId);
                            if (pn != "" && oldPlayer == pn) {
                                int c = newCalculatedId;
                                tv[newCalculatedId].setText("");
                                Log.d(TAG, "newCalculatedId old: "+newCalculatedId);
                                newCalculatedId = getPlayerIndex(c, numOfPlayers);
                                Log.d(TAG, "newCalculatedId new: "+newCalculatedId);
                            }
                            else {
                                done = true;
                            }
                        }
                        else {
                            done = true;
                        }
                    }
                    //after getting rid of all oldNames, set the new name in the next box
                    tv[calculatedId].setText(playerName);
                    if (mSoundSettings == soundSettings.On) {
                        mSounds.play(mBracketClickSoundID, 1, 1, 1, 0, 1);
                    }

                    //make sure the champion's name is gone too if it is set to the old name
                    if(tv[tv.length-1].getText() == oldPlayer) {
                        tv[tv.length-1].setText("");
                    }
                }
                else {
                    tv[calculatedId].setText(playerName);
                    if (mSoundSettings == soundSettings.On) {
                        mSounds.play(mBracketClickSoundID, 1, 1, 1, 0, 1);
                    }
                }

            }

        }

        else {
            tv[tv.length-1].setText(playerName);
            if(mSoundSettings == soundSettings.On) {
                mSounds.play(mBracketWinnerSoundID,1,1,1,0,1);
            }
        }

    }

    public int getPlayerIndex(int playerId, int remain) {
        int ranges[] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int index = 0;
        int r = remain;
        int previousRange = 0;
        //figure out the correct range (column) of selected textview
        while (r > 1) {
            ranges[index] = r + previousRange;
            previousRange = ranges[index];
            index++;
            r = r / 2;
        }
        index = 0;
        boolean found = false;
        int correctRange = 0;
        while (found == false) {
            if (playerId < ranges[index]) {
                correctRange = index;
                found = true;
            }
            index++;
        }
        index = 0;

        //the correct range (column) is found. now find the correct textview id to copy name to
        int rangeToCopy = correctRange + 1;
        //int destinationIDs[] = new int[]{-1, -1, -1, -1, -1};
        int destinationIDs[] = new int[1000];
        for(int i=0; i<destinationIDs.length; i++) {
            destinationIDs[i] = -1;
        }
        double n = numOfPlayers / Math.pow(2.0, correctRange);
        int numberOfOptions = (int) n;

        int a = ranges[correctRange];
        for(int i=0; i< (ranges[rangeToCopy] - ranges[correctRange]); i++) {
            destinationIDs[i] = a;
            a++;
        }

        int idIndex;
        //now find the correct textview id
        if(playerId < numOfPlayers) {
            idIndex = playerId / 2;
        }
        else {
            //idIndex = (playerId - numberOfOptions * 2) / 2;
            idIndex = (playerId - (ranges[correctRange-1])) / 2;
        }
        int calculatedId = destinationIDs[idIndex];

        return calculatedId;
    }

}