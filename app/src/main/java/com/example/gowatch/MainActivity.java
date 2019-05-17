package com.example.gowatch;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    CountDownTimer timer;
    boolean isTimerSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button stopButton = findViewById(R.id.btnStop);
        stopButton.setEnabled(false);
        final TextView txtTimeOut = findViewById(R.id.txtTimeOut);
        txtTimeOut.setVisibility(View.INVISIBLE);

    }

    private void setTimer(){

        EditText txtDurationIn = findViewById(R.id.txtDurationIn);
        final TextView txtTimeOut = findViewById(R.id.txtTimeOut);
        String ed_text = txtDurationIn.getText().toString().trim();
        if (!TextUtils.isEmpty(ed_text)) {
            int duration = Integer.valueOf(ed_text);

            timer = new CountDownTimer(duration * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long minutes = (millisUntilFinished / 1000) / 60;
                    long seconds = (millisUntilFinished / 1000) % 60;
                    txtTimeOut.setText(minutes+":"+seconds);
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
        Button startButton = findViewById(R.id.btnStart);
        Button stopButton = findViewById(R.id.btnStop);
        final TextView txtTimeOut = findViewById(R.id.txtTimeOut);
        txtTimeOut.setVisibility(View.VISIBLE);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        setTimer();
    }

    public void kickoffTimer(){
        if(isTimerSet) timer.start();
    }

    public void stopTimer(final View view) {

        Button startButton = findViewById(R.id.btnStart);
        Button stopButton = findViewById(R.id.btnStop);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        final TextView txtTimeOut = findViewById(R.id.txtTimeOut);
        txtTimeOut.setVisibility(View.INVISIBLE);

        if(isTimerSet) {
            timer.cancel();
            isTimerSet = false;
        }
    }

}
