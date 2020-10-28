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

public class Spin2Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_spin2);

        Toolbar toolbar = findViewById(R.id.spin2ToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r = new Random();
        wheelImage = findViewById(R.id.wheelImage2);
        tapBtn = findViewById(R.id.wheelTapBtn2);

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
                                player = MediaPlayer.create(Spin2Activity.this,R.raw.sound);
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

        if (degrees >= (FACTOR * 0) && degrees <= (FACTOR * 2)){

            score = score+1;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *2) && degrees <= (FACTOR * 4)){

            score = score+2;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *4) && degrees <= (FACTOR * 6)){

            score = score+3;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *6) && degrees <= (FACTOR * 8)){

            score = score+4;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *8) && degrees <= (FACTOR * 10)){

            score = score+5;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;


        } if (degrees >= (FACTOR *10) && degrees <= (FACTOR * 12)){

            score = score+6;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *12) && degrees <= (FACTOR * 14)){

            score = score+7;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *14) && degrees <= (FACTOR * 16)){

            score = score+8;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *16) && degrees <= (FACTOR * 18)){

            score = score+9;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *18) && degrees <= (FACTOR * 20)){

            score = score+10;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        } if (degrees >= (FACTOR *20) && degrees <= (FACTOR * 22)){

            score = score+11;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        }

        if ((degrees >= (FACTOR * 22 ) && degrees <= (FACTOR * 24)))
        {

            score = score+12;
            Toast.makeText(this, ""+score, Toast.LENGTH_SHORT).show();
            score= 0;

        }

        return text;

    }
}