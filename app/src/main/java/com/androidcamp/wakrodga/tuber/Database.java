package com.androidcamp.wakrodga.tuber;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by demouser on 8/4/16.
 */
public class Database {

    private static final String TAG = "bla";
    private OnTutorListener onTutorListener;
    private OnStudentListener onStudentListener;

    public interface OnTutorListener {
        public void onTutorReady(Tutor tutor);
    }

    public interface  OnStudentListener {
        public void onStudentReady(Student student);
    }

    public Database() {
        addListeners();
    }

    public void addOnTutorReadyListener(OnTutorListener onTutorListener) {
        this.onTutorListener = onTutorListener;
    }

    public void addOnStudentReadyListener(OnStudentListener onStudentListener) {
        this.onStudentListener = onStudentListener;
    }

    private void addListeners() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tutorsRef = rootRef.child("tutors");
        tutorsRef.addChildEventListener(getTutorListener());

        DatabaseReference studentsRef = rootRef.child("students");
        studentsRef.addChildEventListener(getStudentListener());

    }

    private ChildEventListener getTutorListener() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                Tutor tutor = dataSnapshot.getValue(Tutor.class);
                onTutorListener.onTutorReady(tutor);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                Tutor newComment = dataSnapshot.getValue(Tutor.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Tutor movedComment = dataSnapshot.getValue(Tutor.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                // Toast.makeText(mContext, "Failed to load comments.",
                //       Toast.LENGTH_SHORT).show();
            }
        };
    }

    private ChildEventListener getStudentListener() {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                Student student = dataSnapshot.getValue(Student.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                Student newComment = dataSnapshot.getValue(Student.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Student movedComment = dataSnapshot.getValue(Student.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                // Toast.makeText(mContext, "Failed to load comments.",
                //       Toast.LENGTH_SHORT).show();
            }
        };
    }

}
