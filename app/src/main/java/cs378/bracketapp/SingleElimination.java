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
    int numOfTextViews = 0;
    int numOfPlayers = 0;
    TextView tv[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.single_elimination_layout);
        Intent activityFromBracketScreen = getIntent();
        //int numOfPlayers;
        numOfPlayers = activityFromBracketScreen.getIntExtra("numberOfPlayers", -1);
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

                //textView.setText("   Player"+counter);
                tv[t_index].setText("   Player"+counter);
                //textView.setTextSize(20);
                tv[t_index].setTextSize(20);
                //set Id of textView
                //textView.setId(t_index);
                tv[t_index].setId(t_index);
                //put textView in tv
                //tv[t_index] = textView;

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
        int h_inc = prevTop + (box_height - 50);
        int newTop = prevTop + (box_height - 50);
        int mul = 2;
        while(num >= 1) {
            //int newTop = prevTop + (box_height - 50);
            int newLeft = prevLeft + box_width + 20;

            //prevTop = newTop + box_height/2;
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
                //params.topMargin = prevTop;
                //newTop += (temp + 50) ;

                left_lastbox = newLeft;
                top_lastbox = newTop;

                newTop += (box_height * mul);
                parentLayout.addView(outer, params);

                //now add textviews to the outer box
                LayoutInflater layoutInflater1 = getLayoutInflater();
                View v1;
                for(int j=0; j<2; j++) {
                    v1 = layoutInflater1.inflate(R.layout.box, outer, false);
                    //TextView textView = (TextView)v1.findViewById(R.id.box);
                    tv[t_index] = (TextView)v1.findViewById(R.id.box);

                    //tv[t_index].setText("   Player"+t_index);

                    //textView.setTextSize(20);
                    tv[t_index].setTextSize(20);
                    //set Id of textView
                    //textView.setId(t_index);
                    tv[t_index].setId(t_index);
                    //put textView in tv
                    //tv[t_index] = textView;

                    //outer.addView(textView);
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
        //topMar += box_height;
        parentLayout.addView(outer, params);
        //parentLayout.addView(outer);
        //we are going to inflate in "outer" too
        LayoutInflater layoutInflater1 = getLayoutInflater();
        View v1;

        v1 = layoutInflater1.inflate(R.layout.box, outer, false);
        //TextView textView = (TextView)v1.findViewById(R.id.box);
        tv[t_index] = (TextView)v1.findViewById(R.id.box);

        //tv[t_index].setText("   Player"+t_index);

        //textView.setTextSize(20);
        tv[t_index].setTextSize(20);
        //set Id of textView
        //textView.setId(t_index);
        tv[t_index].setId(t_index);
        //put textView in tv
        //tv[t_index] = textView;

        //outer.addView(textView);
        outer.addView(tv[t_index]);

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

    public void onNameProgressClick(View view) {
        int size = numOfTextViews;
        int remain = numOfPlayers;

        TextView selectedView = (TextView) view;
        String playerName = selectedView.getText().toString();
        int playerId = selectedView.getId();
        boolean doNothing = false;

        if (playerId < tv.length-3) {

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

            //numOfplayers

            //tv[calculatedId].setText(playerName + " " + playerId + " " + numOfPlayers);
            if(calculatedId != -1) {
                tv[calculatedId].setText(playerName);
            }

        }

        else {
            tv[tv.length-1].setText(playerName);
        }

    }
}
