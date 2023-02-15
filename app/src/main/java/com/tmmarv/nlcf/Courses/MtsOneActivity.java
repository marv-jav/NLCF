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

import com.tmmarv.nlcf.Engine.QuestionsAndAnswers;
import com.tmmarv.nlcf.Quiz.QuizLoader;
import com.tmmarv.nlcf.R;

public class MtsOneActivity extends QuizLoader {

    private static final int TIME_INTERVAL = 10000; // 10 seconds
    private CountDownTimer timer;
    private QuestionsAndAnswers mQuestionsAndAnswers;

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

        mQuestionsAndAnswers = new QuestionsAndAnswers();

        textQuestion = findViewById(R.id.text_question);
        radioGroup = findViewById(R.id.radio_group);
        buttonNext = findViewById(R.id.button_next);
        answers = new int[mQuestionsAndAnswers.MTS_QUESTIONS.length];

        timer = new CountDownTimer(TIME_INTERVAL, 1000) {
            public void onTick(long millisUntilFinished) {
                String text = millisUntilFinished / 1000 + " seconds remaining";
                buttonNext.setText(text);
            }

            public void onFinish() {
                checkAnswer(MTS_CORRECT_ANSWERS, answers, radioGroup, currentQuestion);
                currentQuestion++;
                if (currentQuestion < MTS_QUESTIONS.length) {
                    showQuestion(MTS_QUESTIONS, MTS_ANSWERS, MTS_CORRECT_ANSWERS, answers, radioGroup, buttonNext, currentQuestion, TIME_INTERVAL, textQuestion);
                } else {
                    showResults(MTS_QUESTIONS, answers);
                }
            }
        };
        timer.start();

        showQuestion(mQuestionsAndAnswers.MTS_QUESTIONS, mQuestionsAndAnswers.MTS_ANSWERS, MTS_CORRECT_ANSWERS, answers, radioGroup, buttonNext, currentQuestion, TIME_INTERVAL, textQuestion);

        buttonNext.setOnClickListener(v -> {
            checkAnswer(mQuestionsAndAnswers.MTS_CORRECT_ANSWERS, answers, radioGroup, currentQuestion);
            timer.cancel();
            currentQuestion++;
            if (currentQuestion < mQuestionsAndAnswers.MTS_QUESTIONS.length) {
                showQuestion(mQuestionsAndAnswers.MTS_QUESTIONS, mQuestionsAndAnswers.MTS_ANSWERS, MTS_CORRECT_ANSWERS, answers, radioGroup, buttonNext, currentQuestion, TIME_INTERVAL, textQuestion);
                timer.start();
            } else {
                showResults(mQuestionsAndAnswers.MTS_QUESTIONS, answers);
            }
        });
    }
}