package com.example.android.epilepsy_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox question1Checkbox1;
    private CheckBox question1Checkbox2;
    private CheckBox question1Checkbox3;
    private EditText question2TextEdit;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question1Checkbox1 = (CheckBox) findViewById(R.id.first);
        question1Checkbox2 = (CheckBox) findViewById(R.id.second);
        question1Checkbox3 = (CheckBox) findViewById(R.id.third);
        question2TextEdit = (EditText) findViewById(R.id.ediText);

        Button startQuizButton = (Button) findViewById(R.id.start_quiz);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checkboxes
                boolean question1Correct =
                        question1Checkbox1.isChecked() && !question1Checkbox2.isChecked() && question1Checkbox3
                                .isChecked();
                if (question1Correct) score++;
                //EditText
                boolean question2Correct = question2TextEdit.getText().toString().toLowerCase()
                        .equals(getResources().getString(R.string.answer_question_2));
                if (question2Correct) score++;

                switch (score) {
                    case 0:
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.both_answers_wrong), Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.one_answer_wrong) + score, Toast.LENGTH_LONG)
                                .show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.both_answers_correct) + score,
                                Toast.LENGTH_LONG).show();
                        break;
                }

                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("score", Integer.toString(score));

                startActivity(intent);
            }
        });

    }
}

