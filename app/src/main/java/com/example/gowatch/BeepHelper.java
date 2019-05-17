package com.example.gowatch;

import android.media.AudioManager;
import android.media.ToneGenerator;

class BeepHelper
{

    public static void beep(int duration)
    {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, duration);
    }
}