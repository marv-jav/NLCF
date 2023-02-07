package com.tmmarv.nlcf.Courses;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tmmarv.nlcf.R;

public class MtsOneActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 10000; // 10 seconds
    private CountDownTimer timer;

    private static final String[] QUESTIONS = {"What is the capital of France?", "What is the currency of Japan?", "What is the highest mountain in the world?",};

    private static final String[][] ANSWERS = {{"Paris", "London", "Berlin"}, {"Yen", "Dollar", "Euro"}, {"Mount Everest", "K2", "Kilimanjaro"},};

    private static final int[] CORRECT_ANSWERS = {0, 0, 0};

    private int currentQuestion = 0;

    private TextView textQuestion;
    private RadioGroup radioGroup;
    private Button buttonNext;
    private int[] answers;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mts_one);

        textQuestion = findViewById(R.id.text_question);
        radioGroup = findViewById(R.id.radio_group);
        buttonNext = findViewById(R.id.button_next);
        answers = new int[QUESTIONS.length];

        showQuestion();

        buttonNext.setOnClickListener(v -> {
            checkAnswer();
            currentQuestion++;
            if (currentQuestion < QUESTIONS.length) {
                showQuestion();
            } else {
                showResults();
            }
        });
    }

    private void showQuestion() {
        String question = QUESTIONS[currentQuestion];
        textQuestion.setText(question);
        radioGroup.removeAllViews();

        for (int i = 0; i < ANSWERS[currentQuestion].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(ANSWERS[currentQuestion][i]);
            radioGroup.addView(radioButton);
        }

        timer = new CountDownTimer(TIME_INTERVAL, 1000) {
            public void onTick(long millisUntilFinished) {
                String text = millisUntilFinished / 1000 + " seconds remaining";
                buttonNext.setText(text);
            }

            public void onFinish() {
                checkAnswer();
                currentQuestion++;
                if (currentQuestion < QUESTIONS.length) {
                    showQuestion();
                } else {
                    showResults();
                }
            }
        };
        timer.start();
    }

    private void checkAnswer() {
        timer.cancel();
        int selected = -1;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                selected = i;
                break;
            }
        }
        answers[currentQuestion] = (selected == CORRECT_ANSWERS[currentQuestion]) ? 1 : 0;
    }

    private void showResults() {
        int correctAnswers = 0;
        for (int answer : answers) {
            if (answer == 1) {
                correctAnswers++;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Results");
        builder.setMessage("Your score: " + correctAnswers + " / " + QUESTIONS.length);
        builder.setPositiveButton("Ok", (dialog, which) -> finish());
        builder.show();
    }
}