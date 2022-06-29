package com.varsitycollege.mycollectables;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MyStats extends AppCompatActivity {

    BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stats);

        //Assign variable
        barChart = findViewById(R.id.bar_chart);

        //Initialize array list
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        //For loop
        for(int i=1; i<10; i++){
            float value = (float) (i*10.0);
            BarEntry barEntry = new BarEntry(i,value);
            barEntries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Collections");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(5000);
        barChart.getDescription().setText("Collection Chart");
        barChart.getDescription().setTextColor(Color.BLUE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //code below creates options for our menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), CategoriesScreen.class));
                finish();
                return true;
            case R.id.nav_addCategory:
                startActivity(new Intent(getApplicationContext(), AddNewCategory.class));
                finish();
                return true;
            case R.id.nav_addToCategory:
                startActivity(new Intent(getApplicationContext(), AddToCategoryScreen.class));
                finish();
                return true;
            case R.id.nav_myStats:
                startActivity(new Intent(getApplicationContext(), MyStats.class));
                finish();
                return true;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
