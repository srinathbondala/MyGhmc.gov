package com.example.myghmc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;

public class TimerService extends Service {

    private final IBinder binder = new TimerBinder();
    private long startTime = 0;
    private boolean isRunning = false;

    public class TimerBinder extends Binder {
        TimerService getService() {
            return TimerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startTimer() {
        startTime = SystemClock.elapsedRealtime();
        isRunning = true;
    }
    public long getStartTime() {
        return startTime;
    }
    public long stopTimer() {
        isRunning = false;
        return SystemClock.elapsedRealtime() - startTime;
    }

    public boolean isTimerRunning() {
        return isRunning;
    }
}
