package com.world_tech_point.visiting_earnapp.earning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.world_tech_point.visiting_earnapp.R;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Quiz_Questions questions = new Quiz_Questions();
    private String mAnswer;
    private int mQuestionsLength = questions.mChoices.length;
    private Random r;
    int score = 0;
    Button nextBtn;
    TextView questionCounter, question,option1,option2,option3,option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.quizToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Quiz");

        questionCounter = findViewById(R.id.quizCounter);
        question = findViewById(R.id.quizQuestion);
        option1 = findViewById(R.id.quizOption1);
        option2 = findViewById(R.id.quizOption2);
        option3 = findViewById(R.id.quizOption3);
        option4 = findViewById(R.id.quizOption4);
        nextBtn = findViewById(R.id.quizNextBtn);

        r = new Random();
        updateQuestion(r.nextInt(mQuestionsLength));
        questionCounter.setText(score+"/25");

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = score+1;
                updateQuestion(score);
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String op1 = option1.getText().toString();
                String op2 = option2.getText().toString();
                String op3 = option3.getText().toString();
                String op4 = option4.getText().toString();
                if (op1.equals(mAnswer)){
                    option1.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }else {
                    option1.setBackgroundColor(Color.parseColor("#FF1744"));
                }
                if (op2.equals(mAnswer)){
                    option2.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op3.equals(mAnswer)){
                    option3.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op4.equals(mAnswer)){
                    option4.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String op1 = option1.getText().toString();
                String op2 = option2.getText().toString();
                String op3 = option3.getText().toString();
                String op4 = option4.getText().toString();
                if (op2.equals(mAnswer)){
                    option2.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }else {
                    option2.setBackground(getDrawable(R.drawable.red_stoke));
                }
                if (op1.equals(mAnswer)){
                    option1.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op3.equals(mAnswer)){
                    option3.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op4.equals(mAnswer)){
                    option4.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String op1 = option1.getText().toString();
                String op2 = option2.getText().toString();
                String op3 = option3.getText().toString();
                String op4 = option4.getText().toString();
                if (op3.equals(mAnswer)){
                    option3.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }else {
                    option3.setBackground(getDrawable(R.drawable.red_stoke));
                }
                if (op1.equals(mAnswer)){
                    option1.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op2.equals(mAnswer)){
                    option2.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op4.equals(mAnswer)){
                    option4.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String op1 = option1.getText().toString();
                String op2 = option2.getText().toString();
                String op3 = option3.getText().toString();
                String op4 = option4.getText().toString();
                if (op4.equals(mAnswer)){
                    option4.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }else {
                    option4.setBackground(getDrawable(R.drawable.red_stoke));
                }
                if (op2.equals(mAnswer)){
                    option2.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op3.equals(mAnswer)){
                    option3.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);

                }else if (op1.equals(mAnswer)){
                    option1.setBackground(getDrawable(R.drawable.green_stoke));
                    nextBtn.setEnabled(true);
                }

            }
        });
    }

    private void updateQuestion(int num) {
        question.setText(questions.getQuestion(num));
        option1.setText(questions.getChoices1(num));
        option2.setText(questions.getChoices2(num));
        option3.setText(questions.getChoices3(num));
        option4.setText(questions.getChoices4(num));
        mAnswer = questions.getAns(num);
        questionCounter.setText(score+"/25");
        nextBtn.setEnabled(false);
        option1.setBackground(getDrawable(R.drawable.white_stoke));
        option2.setBackground(getDrawable(R.drawable.white_stoke));
        option3.setBackground(getDrawable(R.drawable.white_stoke));
        option4.setBackground(getDrawable(R.drawable.white_stoke));
    }
}