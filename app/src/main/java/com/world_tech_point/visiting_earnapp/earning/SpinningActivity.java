package com.world_tech_point.visiting_earnapp.earning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.world_tech_point.visiting_earnapp.R;

import java.util.Random;

import static kotlin.text.Typography.degree;

public class SpinningActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    ImageView wheelImage;
    TextView tapBtn;
    private Random r;
    private int degree = 0, degree_old = 0;
    private static final float FACTOR = 15f;
    private MediaPlayer player;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinning);

        Toolbar toolbar = findViewById(R.id.spin1ToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r = new Random();
        wheelImage = findViewById(R.id.wheelImage);
        tapBtn = findViewById(R.id.wheelTapBtn);

        tapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    degree_old = degree % 360;
                    degree = r.nextInt(3600) + 720;

                    RotateAnimation animationRotate = new RotateAnimation(degree_old,degree,RotateAnimation.RELATIVE_TO_SELF,
                            0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    animationRotate.setDuration(3600);
                    animationRotate.setFillAfter(true);
                    animationRotate.setInterpolator(new DecelerateInterpolator());
                    animationRotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                            if (player == null){
                                player = MediaPlayer.create(SpinningActivity.this,R.raw.sound);
                                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        stopPlayer();
                                    }
                                });
                            }
                            player.start();


                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            stopPlayer();
                            currentNumber(360 - (degree%360));

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    wheelImage.startAnimation(animationRotate);

                }


            }
        });


    }

    private void stopPlayer() {
        if (player != null){
            player.release();
            player=null;
        }
    }


    private String currentNumber (int degrees){
        String text = "";

        if (degrees >= (FACTOR *1) && degrees < (FACTOR * 3)){

            score = score+1;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *3) && degrees < (FACTOR * 5)){

            score = score+2;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *5) && degrees < (FACTOR * 7)){

            score = score+3;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *7) && degrees < (FACTOR * 9)){

            score = score+4;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *9) && degrees < (FACTOR * 11)){

            score = score+5;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;


        } if (degrees >= (FACTOR *11) && degrees < (FACTOR * 13)){

            score = score+6;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *13) && degrees < (FACTOR * 15)){

            score = score+7;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *15) && degrees < (FACTOR * 17)){

            score = score+8;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *17) && degrees < (FACTOR * 19)){

            score = score+9;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *19) && degrees < (FACTOR * 21)){

            score = score+10;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *21) && degrees < (FACTOR * 23)){

            score = score+11;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        }

        if ((degrees >= (FACTOR * 23 ) && degrees < 360) || (degrees >= 0 && degrees < (FACTOR * 1)))
        {

            score = score+12;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        }

        return text;

    }
}