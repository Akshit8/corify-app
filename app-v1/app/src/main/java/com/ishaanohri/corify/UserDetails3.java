package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

public class UserDetails3 extends AppCompatActivity {

    private TextView handwashScore, scoreTextView;
    private ImageButton add, subtract, yesNo1, yesNo2, yesNo3;
    private int count = 5;
    private Boolean yes1 = true, yes2 = true, yes3 = false;
    private RoundedHorizontalProgressBar healthProgress;
    public static Integer oldScore = 30, newScore;
    public static float final_score = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(getApplicationContext().getColor(R.color.colorAccent));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details3);
        healthProgress = findViewById(R.id.healthProgress);
        scoreTextView = findViewById(R.id.scoreTextView);

        healthProgress.animateProgress(1000, 0, oldScore); // (animationDuration, oldProgress, newProgress)
        scoreTextView.setText(String.valueOf(oldScore) + "%");

        handwashScore = findViewById(R.id.handwashScore);
        add = findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        yesNo1 = findViewById(R.id.yesNo1);
        yesNo2 = findViewById(R.id.yesNo2);
        yesNo3 = findViewById(R.id.yesNo3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                handwashScore.setText(String.valueOf(count));
                newScore = oldScore;
                newScore += 1 * 3;
                healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
                scoreTextView.setText(String.valueOf(newScore) + "%");
                oldScore = newScore;
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count != 1)
                {
                    count --;
                    handwashScore.setText(String.valueOf(count));
                    newScore = oldScore;
                    newScore += -1 * 3;
                    healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(newScore) + "%");
                    oldScore = newScore;
                }
            }
        });

        yesNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScore = oldScore;
                if(yes1)
                {
                    yes1 = false;
                    yesNo1.setImageResource(R.drawable.no);
                    newScore += 7;
                }
                else
                {
                    yes1 = true;
                    yesNo1.setImageResource(R.drawable.yes);
                    newScore -= 7;
                }
                healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
                scoreTextView.setText(String.valueOf(newScore) + "%");
                oldScore = newScore;
            }
        });

        yesNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScore = oldScore;
                if(yes2)
                {
                    yes2 = false;
                    yesNo2.setImageResource(R.drawable.no);
                    newScore -= 5;
                }
                else
                {
                    yes2 = true;
                    yesNo2.setImageResource(R.drawable.yes);
                    newScore += 5;
                }
                healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
                scoreTextView.setText(String.valueOf(newScore) + "%");
                oldScore = newScore;
            }
        });

        yesNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScore = oldScore;
                if(yes3)
                {
                    yes3 = false;
                    yesNo3.setImageResource(R.drawable.no);
                    newScore += 12;
                }
                else
                {
                    yes3 = true;
                    yesNo3.setImageResource(R.drawable.yes);
                    newScore -= 12;
                }
                healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
                scoreTextView.setText(String.valueOf(newScore) + "%");
                oldScore = newScore;
            }
        });

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final_score = newScore;
                startActivity(new Intent(UserDetails3.this,HealthScore.class));
            }
        });

    }

    public void updateScore()
    {
        int diff = count - 7;

        newScore = oldScore;
        newScore += diff * 3;
        healthProgress.animateProgress(1000, oldScore, newScore); // (animationDuration, oldProgress, newProgress)
        scoreTextView.setText(String.valueOf(newScore) + "%");
        oldScore = newScore;
    }
}
