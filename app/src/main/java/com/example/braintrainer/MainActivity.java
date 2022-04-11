package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, statusTextView, questionTextView, scoreTextView;

    GridLayout gridLayout;

    Button goButton, playAgain;
    Button zz, zo, oz, oo;

    boolean gameIsActive = false;

    int totalQuestions = 0, rightQuestions = 0;
    int q1, q2;

    CountDownTimer countDownTimer;

    Random random = new Random();

    ArrayList<Integer> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridView);

        statusTextView = findViewById(R.id.statusTextView);

        questionTextView = findViewById(R.id.questionTextView);

        scoreTextView = findViewById(R.id.scoreTextView);

        timerTextView = findViewById(R.id.timerTextView);
        //timerTextView.setVisibility(View.VISIBLE);

        goButton = findViewById(R.id.goButton);

        playAgain = findViewById(R.id.playAgain);

        zz = findViewById(R.id.zz);
        zo = findViewById(R.id.zo);
        oz = findViewById(R.id.oz);
        oo = findViewById(R.id.oo);

    }

    public void startGame(View view){

        gameIsActive = true;

        playAgain.setVisibility(View.GONE);

        totalQuestions = 0;
        rightQuestions = 0;

        timerTextView.setVisibility(View.VISIBLE);
        statusTextView.setVisibility(View.VISIBLE);
        statusTextView.setText(null);
        questionTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

        gridLayout.setVisibility(View.VISIBLE);

        goButton.setVisibility(View.GONE);

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                gridLayout.setEnabled(false);
                statusTextView.setText("Your score: " + rightQuestions + "/" + totalQuestions);

                playAgain.setVisibility(View.VISIBLE);
                gameIsActive = false;
            }
        }.start();

        generateQuestion();

    }

    public void generateQuestion(){

        scoreTextView.setText(String.valueOf(rightQuestions) + "/" + String.valueOf(totalQuestions));

        q1 = random.nextInt(30 - 0) + 0;
        q2 = random.nextInt(30 - 0) + 0;
        int sum = q1 + q2;

        questionTextView.setText(String.valueOf(q1) + " + " + String.valueOf(q2));

        int r1 = random.nextInt(sum - 0) + 0;
        int r2 = random.nextInt((sum + 30) - (sum + 1)) + (sum + 1);
        int r3 = random.nextInt(sum - 0) + 0;

        results.clear();

        results.add(r1);
        results.add(r2);
        results.add(r3);
        results.add(sum);

        Collections.shuffle(results);

        zz.setText(String.valueOf(results.get(0)));
        zo.setText(String.valueOf(results.get(1)));
        oz.setText(String.valueOf(results.get(2)));
        oo.setText(String.valueOf(results.get(3)));

    }

    public void selectAnswer(View view){

        if (gameIsActive == true) {

            Button tmp = (Button) view;

            int chosen = Integer.parseInt(tmp.getText().toString());
            if (chosen == (q1 + q2)) {
                //Toast.makeText(getApplicationContext(), "RIGHT!!!", Toast.LENGTH_SHORT).show();
                statusTextView.setText("Correct!");
                totalQuestions++;
                rightQuestions++;

                generateQuestion();
            } else {
                statusTextView.setText("Wrong!");
                totalQuestions++;

                generateQuestion();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Game is not active!", Toast.LENGTH_SHORT).show();
        }

    }

}