package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean counterIsActive=false;
    Button controllerButton;
    CountDownTimer EggCountdownTime;


    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/ 60;
        int seconds = secondsLeft - minutes * 60;
        String secondsString = Integer.toString(seconds);

       if(Integer.parseInt(secondsString)<=9){
            secondsString="0"+secondsString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);}


        public void resetTimer(){
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            EggCountdownTime.cancel();
            controllerButton.setText("GO!");
            timerSeekBar.setEnabled(true);
            counterIsActive=false;
        }



    public void controlTimer(View view){
        if (counterIsActive==false){


            counterIsActive=true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");
             EggCountdownTime = new CountDownTimer(timerSeekBar.getProgress()*1000,1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mplayer.start();
                    resetTimer();
                }
            }.start();




        } else{
                resetTimer();

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton =(Button) findViewById(R.id.controllerButton);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
