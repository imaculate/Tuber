package com.androidcamp.wakrodga.tuber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database database = new Database();
        final ArrayList<Tutor> tutors = new ArrayList<>();

        database.addOnTutorReadyListener(new Database.OnTutorListener() {
            @Override
            public void onTutorReady(Tutor tutor) {
                tutors.add(tutor);
            }
        });

    }
}
