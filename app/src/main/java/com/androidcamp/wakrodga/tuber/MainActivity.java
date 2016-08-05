package com.androidcamp.wakrodga.tuber;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AllTutorsFragment.OnFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private boolean successfullLogin=false;
    private boolean dataDownloaded = false;
    private ViewPager mViewPager;
    public static String TAG_ALL = "all_tutors";
    public static String TAG_MY = "my_tutors";
    public static String TAG_FAV = "fav_tutors";

    public static String TITLE_ALL = "ALL TUTORS";
    public static String TITLE_MY = "MY TUTORS";
    public static String TITLE_FAV = "FAVORITES";

    private FirebaseAuth mAuth;



    public static final ArrayList<Tutor> tutors = new ArrayList<>();

    private static final int RC_SIGN_IN = 1;
    private static final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //AuthUI.getInstance().signOut(this);

        FragmentManager fragMan = getSupportFragmentManager();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
            Log.d("BUGBUG","user is already signed in, lets move to fromActivity");
            //startActivity(new Intent(this, FormActivity.class));
            startActivity(new Intent(MainActivity.this, Statistics.class));

            finish();
        } else {
            // not signed in
            Log.d("BUGBUG","user is NOT signed in, lets show sign in options");
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setProviders(AuthUI.FACEBOOK_PROVIDER,AuthUI.GOOGLE_PROVIDER,AuthUI.EMAIL_PROVIDER)
                            .setTheme(R.style.SuperAppTheme)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
            if (requestCode == RESULT_OK) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));
            }
    }


    @Override
    public void onFragmentInteraction() {


    }

    @Override
    public void onStart() {
        super.onStart();
     }

    @Override
    public void onStop() {
        super.onStop();

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
               // Intent intent = new Intent(MainActivity.this, Statistics.class);
               // startActivity(intent);
                break;
            case R.id.log_out:
                Toast.makeText(this, "LOGED_OUT", Toast.LENGTH_SHORT).show();
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                //startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                                FirebaseAuth.getInstance().signOut();
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
