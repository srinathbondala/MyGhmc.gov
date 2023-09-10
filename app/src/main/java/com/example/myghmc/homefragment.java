package com.example.myghmc;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class homefragment extends Fragment implements View.OnClickListener{
    ConstraintLayout card1,card2,card3;
    Button check_in;
    CalendarView calendarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container,false);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        check_in = view.findViewById(R.id.chk_in);
        calendarView = view.findViewById(R.id.select_calender);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(view.getContext(),house_Details.class);
        if(view==card1) {
            Toast.makeText(view.getContext(), "Total houses 200", Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(i, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
        } else if(view==card2) {
            Toast.makeText(view.getContext(), "houses scanned 100", Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(i, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
        } else if (view==card3) {
            Toast.makeText(view.getContext(), "house unscanned", Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(i, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle());
        }
    }
}

