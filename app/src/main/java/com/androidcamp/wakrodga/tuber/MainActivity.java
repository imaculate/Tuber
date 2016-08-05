package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.content.Intent;

import android.app.LauncherActivity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements AllTutorsFragment.OnFragmentInteractionListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static String TAG_ALL = "all_tutors";
    public static String TAG_MY = "my_tutors";
    public static String TAG_FAV = "fav_tutors";

    public static String TITLE_ALL = "ALL TUTORS";
    public static String TITLE_MY = "MY TUTORS";
    public static String TITLE_FAV = "FAVORITES";

    public static final ArrayList<Tutor> tutors = new ArrayList<>();

    private static final int RC_SIGN_IN = 1;
    private static final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Database database = new Database();

        database.addOnTutorReadyListener(new Database.OnTutorListener() {
            @Override
            public void onTutorReady(Tutor tutor) {
                tutors.add(tutor);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //AuthUI.getInstance().signOut(this);

        FragmentManager fragMan = getSupportFragmentManager();

        mSectionsPagerAdapter = new SectionsPagerAdapter(fragMan);

        // Set up the ViewPager  with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabslayout);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton myFab = (FloatingActionButton)findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });
        /*ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                if(tab.getText().equals(TITLE_ALL)){
                    FragmentManager fragMan = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragMan.beginTransaction();
                    AllTutorsFragment frag = new AllTutorsFragment();

                    fragTransaction.replace(R.id.fragment_container,frag , TAG_ALL ).commit();


                }else if(tab.getText().equals(TITLE_MY)){

                }else{

                }

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab_one = getSupportActionBar().newTab();

        tab_one.setTabListener(tabListener);
        tab_one.setText(TITLE_ALL);
        getSupportActionBar().addTab(tab_one);

        ActionBar.Tab tab_two = getSupportActionBar().newTab();
        tab_two.setTabListener(tabListener);
        tab_two.setText(TITLE_MY);
        getSupportActionBar().addTab(tab_two);

        ActionBar.Tab tab_three = getSupportActionBar().newTab();
        tab_three.setTabListener(tabListener);
        tab_three.setText(TITLE_FAV);
        getSupportActionBar().addTab(tab_three);


        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        AllTutorsFragment frag = new AllTutorsFragment();

        fragTransaction.add(R.id.fragment_container,frag , TAG_ALL ).commit();*/

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setProviders(AuthUI.FACEBOOK_PROVIDER,AuthUI.GOOGLE_PROVIDER,AuthUI.EMAIL_PROVIDER)
                        .setTheme(R.style.SuperAppTheme)
                        .build(),
                RC_SIGN_IN);
       // startActivity(new Intent(this, FormActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
            if (requestCode == RESULT_OK) {
                // Logged in!
                startActivity(new Intent(this, FormActivity.class));
                //AuthUI.getInstance()
                  //      .signOut(this);
                finish();
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            }
    }




    @Override
    public void onFragmentInteraction() {


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
                Intent intent = new Intent(MainActivity.this, Statistics.class);
                startActivity(intent);
                break;
            case R.id.log_out:
                Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                break;
            default:
                break;
        }
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return  new AllTutorsFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TITLE_ALL;
                case 1:
                    return TITLE_MY;
                case 2:
                    return TITLE_FAV;
            }
            return null;
        }
    }
}
