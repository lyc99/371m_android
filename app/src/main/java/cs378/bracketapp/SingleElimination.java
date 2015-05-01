package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SingleElimination extends Activity{

    public String[] players;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.single_elimination_layout);
        Intent activityFromBracketScreen = getIntent();
        int numOfPlayers;
        numOfPlayers = activityFromBracketScreen.getIntExtra("numberOfPlayers", -1);
        //we get the number that was selected at drop down

        setContentView(R.layout.single_elimination_layout);

        //parent layout
        RelativeLayout parentLayout = (RelativeLayout)findViewById(R.id.single_elimination_layout);
        //layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View v;
        final int origTop = 50; final int origLeft = 20;
        int box_width = 150; int box_height = 120;
        int counter = 1;    int topMar = 50;    int leftMar = 20;
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
            topMar += 150;
            parentLayout.addView(outer, params);
            //parentLayout.addView(outer);
            //we are going to inflate in "outer" too
            LayoutInflater layoutInflater1 = getLayoutInflater();
            View v1;
            for(int j=0; j<2; j++) {
                v1 = layoutInflater1.inflate(R.layout.box, outer, false);
                TextView textView = (TextView)v1.findViewById(R.id.box);
                textView.setText("   Player"+counter);
                textView.setTextSize(20);
                outer.addView(textView);
                counter++;
            }
        }
        int temp = box_height;
        //now make the rest of the bracket
        topMar = 150; int prevTop = origTop; int prevLeft = origLeft;
        while(num >= 1) {
            int newTop = prevTop + (box_height/2) + (20/2);
            int newLeft = prevLeft + box_width + 20;

            prevTop = newTop + box_height/2;
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
                newTop += (temp + 50) ;
                parentLayout.addView(outer, params);
            }
            num = num / 2;
        }

//        for(int i=1; i<=numOfPlayers; i++) {
//            //add the text layout to the parent layout
//            v = layoutInflater.inflate(R.layout.box, parentLayout, false);
//            //in order to get the view we have to use the new "box" layout in it
//            TextView textView = (TextView)v.findViewById(R.id.box);
//            textView.setText("Player"+i);
//
//            //Add the text view to the parent layout
//            parentLayout.addView(textView);
//        }

        /*
        Bundle extras = getIntent().getBundleExtra("playerBundle");
        int selectedNumber = extras.getInt("numberOfPlayers");
        players = extras.getStringArray("playerNames");
        Log.d(TAG, "selectedNumber =" + selectedNumber);

        if(selectedNumber == 16) {
            setContentView(R.layout.bracket16);
        }

        else if(selectedNumber == 8)
            setContentView(R.layout.bracket8);
        else if(selectedNumber == 4)
            setContentView(R.layout.bracket4);
        else
            setContentView(R.layout.single_elimination_layout);

        TextView textView;
        StringBuilder textViewName = new StringBuilder();
        textViewName.append("textView");
        for(int i = 0; i < selectedNumber; i++)
        {
            textViewName.append("" + i);
            int id = getResources().getIdentifier(textViewName.toString(), "id", getPackageName());
            if (id != 0) {
                 textView = (TextView) findViewById(id);
                 textView.setText(players[i]);
            }
                textViewName.deleteCharAt(textViewName.length()-1);
            if(i>9)
                textViewName.deleteCharAt(textViewName.length()-1);
        }
        */

//        Intent activityFromBracketScreen = getIntent();
//        if(extras != null) {
//           selectedNumber = extras.getString("selected");
//            TextView tv = (TextView)findViewById(R.id.single_elimination_title);
//            tv.setText(selectedNumber);
//        }
//
//        int numOfPlayers = activityFromBracketScreen.getExtras().getInt("numberOfPlayers");

    }
}
