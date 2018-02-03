package com.example.android.musictheoryquiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class scoreActivity extends AppCompatActivity{

    // DECLARE VIEWS
    TextView score;
    Button newGame;
    SmileRating smileRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        initializeAll();
        smileRating.setIndicator(true);
        int prevPoint = 0;

        // THIS WILL GET INTENT AND THE INT POINTSCOUNTER PASSED FROM PREVIOUS ACTIVITY
        Intent mIntent = getIntent();
        int pointsCounter = mIntent.getIntExtra("pointsCounter", 0);

        animateTextView(prevPoint, pointsCounter, score);
        score.setText("" + pointsCounter);
        animateSmile(pointsCounter);

        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(scoreActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    // INITIALIZE ALL VIEWS
    public void initializeAll(){
        score = findViewById(R.id.score);
        newGame = (Button) findViewById(R.id.new_game);
        smileRating = (SmileRating) findViewById(R.id.smile_rating);
    }

    //INCREMENTAL POINTS ANIMATION
    public void animateTextView(int initialValue, int finalValue, final TextView textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }

    public void animateSmile(int point) {
        if (point <= 0) {
            smileRating.setSelectedSmile(BaseRating.TERRIBLE);
        } else {
            if (point > 0 && point <= 20) {
                smileRating.setSelectedSmile(BaseRating.BAD);
            } else {
                if (point > 20 && point <= 60) {
                    smileRating.setSelectedSmile(BaseRating.OKAY);
                } else {
                    if (point > 60 && point <= 80) {
                        smileRating.setSelectedSmile(BaseRating.GOOD);
                    } else {
                        if (point > 80) {
                            smileRating.setSelectedSmile(BaseRating.GREAT);
                        }
                    }
                }
            }
        }
    }
}
