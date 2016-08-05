package com.androidcamp.wakrodga.tuber;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by demouser on 8/4/16.
 */
public class Database {

    private static final String TAG = "bla";
    public static ArrayList<Tutor> tutors = new ArrayList<>();

    public static Set<String> names = new HashSet<>();


    public Database() {
        addListeners();
    }

    private void addListeners() {
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tutorsRef = rootRef.child("tutors");
        tutorsRef.addValueEventListener(getTutorsValueEventListener());

        DatabaseReference studentsRef = rootRef.child("students");
    }


    private ValueEventListener getTutorsValueEventListener() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Tutor tutor = data.getValue(Tutor.class);
                    if(!names.contains(tutor.name)) {
                        tutors.add(tutor);
                        names.add(tutor.name);
                    }
                    Log.d("VALUE EVENT LISTENER", tutor.country + "");
                    AllTutorsFragment.setTutors(tutors);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

}
