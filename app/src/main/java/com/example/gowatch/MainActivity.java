package com.example.gowatch;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    CountDownTimer timer;
    boolean isTimerSet = false;
    Button startButton;
    Button stopButton;
    TextView txtTimeOut;
    EditText txtDurationIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimeOut = findViewById(R.id.txtTimeOut);
        txtTimeOut.setVisibility(View.INVISIBLE);
        startButton = findViewById(R.id.btnStart);
        stopButton = findViewById(R.id.btnStop);
        txtDurationIn = findViewById(R.id.txtDurationIn);
        txtTimeOut = findViewById(R.id.txtTimeOut);

        stopButton.setVisibility(View.INVISIBLE);
    }

    private void setTimer(String durationIn){

        if (!TextUtils.isEmpty(durationIn)) {
            int duration = Integer.valueOf(durationIn);
            timer = new CountDownTimer(duration * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long minutes = (millisUntilFinished / 1000) / 60;
                    long seconds = (millisUntilFinished / 1000) % 60;
                    String twoDigitSeconds = String.format("%02d", seconds);
                    String timeRemaining = minutes+":"+twoDigitSeconds;
                    txtTimeOut.setText(timeRemaining);
                }

                public void onFinish() {
                    BeepHelper.beep(500);
                    kickoffTimer();
                }
            };
            isTimerSet = true;
            kickoffTimer();
        }
    }

    public void startTimer(final View view) {

        String durationIn = txtDurationIn.getText().toString();
        if(!durationIn.equals("")) {
            txtTimeOut.setVisibility(View.VISIBLE);
            toggleButtons(startButton, stopButton);
            setTimer(durationIn);
        }
    }

    private void kickoffTimer(){
        if(isTimerSet) timer.start();
    }

    public void stopTimer(final View view) {

        toggleButtons(startButton, stopButton);
        txtTimeOut.setVisibility(View.INVISIBLE);

        if(isTimerSet) {
            timer.cancel();
            isTimerSet = false;
        }
    }

    private void toggleButtons(Button start, Button stop){

        if (start.getVisibility() == View.VISIBLE){
            start.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.VISIBLE);
        }
        else{
            start.setVisibility(View.VISIBLE);
            stop.setVisibility(View.INVISIBLE);
        }
    }
}
