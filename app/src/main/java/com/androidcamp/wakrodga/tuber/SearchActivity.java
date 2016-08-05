package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by demouser on 8/4/16.
 */
public class SearchActivity extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Button btnSubmit;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    public static ArrayList<Tutor> tutorsAfterSearch = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        addListenerOnButton();

    }



    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.countries);
        spinner2 = (Spinner) findViewById(R.id.cities);
        spinner3 = (Spinner) findViewById(R.id.subjects);
        spinner4 = (Spinner) findViewById(R.id.languages);
        checkBox1 = (CheckBox) findViewById(R.id.frontal);
        checkBox2 = (CheckBox) findViewById(R.id.online);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, MainPage.class);
                i.putExtra(MainPage.FILTER_RESULT, "true");
                startActivity(i);

                applyFilters();

            }

        });
    }

    private void applyFilters() {
        tutorsAfterSearch = new ArrayList<>();

        String country = String.valueOf(spinner1.getSelectedItem());
        String city = String.valueOf(spinner2.getSelectedItem());
        String subject = String.valueOf(spinner3.getSelectedItem());
        String language = String.valueOf(spinner4.getSelectedItem());

        boolean frontal = checkBox1.isChecked();
        boolean online = checkBox2.isChecked();

        for(Tutor t: Database.tutors)
        {
            if(t.city.equals(city) || city.equals("Choose a city")) {

                if (t.country.equals(country) || country.equals("Choose a country")) {

                    if (contains(t.getSubjects().values(), subject) || subject.equals("Choose a subject")) {

                        if (contains(t.getLanguages().values(),language )|| language.equals("Choose a Language")) {

                            if ((frontal == true && t.frontal) || frontal == false) {

                                    if ((true == online && t.online) || online == false) {

                                            tutorsAfterSearch.add(t);
                                           // Toast.makeText(SearchActivity.this, "Added: " + t.name, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }

                        }
                    }
                }


       // Toast.makeText(SearchActivity.this,"size: "+tutorsAfterSearch.size(),Toast.LENGTH_SHORT).show();

    }

    private boolean contains(Collection<String> values, String subject) {
        for(String val: values)
        {
            if(val.equals(subject))
                return true;
        }
        return false;
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
                //Toast.makeText(this, "EDITED", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                //Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;

    }
}
