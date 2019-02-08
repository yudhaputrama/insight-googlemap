package org.d3ifcool.googlemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

public class BuildingActivity extends AppCompatActivity {
    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

        AppCompatButton ruteButton = findViewById(R.id.rute_button);
        ruteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuildingActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });



    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("text") && getIntent().hasExtra("text")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String text = getIntent().getStringExtra("text");
        }
    }
}
