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
    private static Database database;

    public static Set<String> names = new HashSet<>();
    private  onDataChanged listener;
    private  onChildChanged childListener;

    private Database() {

    }

    public static Database getDatabaseInstance() {
        if (database == null) {
            database = new Database();
            database.addListeners();
        }
        return  database;
    }



    public interface onDataChanged {
        public void onDataChange();
    }

    public interface onChildChanged {
        public void onChildChange();
    }

    public void setDataChangedListener(onDataChanged listener) {
        this.listener = listener;
    }

    public  void setChildlistener(onChildChanged listener) {
        childListener = listener;
    }

    private void addListeners() {
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tutorsRef = rootRef.child("tutors");
        tutorsRef.addValueEventListener(getTutorsValueEventListener());
        tutorsRef.addChildEventListener(getChildListener());

        DatabaseReference studentsRef = rootRef.child("students");

    }


    private  ChildEventListener getChildListener() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Tutor tutor = dataSnapshot.getValue(Tutor.class);
                for(Tutor t : tutors) {
                    if(tutor.getName().equals(t.getName())) {
                        tutors.remove(t);
                        tutors.add(tutor);
                        break;
                    }
                }
                childListener.onChildChange();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
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
                    if(listener!=null)
                        listener.onDataChange();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

}
