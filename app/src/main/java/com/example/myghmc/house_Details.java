package com.example.myghmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class house_Details extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ProgressBar circularProgressBar;
    private TextView percentageTextView;
    BarChart barChart;
    private ImageView back,calender_open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        getSupportActionBar().hide();
        barChart=(BarChart) findViewById(R.id.barChart);
        recyclerView = findViewById(R.id.scanned_info_table);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        percentageTextView = findViewById(R.id.percentageTextView);
        back=findViewById(R.id.back);
        calender_open=findViewById(R.id.calenderopen);
        int end=(125*100/200);
        back.setOnClickListener(this);
        calender_open.setOnClickListener(this);
        animateCircularProgressBar(0, end,1500);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<house_data> data = new ArrayList<>();
        data.add(new house_data("A101","10:00 AM"));
        data.add(new house_data("B202","11:30 AM"));
        data.add(new house_data("C303","12:00 PM"));
        data.add(new house_data("D101","1:00 PM"));
        data.add(new house_data("E202","2:30 PM"));
        data.add(new house_data("F303","3:00 PM"));
        data.add(new house_data("A101","10:00 AM"));
        data.add(new house_data("B202","11:30 AM"));
        data.add(new house_data("C303","12:00 PM"));
        data.add(new house_data("D101","1:00 PM"));
        data.add(new house_data("E202","2:30 PM"));
        data.add(new house_data("F303","3:00 PM"));
        data.add(new house_data("A101","10:00 AM"));
        data.add(new house_data("B202","11:30 AM"));
        data.add(new house_data("C303","12:00 PM"));
        data.add(new house_data("D101","1:00 PM"));
        data.add(new house_data("E202","2:30 PM"));
        data.add(new house_data("F303","3:00 PM"));
        data.add(new house_data("A101","10:00 AM"));
        data.add(new house_data("B202","11:30 AM"));
        data.add(new house_data("C303","12:00 PM"));
        data.add(new house_data("D101","1:00 PM"));
        data.add(new house_data("E202","2:30 PM"));
        data.add(new house_data("F303","3:00 PM"));
        TableAdapter adapter = new TableAdapter(data);
        recyclerView.setAdapter(adapter);
        bargraph_display();
    }
    private void animateCircularProgressBar(int start, int end,int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(circularProgressBar, "progress", start, end);
        objectAnimator.setDuration(duration);
        objectAnimator.addUpdateListener(animation -> {
            int progress = (int) animation.getAnimatedValue();
            percentageTextView.setText(progress + "%");
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                circularProgressBar.setProgress(end);
            }
        });
        objectAnimator.start();
    }
    private void bargraph_display() {
        String[] xAxisLabels = new String[]{"Total","Scanned", "Unscanned"};
        int[] yAxisValues = new int[]{200, 125, 75};
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < xAxisLabels.length; i++) {
            entries.add(new BarEntry(i, yAxisValues[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Number of Houses");
        BarData barData = new BarData(dataSet);

        barChart.setData(barData);

        // Setting X-axis labels
        barChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisLabels));
        barChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        barChart.getDescription().setEnabled(false);  // Hide description
        barChart.getLegend().setEnabled(false);  // Hide legend if not needed
        barChart.invalidate();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.swipe_in_left, R.anim.swipe_out_right);
    }
    @Override
    public void onClick(View view) {
        if(view==back)
        {
            super.onBackPressed();
            overridePendingTransition(R.anim.swipe_in_left, R.anim.swipe_out_right);
        }
        if(view==calender_open)
        {
            Toast.makeText(this, "Work Under Progress", Toast.LENGTH_SHORT).show();
        }
    }
}