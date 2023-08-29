package com.example.myghmc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
public class user_home_fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home_fragment,container,false);
        BarChart barChart = view.findViewById(R.id.barChart);

        // Data
        String[] xAxisLabels = new String[]{"East", "West", "South", "North"};
        int[] yAxisValues = new int[]{50, 75, 35, 90};  // Sample house counts

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
        return view;
    }
}
