package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by demouser on 8/4/16.
 */
public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();


        Integer id = intent.getIntExtra("id",-1);
        //if(id != -1)
          //  Toast.makeText(Profile.this,"the id:"+id,Toast.LENGTH_SHORT).show();
        final Tutor tutor = Database.tutors.get(id);

        ImageView v = (ImageView) findViewById(R.id.profile_image);


        Picasso.with(Profile.this).load(tutor.getImage()).error(R.drawable.com_facebook_profile_picture_blank_portrait).into(v);
        v.setOutlineProvider(new OvalOutlineProvider());
        v.setClipToOutline(true);

        TextView tv = (TextView)findViewById(R.id.profile_name);
        tv.setText(tutor.getName());

        TextView tv2 = (TextView)findViewById(R.id.profile_country);
        tv2.setText(tutor.getCountry());

        TextView tv3 = (TextView)findViewById(R.id.profile_city);
        tv3.setText(tutor.getCity());

        TextView tv4 = (TextView)findViewById(R.id.profile_subjects);

        String sub = "";
        if(tutor.getSubjects() != null) {
            for (String s : tutor.getSubjects().values())
                sub += s + ", ";
            sub = sub.substring(0, sub.length() - 2);
        }
        tv4.setText(sub);

        TextView tv5 = (TextView)findViewById(R.id.profile_lang);

        String lan = "";
        for(String s: tutor.getLanguages().values())
            lan += s+", ";
        lan = lan.substring(0,lan.length()-2);
        tv5.setText(lan);


        RatingBar rb = (RatingBar)findViewById(R.id.radingBar_profile);
        rb.setRating(tutor.reputation.floatValue());

        TextView tv6 = (TextView)findViewById(R.id.profile_price);
        tv6.setText(tutor.getPrice().toString());


        CheckBox checkBox1 = (CheckBox) findViewById(R.id.frontal_profile);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.online_profile);
        checkBox1.setChecked(tutor.getFrontal());
        checkBox2.setChecked(tutor.getOnline());


        FloatingActionButton  bookButton = (FloatingActionButton)findViewById(R.id.book_fab);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Request has been to sent to "+tutor.getName(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    static class OvalOutlineProvider extends ViewOutlineProvider {
        @Override public void getOutline(View view, Outline outline)
        { outline.setOval(0, 0, view.getWidth(), view.getHeight()); } }


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
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(Profile.this, MainActivity.class));
                                finish();
                            }
                        });
                break;
            default:
                break;
        }
        return true;
    }
}
