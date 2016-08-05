package com.androidcamp.wakrodga.tuber;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class StudentFormActivity extends AppCompatActivity {


    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView imageView1 = (ImageView) findViewById(R.id.place_icon);
        imageView1.setColorFilter(Color.argb(255, 255, 255, 255));

        ImageView imageView2 = (ImageView) findViewById(R.id.place_icon2);
        imageView2.setColorFilter(Color.argb(255, 255, 255, 255));


        ImageView imageView4 = (ImageView) findViewById(R.id.lang_icon);
        imageView4.setColorFilter(Color.argb(255, 255, 255, 255));

        spinner1= (Spinner) findViewById(R.id.countries);
        ArrayAdapter<CharSequence> foodadapter = ArrayAdapter.createFromResource(
                this, R.array.country_arrays, R.layout.spinner_item);
        foodadapter.setDropDownViewResource( R.layout.spinner_item);
        spinner1.setAdapter(foodadapter);


        spinner2= (Spinner) findViewById(R.id.cities);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.city_arrays, R.layout.spinner_item);
        adapter.setDropDownViewResource( R.layout.spinner_item);
        spinner2.setAdapter(adapter);


        spinner4= (Spinner) findViewById(R.id.languages);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.languages_arrays, R.layout.spinner_item);
        adapter3.setDropDownViewResource( R.layout.spinner_item);
        spinner4.setAdapter(adapter3);
    }

public void clickedApply(View v){
    startActivity(new Intent(StudentFormActivity.this, MainPage.class));
}
}
