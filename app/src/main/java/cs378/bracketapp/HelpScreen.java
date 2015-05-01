package cs378.bracketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HelpScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_layout);

        Intent activityFromMain = getIntent();

    }
}
