package com.maheshchandran.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerView, questionView, marksView;

    Button optionOne, optionTwo, optionThree, optionFour, playAgain;

    float leftOperand, rightOperand, correctAnswer;

    int operator, answerPlacement, questionsAsked, marksScored;


    public void enterGameLoop() {

        CountDownTimer timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                gameOver();

            }
        }.start();

        nextRound();

    }

    public void gameOver() {

        timerView.setVisibility(View.GONE);
        questionView.setVisibility(View.GONE);
        marksView.setVisibility(View.GONE);
        optionOne.setVisibility(View.GONE);
        optionTwo.setVisibility(View.GONE);
        optionThree.setVisibility(View.GONE);
        optionFour.setVisibility(View.GONE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.GONE);

        TextView gameOverTextView = findViewById(R.id.gameOverTextView);
        gameOverTextView.setVisibility(View.VISIBLE);
        gameOverTextView.setText("Game Over!");

        TextView highScore = findViewById(R.id.highScore);
        highScore.setVisibility(View.VISIBLE);

        highScore.setText("You answered " + String.valueOf(marksScored) + " out of " + String.valueOf(questionsAsked) + " questions");

        Button goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        goButton.setText("Play Again");

    }

    public void nextRound() {

        displayQuestion();
        getCorrectAnswer();
        displayOptions();

    }

    public void checkAnswer(View view) {

        if(Integer.parseInt(view.getTag().toString()) == answerPlacement) {
            marksScored++;
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Wrong Answer", Toast.LENGTH_SHORT).show();
        }

        questionsAsked++;

        displayMarks();
        nextRound();

    }

    public void displayMarks() {

        String score = String.valueOf(marksScored) + "/" + String.valueOf(questionsAsked);

        marksView.setText(score);

    }

    public void getRandomNumbers() {

        leftOperand =  new Random().nextInt(20) + 1;
        rightOperand =  new Random().nextInt(20) + 1;
    }

    public char getRandomOperator() {

        char returnOperator = ' ';
        operator = new Random().nextInt(4);

        switch(operator) {

            case 0:
                returnOperator = '+';
                break;

            case 1:
                returnOperator = '-';
                break;

            case 2:
                returnOperator = '*';
                break;

            case 3:
                returnOperator = '/';
                break;
        }

        return returnOperator;
    }

    public void displayQuestion() {

        String question;

        getRandomNumbers();

        question = String.valueOf(leftOperand) + getRandomOperator() + String.valueOf(rightOperand);

        questionView.setText(question);
    }

    public void getCorrectAnswer() {

        switch(operator) {

            case 0:
                correctAnswer =  (leftOperand + rightOperand);

                break;

            case 1:

                correctAnswer =  (leftOperand - rightOperand);

                break;

            case 2:

                correctAnswer =  (leftOperand * rightOperand);

                break;

            case 3:

                correctAnswer =  (leftOperand / rightOperand);

                break;
        }

        Log.i("Info", " the correct answer is:" + String.valueOf(correctAnswer));

    }

    public void displayOptions() {

        int ansOne, ansTwo, ansThree;

        ansOne  = new Random().nextInt(40);
        ansTwo = new Random().nextInt(40);
        ansThree = new Random().nextInt(40);
        answerPlacement = new Random().nextInt(4);

        Log.i("Info", "Now in display options");

        switch(answerPlacement) {

            case 0:
                optionOne.setText(String.valueOf(correctAnswer));
                optionTwo.setText(String.valueOf((float)ansOne));
                optionThree.setText(String.valueOf((float)ansTwo));
                optionFour.setText(String.valueOf((float)ansThree));

                break;

            case 1:

                optionOne.setText(String.valueOf((float)ansOne));
                optionTwo.setText(String.valueOf(correctAnswer));
                optionThree.setText(String.valueOf((float)ansTwo));
                optionFour.setText(String.valueOf((float)ansThree));

                break;

            case 2:

                optionOne.setText(String.valueOf((float)ansOne));
                optionTwo.setText(String.valueOf((float)ansTwo));
                optionThree.setText(String.valueOf(correctAnswer));
                optionFour.setText(String.valueOf((float)ansThree));

                break;

            case 3:

                optionOne.setText(String.valueOf((float)ansOne));
                optionTwo.setText(String.valueOf((float)ansTwo));
                optionThree.setText(String.valueOf((float)ansThree));
                optionFour.setText(String.valueOf(correctAnswer));


        }

    }

    public void setUIElements() {

        timerView.setVisibility(View.VISIBLE);
        questionView.setVisibility(View.VISIBLE);
        marksView.setVisibility(View.VISIBLE);
        optionOne.setVisibility(View.VISIBLE);
        optionTwo.setVisibility(View.VISIBLE);
        optionThree.setVisibility(View.VISIBLE);
        optionFour.setVisibility(View.VISIBLE);

        TextView gameOverTextView = findViewById(R.id.gameOverTextView);
        gameOverTextView.setVisibility(View.GONE);

        TextView highScore = findViewById(R.id.highScore);
        highScore.setVisibility(View.GONE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);

    }

    public void startGame(View view) {

        view.setVisibility(View.GONE);

        setUIElements();

        questionsAsked = 0;
        marksScored = 0;

        displayMarks();

        enterGameLoop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = findViewById(R.id.timerView);
        questionView = findViewById(R.id.questionView);
        marksView = findViewById(R.id.marksViews);
        optionOne = findViewById(R.id.optionOne);
        optionTwo = findViewById(R.id.optionTwo);
        optionThree = findViewById(R.id.optionThree);
        optionFour = findViewById(R.id.optionFour);


    }
}
