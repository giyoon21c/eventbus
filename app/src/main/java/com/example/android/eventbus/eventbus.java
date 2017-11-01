package com.example.android.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class eventbus extends AppCompatActivity {

    public static final String EXTRA_KEYS = "EXTRA_KEYS";
    Button detailsButton;
    Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);

        EventBus.getDefault().register(this);

        detailsButton = (Button)findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(eventbus.this,
                        SecondActivity.class);

                // sending static info to second activity
                //detailsIntent.putExtra(EXTRA_KEYS, "this is extra from main activity...");
                //startActivity(detailsIntent);

                // sending dynamic user text to second activity
                EditText extraText = (EditText)findViewById(R.id.extraText);
                String extraString = extraText.getText().toString();
                detailsIntent.putExtra(EXTRA_KEYS, extraString);
                startActivityForResult(detailsIntent, 0);
            }
        });

        resultButton = (Button)findViewById(R.id.resultFromSecondActivity);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Boolean result = data.getBooleanExtra(SecondActivity.RESULT_FROM_CHILD_ACTIVITY, false);
            resultButton.setText(result.toString());
        }
    }

    @Subscribe
    public void onEvent(CustomMessageEvent event) {
        Log.d("THINGS", "Event fired!");
    }


}
