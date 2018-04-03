package com.example.akauf.slotmachine;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean stopped;
    private Update update;
    private Handler handler;
    private GridLayout grid;
    private TextView button,points,speed;
    private ImageView pic1,pic2,pic3;
    private SeekBar bar;
    private int p1,p2,p3,i,j,s1,s2,s3,pts1,pts2,pts3;
    private AnimationDrawable animation1, animation2, animation3;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.spinner);
        update = new Update();
        handler = new Handler();
        grid = findViewById(R.id.GridLayout1);
        pic1 = findViewById(R.id.pic1);
        pic2 = findViewById(R.id.pic2);
        pic3 = findViewById(R.id.pic3);
        speed = findViewById(R.id.speed);
        points = findViewById(R.id.points);
        stopped = false;
        bar = findViewById(R.id.seekbar);
        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        speed.setText(progress+"");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }
    public void Start(View v) {
        try {
            String inputspeed = speed.getText().toString();
            i = Integer.parseInt(inputspeed);
            if (i==0){j = 5000;}
            if (i<26&&i>0){j = 1000;}
            if (i>25&&i<76){j = 500;}
            if (i>75){j = 250;}
            if (i==100){j=i;}
            if (stopped == false) {
                button.setText("STOP");
                handler.postDelayed(update, j);
                stopped = true;
            } else {
                button.setText("SPIN");
                handler.removeCallbacks(update);
                GetPoints();
                stopped = false;
            }
        }catch (NumberFormatException e){}
    }

    private class Update implements Runnable {
        @Override
        public void run() {
            try{
                String inputspeed = speed.getText().toString();
                i = Integer.parseInt(inputspeed);
                if (i==0){j = 5000;}
                if (i<26&&i>0){j = 1000;}
                if (i>25&&i<76){j = 500;}
                if (i>75){j = 250;}
                if (i==100){j=i;}
            randomize();
            handler.postDelayed(update, j);
            }catch (NumberFormatException e){}
        }
    }

    public void GetPoints(){
        int score = 0;

        //Three of the same images
        if(p1== p2 && p2 == p3) {
            score+=50;
            Toast.makeText(this, "You Win Big!", Toast.LENGTH_SHORT).show();
            points.setText(score + " ");
        }
        //Two of the same images
        else if (p1 == p2 || p2 == p3 || p1 == p3) {
            score+=20;
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
            points.setText(score + " ");

        } else {
            score = 0;
            Toast.makeText(this, "You lose!", Toast.LENGTH_SHORT).show();
            points.setText(score+ " ");
        }

    }

    public void Rules(View v){
        Intent intent = new Intent(this, rules.class);
        startActivity(intent);

    }

    public void randomize(){
        Random random = new Random();
        p1 = random.nextInt(4)+1;
        p2 = random.nextInt(4)+1;
        p3 = random.nextInt(4)+1;
        switch (p1){
            case 1:
                pic1.setImageResource(R.drawable.cake);
                break;
            case 2:
                pic1.setImageResource(R.drawable.icecream);
                break;
            case 3:
                pic1.setImageResource(R.drawable.cookie);
                break;
            case 4:
                pic1.setImageResource(R.drawable.th);
                break;
        }
        switch (p2){
            case 1:
                pic2.setImageResource(R.drawable.cake);
                break;
            case 2:
                pic2.setImageResource(R.drawable.icecream);
                break;
            case 3:
                pic2.setImageResource(R.drawable.cookie);
                break;
            case 4:
                pic3.setImageResource(R.drawable.th);
                break;
        }
        switch (p3){
            case 1:
                pic3.setImageResource(R.drawable.cake);
                break;
            case 2:
                pic3.setImageResource(R.drawable.icecream);
                break;
            case 3:
                pic3.setImageResource(R.drawable.cookie);
                break;
            case 4:
                pic3.setImageResource(R.drawable.th);
                break;
        }
    }
}
