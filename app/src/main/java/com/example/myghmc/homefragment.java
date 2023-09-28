package com.example.myghmc;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.concurrent.TimeUnit;

public class homefragment extends Fragment implements View.OnClickListener{
    private ConstraintLayout card1,card2,card3;
    private Button check_in;
    private TextView timer,time_show;
    private CalendarView calendarView;
    private TimerService timerService;
    private boolean isBound = false;
    private Handler updateHandler;
    private LinearLayout hiddenLayout;
    View k;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container,false);
        k=view;
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        check_in = view.findViewById(R.id.chk_in);
        timer=view.findViewById(R.id.timer);
        time_show=view.findViewById(R.id.time_show);
        calendarView = view.findViewById(R.id.select_calender);
        hiddenLayout = view.findViewById(R.id.dropdown_time);
        hiddenLayout.setVisibility(View.GONE);
        try {
            updateHandler = new Handler();
            card1.setOnClickListener(this);
            card2.setOnClickListener(this);
            card3.setOnClickListener(this);
            check_in.setOnClickListener(this);
            Intent intent = new Intent(view.getContext(), TimerService.class);
            view.getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }catch (Exception e)
        {}
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(view.getContext(),house_Details.class);
        if(view==card1) {
            Toast.makeText(view.getContext(), "Total houses 200", Toast.LENGTH_SHORT).show();
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.swipe_in_right, R.anim.swipe_out_left);
        } else if(view==card2) {
            Toast.makeText(view.getContext(), "houses scanned 100", Toast.LENGTH_SHORT).show();
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.swipe_in_right, R.anim.swipe_out_left);
        } else if (view==card3) {
            Toast.makeText(view.getContext(), "house unscanned", Toast.LENGTH_SHORT).show();
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.swipe_in_right, R.anim.swipe_out_left);
        }
        else if(view==check_in)
        {
            try{
                if (hiddenLayout.getVisibility() == View.GONE) {
                    Animation slideDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_down);
                    hiddenLayout.startAnimation(slideDown);
                    hiddenLayout.setVisibility(View.VISIBLE);
                }
                else {
                    hiddenLayout.setVisibility(View.GONE);
                }
                if (timerService.isTimerRunning()) {
                    timerService.stopTimer();
                    updateHandler.removeCallbacks(updateTimeRunnable);
                    check_in.setText("Check In");
                    time_show.setText("Time finished:"+timer.getText());
                } else {
                    timerService.startTimer();
                    updateHandler.post(updateTimeRunnable);
                    check_in.setText("Check Out");
                }
            }catch (Exception exception)
            {
                Toast.makeText(timerService, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if (timerService.isTimerRunning()) {
                long elapsedMillis = SystemClock.elapsedRealtime() - timerService.getStartTime();
                timer.setText(formatMillisecondsToTime(elapsedMillis));
                updateHandler.postDelayed(this, 1000);
            }
        }
    };

    private String formatMillisecondsToTime(long milliseconds) {
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                TimerService.TimerBinder binder = (TimerService.TimerBinder) service;
                timerService = binder.getService();
                isBound = true;
                if (timerService.isTimerRunning()) {
                    check_in.setText("Check Out");
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }
        };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBound) {
            k.getContext().unbindService(serviceConnection);
            isBound = false;
        }
    }
}