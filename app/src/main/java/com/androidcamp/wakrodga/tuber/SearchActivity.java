package com.androidcamp.wakrodga.tuber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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



                Toast.makeText(SearchActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem())+
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+
                                "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem())+
                                "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem())+
                                "\ncheckbox 1 : "+ checkBox1.isChecked()+
                                "\ncheckbox 2 : "+ checkBox2.isChecked()
                        ,
                        Toast.LENGTH_SHORT).show();
            }

        });
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
