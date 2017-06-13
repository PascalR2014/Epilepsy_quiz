package com.example.android.epilepsy_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // Submit score method
        Bundle b = getIntent().getExtras();
        String score = b.getString("score");

        TextView correctAnswersTextView = (TextView) findViewById(R.id.answers_correct);
        TextView totalAnswersTextView = (TextView) findViewById(R.id.answers_total);

        correctAnswersTextView.setText(score);
        totalAnswersTextView.setText("5");

        Button playAgainButton = (Button) findViewById(R.id.button_play_again);

        switch (Integer.parseInt(score)) {
            case 3:
                Toast.makeText(SummaryActivity.this, getResources().getString(R.string.not_so_bad) + score, Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(SummaryActivity.this, getResources().getString(R.string.nice_score) + score, Toast.LENGTH_LONG).show();
                break;
            case 5:
                Toast.makeText(SummaryActivity.this, getResources().getString(R.string.great_score) + score, Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(SummaryActivity.this, getResources().getString(R.string.could_be_better), Toast.LENGTH_LONG).show();
                break;
        }

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}