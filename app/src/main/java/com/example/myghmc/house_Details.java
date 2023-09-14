package com.example.myghmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class house_Details extends AppCompatActivity{

    RecyclerView scanned_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        getSupportActionBar().hide();
        BarChart barChart=(BarChart) findViewById(R.id.barChart);
        RecyclerView recyclerView = findViewById(R.id.scanned_info_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<house_data> data = new ArrayList<>();
        // Add data to the list
        data.add(new house_data("A101","10:00 AM"));
        data.add(new house_data("B202","11:30 AM"));
        data.add(new house_data("C303","12:00 PM"));
        TableAdapter adapter = new TableAdapter(data);
        recyclerView.setAdapter(adapter);

        String[] xAxisLabels = new String[]{"East", "West", "South", "North"};
        int[] yAxisValues = new int[]{50, 75, 35, 90};
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
        overridePendingTransition(R.anim.swipe_in_left,
                R.anim.swipe_out_right);
    }
}