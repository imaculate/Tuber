package com.androidcamp.wakrodga.tuber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import az.plainpie.PieView;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Database database = new Database();


        database.addOnTutorReadyListener(new Database.OnTutorListener() {
            @Override
            public void onTutorReady(Tutor tutor) {
                Database.tutors.add(tutor);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorAccent));
        pieView.setMainBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String msg = "";
        switch (menuItem.getItemId()) {
            case R.id.edit:
                Toast.makeText(this, "EDITED", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
