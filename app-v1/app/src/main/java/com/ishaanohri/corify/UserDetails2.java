package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

public class UserDetails2 extends AppCompatActivity {

    private Chip cold, fever, bronchitis, breath, headache, aids, pneumonia, cough, heart, chronic;
    private RoundedHorizontalProgressBar healthProgress2;
    private Button nextButton;
    private TextView scoreTextView;
    public static float total, score;
    float value, oldValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details2);

        cold = findViewById(R.id.chip1);
        fever = findViewById(R.id.chip2);
        bronchitis = findViewById(R.id.chip3);
        breath = findViewById(R.id.chip4);
        headache = findViewById(R.id.chip5);
        aids = findViewById(R.id.chip6);
        pneumonia = findViewById(R.id.chip7);
        cough = findViewById(R.id.chip8);
        heart = findViewById(R.id.chip9);
        chronic = findViewById(R.id.chip10);
        healthProgress2 = findViewById(R.id.healthProgress2);
        nextButton = findViewById(R.id.nextButton2);
        scoreTextView = findViewById(R.id.scoreTextView2);

        total = 47;
        score = 47;

        value = score/total * 100;

        healthProgress2.animateProgress(1000, 0, Math.round(value)); // (animationDuration, oldProgress, newProgress)
        scoreTextView.setText(String.valueOf(Math.round(value)) + "%");

        cold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 2;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 2;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        fever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 3;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 3;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        bronchitis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 6;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 6;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        breath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        headache.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        aids.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    score = score - 8;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 8;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        cough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    score = score - 2;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 2;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        pneumonia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    score = score - 7;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 7;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        heart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    score = score - 4;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 4;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });
        chronic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    score = score - 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }
                else
                {
                    score = score + 5;
                    oldValue = value;
                    value = score/total * 100;
                    healthProgress2.animateProgress(1000, Math.round(oldValue), Math.round(value)); // (animationDuration, oldProgress, newProgress)
                    scoreTextView.setText(String.valueOf(Math.round(value)) + "%");
                }

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails2.this, UserDetails3.class);
                startActivity(intent);
            }
        });

    }
}
