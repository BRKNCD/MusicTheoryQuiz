package com.example.android.musictheoryquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DECLARE VIEWS
    CheckBox answer1;
    CheckBox answer2;
    CheckBox answer3;
    CheckBox answer4;

    CheckBox answer21;
    CheckBox answer22;
    CheckBox answer23;
    CheckBox answer24;

    CheckBox answer31;
    CheckBox answer32;
    CheckBox answer33;
    CheckBox answer34;

    CheckBox answer41;
    CheckBox answer42;
    CheckBox answer43;
    CheckBox answer44;

    CheckBox answer51;
    CheckBox answer52;
    CheckBox answer53;
    CheckBox answer54;

    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioGroup radioGroup;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    EditText freeAnswer;

    ImageButton submit;

    // THIS WILL STORE THE POINT
    int pointsCounter = 0;

    // A 2D ARRAY FOR STORING THE CORRECT ANSWERS
    String[][] correctAnswer = {{"G maj", "E min"},
            {"D maj", "B min"},
            {"A maj", "F# min"},
            {"E maj", "C# min"},
            {"C maj", "A min"}};

    // A 2D ARRAY CONTAINING ANSWERS FOR EACH QUESTION
    String[][] answers = {{"F maj", correctAnswer[0][0], "G# min", correctAnswer[0][1]},
            {correctAnswer[1][0], correctAnswer[1][1], "C maj", "A maj"},
            {correctAnswer[2][1], "G maj", "D# min", correctAnswer[2][0]},
            {"C# maj", "F min", correctAnswer[3][1], correctAnswer[3][0]},
            {"B maj", correctAnswer[4][0], correctAnswer[4][1], "C min"}};

    // A 2D ARRAY WITH ALL THE CHECKBOX VIEWS
    CheckBox[][] checkBoxArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeAll();
        randomAnswer();
        displayAnswers();

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                submitAnswer();
                checkRadioButton1();
                checkRadioButton2();
                checkRadioButton3();
                checkEditText();

                // THIS WILL PAS INT POINTSCOUNTER TO OTHER ACTIVYTY (SCORE_ACTIVITY)
                Intent i = new Intent(MainActivity.this, scoreActivity.class);
                i.putExtra("pointsCounter", pointsCounter);
                startActivity(i);
            }
        });
    }

    // INITIALIZE ALL VIEWS
    public void initializeAll(){

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        answer21 = findViewById(R.id.answer21);
        answer22 = findViewById(R.id.answer22);
        answer23 = findViewById(R.id.answer23);
        answer24 = findViewById(R.id.answer24);

        answer31 = findViewById(R.id.answer31);
        answer32 = findViewById(R.id.answer32);
        answer33 = findViewById(R.id.answer33);
        answer34 = findViewById(R.id.answer34);

        answer41 = findViewById(R.id.answer41);
        answer42 = findViewById(R.id.answer42);
        answer43 = findViewById(R.id.answer43);
        answer44 = findViewById(R.id.answer44);

        answer51 = findViewById(R.id.answer51);
        answer52 = findViewById(R.id.answer52);
        answer53 = findViewById(R.id.answer53);
        answer54 = findViewById(R.id.answer54);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup2 = findViewById(R.id.radiogroup2);
        radioGroup3 = findViewById(R.id.radiogroup3);
        freeAnswer = findViewById(R.id.textAnswer);

        checkBoxArray = new CheckBox[][]{
                {answer1, answer2, answer3, answer4},
                {answer21, answer22, answer23, answer24},
                {answer31, answer32, answer33, answer34},
                {answer41, answer42, answer43, answer44},
                {answer51, answer52, answer53, answer54}};

        submit = findViewById(R.id.submit);
    }

    // MOTHOD FOR RANDOMIZE ANSWER ORDER
    private void randomAnswer() {
        List<String> answerRandTemp;
        for (int i = 0; i < answers.length; i++) {
            answerRandTemp = Arrays.asList(answers[i]);
            Collections.shuffle(answerRandTemp);
            answerRandTemp.toArray(answers[i]);
        }
    }

    //ASSIGN TEXT TO EACH CheckBox
    public void displayAnswers() {
        for (int questionIndex = 0; questionIndex < answers.length; questionIndex++) {
            for (int i = 0; i < answers[0].length; i++)
                checkBoxArray[questionIndex][i].setText(answers[questionIndex][i]);
        }
    }

    //ACTION PERFORMED WHEN BUTTON submit IS CLICKED
    public void submitAnswer() {
        for (int questionIndex = 0; questionIndex < answers.length; questionIndex++) {
            for (int i = 0; i < checkBoxArray[0].length; i++)
                controlCheck(checkBoxArray[questionIndex][i], questionIndex);
        }
    }

    // CONTROL WHICH CHECKBOXES ARE CHECKED AND IF THEY ARE GO controlAnswers
    public void controlCheck(CheckBox answerToCheck, int questionNumber) {
        if (answerToCheck.isChecked()) {
            controlAnswers(answerToCheck, questionNumber);
        }
    }

    //AFTER CONTROL IF IT'S CHECKED, COMPARE TEXT OF SELECTED CheckBoxes AND COMPARE IT WITH correctAnswer ARRAY
    public void controlAnswers(CheckBox checkAnswer, int questionNumber) {

        String answer = checkAnswer.getText().toString();

        if (answer.equals(correctAnswer[questionNumber][0]) || answer.equals(correctAnswer[questionNumber][1])) {
            pointsCounter += 10;
        } else {
            pointsCounter -= 5;
        }
    }

    // METHOD FOR CHECK RADIOBUTTON1
    public void checkRadioButton1() {
        // Is the button now checked?
        // Check which radio button was clicked
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio1:
                if (radio1.isChecked())
                    pointsCounter -= 5;
                break;

            case R.id.radio2:
                if (radio2.isChecked())
                    pointsCounter += 10;
                break;

            case R.id.radio3:
                if (radio3.isChecked())
                    pointsCounter -= 5;
                break;
        }
    }
    // METHOD FOR CHECK RADIOBUTTON2
    public void checkRadioButton2() {
        // Is the button now checked?
        // Check which radio button was clicked
        int id = radioGroup2.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio1:
                if (radio1.isChecked())
                    pointsCounter -= 5;
                break;

            case R.id.radio2:
                if (radio2.isChecked())
                    pointsCounter -= 5;
                break;

            case R.id.radio3:
                if (radio3.isChecked())
                    pointsCounter += 10;
                break;
        }
    }

    // METHOD FOR CHECK RADIOBUTTON3
    public void checkRadioButton3() {
        // Is the button now checked?
        // Check which radio button was clicked
        int id = radioGroup3.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio1:
                if (radio1.isChecked())
                    pointsCounter -= 5;
                break;

            case R.id.radio2:
                if (radio2.isChecked())
                    pointsCounter += 10;
                break;

            case R.id.radio3:
                if (radio3.isChecked())
                    pointsCounter -= 5;
                break;
        }
    }

    // METHOD FOR CHECK EDITTEXT
    public void checkEditText() {

        String editTextAnswer = freeAnswer.getText().toString();
        String rightAnswer = getString(R.string.rightAnswer);
        if (editTextAnswer.equals(rightAnswer)) {

            pointsCounter += 10;

        } else {
            pointsCounter -= 5;
        }
    }

    // METHOD FOR RESETTING CHECKBOXES STATE
    public void resetCheckBoxes() {
        for (int questionIndex = 0; questionIndex < answers.length; questionIndex++) {
            for (int i = 0; i < answers[0].length; i++)
                checkBoxArray[questionIndex][i].setChecked(false);
        }
    }
}
