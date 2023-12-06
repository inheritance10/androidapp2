package com.example.android_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RandomActivity extends AppCompatActivity {

    EditText barCount;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        Button generateButton = findViewById(R.id.barCountButton);
        barCount = findViewById(R.id.editBarCount);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateProgressBar();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void generateProgressBar(){
        int barCount = Integer.parseInt(this.barCount.getText().toString());

        LinearLayout container = findViewById(R.id.container);
        for(int i=1; i<= barCount; i++){
            ProgressBar progressBar = new ProgressBar(RandomActivity.this,null, android.R.attr.progressBarStyleHorizontal);

            progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0,26,0,0);
            progressBar.setLayoutParams(params);

            int minValue = (int) (Math.random() * 100);
            int maxValue = (int) (Math.random() * 100);

            int range = Math.abs(maxValue - minValue);
            minValue = Math.min(minValue, maxValue);
            maxValue = maxValue + range;

            int randomValue = (int) (Math.random() * range) + minValue;
            progressBar.setMax(randomValue);

            progressBar.setProgress(randomValue - minValue);

            TextView values = new TextView(RandomActivity.this);
            values.setLayoutParams(params);
            values.setText(String.format("Min: %d, Max: %d,Value: %d, Percantage: %d%%",minValue,maxValue,randomValue,
                    (int) ((randomValue-minValue) / (float) range * 100)));

            container.addView(values);
            container.addView(progressBar);
        }
    }
}
