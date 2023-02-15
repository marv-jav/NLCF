package com.tmmarv.nlcf.Quiz;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tmmarv.nlcf.Engine.QuestionsAndAnswers;
import com.tmmarv.nlcf.R;

public class QuizLoader extends QuestionsAndAnswers {

    int question;
    int countDown;

    public void showQuestion(String[] QUESTIONS, String[][] ANSWERS, int[] CORRECT_ANSWERS, int[] answers, RadioGroup radioGroup, Button button, int currentQuestion, int TIME_INTERVAL, TextView textView) {
        String quest = QUESTIONS[currentQuestion];
        textView.setText(quest);
        radioGroup.removeAllViews();

        question = currentQuestion;
        for (int i = 0; i < ANSWERS[currentQuestion].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(ANSWERS[currentQuestion][i]);
            radioGroup.addView(radioButton);
        }

    }

    public void checkAnswer(int[] CORRECT_ANSWERS, int[] answers, RadioGroup radioGroup, int currentQuestion) {
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

    public void showResults(String[] QUESTIONS, int[] answers) {
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
