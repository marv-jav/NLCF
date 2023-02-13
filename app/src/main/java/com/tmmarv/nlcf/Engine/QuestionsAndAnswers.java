package com.tmmarv.nlcf.Engine;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsAndAnswers extends AppCompatActivity {

        public String[] MTS_QUESTIONS = {
                "What is the capital of France?",
                "What is the currency of Japan?",
                "What is the highest mountain in the world?",
        };

        public String[][] MTS_ANSWERS = {
                {"Paris", "London", "Berlin"},
                {"Yen", "Dollar", "Euro"},
                {"Mount Everest", "K2", "Kilimanjaro"},
        };

        public int[] MTS_CORRECT_ANSWERS = {
                0,
                0,
                0
        };

}
