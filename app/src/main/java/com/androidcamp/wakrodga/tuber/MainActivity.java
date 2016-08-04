package com.androidcamp.wakrodga.tuber;


import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AllTutorsFragment.OnFragmentInteractionListener{

    public static String TAG_ALL = "all_tutors";
    public static String TAG_MY = "my_tutors";
    public static String TAG_FAV = "fav_tutors";


    public static String TITLE_ALL = "ALL TUTORS";
    public static String TITLE_MY = "MY TUTORS";
    public static String TITLE_FAV = "FAVORITES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
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

        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        AllTutorsFragment frag = new AllTutorsFragment();

        fragTransaction.add(R.id.fragment_container,frag , TAG_ALL ).commit();



    }



    @Override
    public void onFragmentInteraction() {

    }
}
