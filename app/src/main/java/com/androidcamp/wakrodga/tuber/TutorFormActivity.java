package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by demouser on 8/4/16.
 */
public class TutorFormActivity extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Button btnSubmit;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tutor_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addListenerOnButton();
    }
    public void addListenerOnButton() {


        checkBox1 = (CheckBox) findViewById(R.id.frontal);
        checkBox2 = (CheckBox) findViewById(R.id.online);



        ImageView imageView1 = (ImageView) findViewById(R.id.place_icon);
        imageView1.setColorFilter(Color.argb(255, 255, 255, 255));

        ImageView imageView2 = (ImageView) findViewById(R.id.place_icon2);
        imageView2.setColorFilter(Color.argb(255, 255, 255, 255));

        ImageView imageView3 = (ImageView) findViewById(R.id.subjects_icon);
        imageView3.setColorFilter(Color.argb(255, 255, 255, 255));

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

        spinner3= (Spinner) findViewById(R.id.subjects);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.subjects_arrays, R.layout.spinner_item);
        adapter2.setDropDownViewResource( R.layout.spinner_item);
        spinner3.setAdapter(adapter2);

        spinner4= (Spinner) findViewById(R.id.languages);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.languages_arrays, R.layout.spinner_item);
        adapter3.setDropDownViewResource( R.layout.spinner_item);
        spinner4.setAdapter(adapter3);


//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TutorFormActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem())+
//                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+
//                                "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem())+
//                                "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem())+
//                                "\ncheckbox 1 : "+ checkBox1.isChecked()+
//                                "\ncheckbox 2 : "+ checkBox2.isChecked()
//                        ,
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
    }
    public void clickedApply(View v){
        saveData();
        startActivity(new Intent(TutorFormActivity.this,Statistics.class));
    }

    public void saveData() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Tutor t = new Tutor();
        t.setCity(spinner2.getSelectedItem().toString());
        t.setCountry(spinner1.getSelectedItem().toString());
        t.setPrice((long) 0);
        Map<String, String> lan = new HashMap<String, String>();
        lan.put("name", spinner4.getSelectedItem().toString());
        t.setLanguages(lan);
        Map<String, String> sub = new HashMap<String, String>();
        sub.put("name", spinner3.getSelectedItem().toString());
        t.setSubjects(sub);
        t.setName(auth.getCurrentUser().getDisplayName());
        t.setReputation((double) 0);
        if(checkBox1.isChecked()) {
            t.setFrontal(true);
        }
        if(checkBox2.isChecked()) {
            t.setOnline(true);
        }
        t.setImage("https://cdn4.iconfinder.com/data/icons/small-n-flat/24/user-alt-128.png");
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("tutors").child(auth.getCurrentUser().getDisplayName()).setValue(t);
    }

}
