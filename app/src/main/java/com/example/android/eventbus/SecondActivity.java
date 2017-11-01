package com.example.android.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String RESULT_FROM_CHILD_ACTIVITY = "RESULT_FROM_CHILD_ACTIVITY";
    Boolean response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // get the extra from the intent that caused this activity
        Bundle extras = getIntent().getExtras();
        String extraString = extras.getString(eventbus.EXTRA_KEYS);

        TextView textView = (TextView) findViewById(R.id.secondActivityTextView);
        textView.setText(extraString);

        Button yesButton = (Button)findViewById(R.id.yesButton);
        Button noButton = (Button)findViewById(R.id.noButton);

        // set the reply to true when clicked
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = true;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_FROM_CHILD_ACTIVITY, response);
                // intent must be returned...
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        // set the reply to false when clicked
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = false;
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_FROM_CHILD_ACTIVITY, response);
                // intent must be returned...
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });


    }
}
