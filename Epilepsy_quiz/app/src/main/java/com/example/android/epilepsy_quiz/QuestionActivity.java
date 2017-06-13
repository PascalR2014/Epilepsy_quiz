package com.example.android.epilepsy_quiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    private int questionNum = 0;
    private RadioGroup radioGroup;
    private Question question;
    private String[] answers;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // This activity will display multiple questions in the same reusable containers
        this.setQuestion(questionNum);

        Button nextQuestion = (Button) findViewById(R.id.button_next);

        // Get score from previous activity
        Bundle b = getIntent().getExtras();
        score = Integer.parseInt(b.getString("score"));

        // When user submits an answer
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get number of checked RadioButton within RadioGroup
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int checkedAnswer = radioGroup.indexOfChild(radioButton) + 1;

                // Check if user checked the correct answer
                if (question.getCorrectAnswer() == checkedAnswer) {
                    score++;
                    Toast.makeText(QuestionActivity.this, getResources().getString(R.string.correct_your_score_is) + score, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(QuestionActivity.this, getResources().getString(R.string.sorry_wrong_answer), Toast.LENGTH_LONG).show();
                }

                questionNum++;

                // If all questions were displayed, go to summary activity,
                // if not, display next question and clear all checkboxes
                if (questionNum == answers.length) {
                    Intent intent = new Intent(QuestionActivity.this, SummaryActivity.class);
                    intent.putExtra("score", Integer.toString(score));
                    startActivity(intent);
                } else {
                    setQuestion(questionNum);
                    radioGroup.clearCheck();
                }
            }
        });
    }

    private void setQuestion(int n) {

        // Get all questions array from strings resource
        Resources res = getResources();
        String[] questions = res.getStringArray(R.array.questions);

        // Get possible answers array for current question from strings resource
        int resIdAnswers =
                res.getIdentifier("answers_for_question_" + (n + 1), "array", this.getPackageName());
        answers = res.getStringArray(resIdAnswers);

        // Get correct answers from strings resource
        String[] correctAnswers = res.getStringArray(R.array.correct_answers_radiobuttons);

        question = new Question(questions[n], answers, correctAnswers[n]);

        // Set text of question
        TextView questionTextView = (TextView) findViewById(R.id.text_question);
        questionTextView.setText(questions[n]);

        // Set text of answers radio buttons
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(answers[i]);
        }
    }
}
