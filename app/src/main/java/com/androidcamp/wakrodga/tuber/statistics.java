package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Random;

import az.plainpie.PieView;


public class Statistics extends AppCompatActivity implements Database.onDataChanged, Database.onChildChanged {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Database database = Database.getDatabaseInstance();
        database.setDataChangedListener(this);
        database.setChildlistener(this);

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorAccent2));
        pieView.setMainBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        PieView pieView2 = (PieView) findViewById(R.id.pieView2);
        pieView2.setPercentageBackgroundColor(getResources().getColor(R.color.colorPrimary2));

        doMaths();
    }

    public void doMaths() {

        PieView pieView = (PieView) findViewById(R.id.pieView);
        pieView.setPercentageBackgroundColor(getResources().getColor(R.color.colorAccent2));
        pieView.setMainBackgroundColor(getResources().getColor(R.color.colorPrimaryDark2));

        TextView textView = (TextView) findViewById(R.id.price);

        PieView pieView2 = (PieView) findViewById(R.id.pieView2);
        pieView2.setPercentageBackgroundColor(getResources().getColor(R.color.colorPrimary2));

        TextView textView2 = (TextView) findViewById(R.id.mysubject);

        Tutor me = new Tutor();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        boolean isMe = false;
        for (Tutor t : Database.getDatabaseInstance().tutors) {
            if(t.getName().equals(auth.getCurrentUser().getDisplayName())){
                me=t;
                isMe=true;
                break;
            }
        }

        if(!isMe) {
            pieView.setmPercentage(0);
            textView.setText(String.valueOf(0)+"$");
            pieView2.setmPercentage(0);
            textView2.setText("YOU DON'T TEACH ANY SUBJECT");

        }else {
            ArrayList<String> mySubjects = new ArrayList<>();
            for(String s : me.getSubjects().values()) {
                mySubjects.add(s);
            }
            Random r = new Random();
            int index = 0;
            /*if(mySubjects.size()>1)
                index = r.nextInt(mySubjects.size()-1);*/
            long result = Math.round(findLowerPrices(mySubjects, (double) me.getPrice()));
            double averagePrice = Math.round(averagePrice(mySubjects));
            long result2 = Math.round(sameSubject(mySubjects.get(index)));

            pieView.setmPercentage(result);
            textView.setText(String.valueOf(averagePrice)+"$");
            pieView2.setmPercentage(result2);
            textView2.setText("TUTORS ALSO TEACHING " +mySubjects.get(index).toUpperCase());
        }
    }

    public double findLowerPrices(ArrayList<String> mySubjects, Double myPrice) {
        double percentage = 0;
        int count = 0;
        int countLower = 0;
        for(Tutor t : Database.getDatabaseInstance().tutors) {
            for(String mySubject : mySubjects) {
                if (t.getSubjects().values().contains(mySubject)) {
                    count++;
                    if (t.getPrice() < myPrice) {
                        countLower++;
                    }
                }
            }
        }
        if(count!=0)percentage = (double)countLower/(double)count;
        percentage*=100;
        return percentage;
    }

    public double averagePrice(ArrayList<String> mySubjects) {
        double average = 0;
        int count = 0;
        int sum = 0;
        for(Tutor t : Database.getDatabaseInstance().tutors) {
            for(String mySubject : mySubjects) {
                if (t.getSubjects().values().contains(mySubject)) {
                    count++;
                    sum += t.getPrice();
                }
            }
        }
        average = (double) sum / (double) count;
        return average;
    }

    public double sameSubject(String mySubject) {
        double percentage = 0;
        int count = 0;
        int countSubject = 0;
        for(Tutor t : Database.getDatabaseInstance().tutors) {
            if(t.getSubjects().values().contains(mySubject)){
                countSubject++;
            }
            count++;
        }
        if(count!=0)percentage = (double)countSubject/(double)count;
        percentage*=100;
        return percentage;
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
                startActivity(new Intent(Statistics.this, TutorFormActivity.class));
                break;
            case R.id.log_out:
                Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(Statistics.this, MainActivity.class));
                                finish();
                            }
                        });
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDataChange() {
        doMaths();
    }

    @Override
    public void onChildChange() {
        doMaths();
    }

}
