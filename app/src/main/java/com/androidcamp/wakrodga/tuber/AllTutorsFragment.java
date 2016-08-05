package com.androidcamp.wakrodga.tuber;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v4.app.ServiceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AllTutorsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AllTutorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllTutorsFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView listView = null;

    private OnFragmentInteractionListener mListener;

    public AllTutorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllTutorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllTutorsFragment newInstance(String param1, String param2) {
        AllTutorsFragment fragment = new AllTutorsFragment();
       /* Bundle args = new Bundle();

        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_tutors, container, false);


       // Database database = new Database();
        final ArrayList<Tutor> tutors = Database.tutors;

//        database.addOnTutorReadyListener(new Database.OnTutorListener() {
//            @Override
//            public void onTutorReady(Tutor tutor) {
//                tutors.add(tutor);
//            }
//        });
        MyAdapter adapter = new MyAdapter();

        mListener = new OnFragmentInteractionListener()
        {

            @Override
            public void onFragmentInteraction() {
                Intent i = new Intent(getContext(),Profile.class);
                startActivity(i);
            }
        };
        Intent i = getActivity().getIntent();
        if (i.getStringExtra(MainPage.FILTER_RESULT) != null) {
            adapter.setTutors(SearchActivity.tutorsAfterSearch);
        }

        // adapter.setTutors();
        listView = (ListView) v.findViewById(R.id.tutor_list_view);
        listView.setAdapter(adapter);





        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"Item clicked!", Toast.LENGTH_SHORT).show();
                Tutor tutor = (Tutor) adapterView.getItemAtPosition(i);
                mListener.onFragmentInteraction();

            }
        });*/
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }

    private class MyAdapter extends BaseAdapter {
        private ArrayList<Tutor> globalTutors = Database.tutors;

        public void setTutors(ArrayList<Tutor> tutors) {
            globalTutors = tutors;
        }
        ;

        public int getCount() {
            return globalTutors.size();
        }

        public Object getItem(int position) {
            return globalTutors.get(position);

        }

        public long getItemId(int position) {
            return globalTutors.get(position).hashCode();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if (null == convertView) {
                LayoutInflater inflater = LayoutInflater.from(getActivity().getApplicationContext());
                view = inflater.inflate(R.layout.card_view_tutor, null);

                CardView card = (CardView) view.findViewById(R.id.card_view_tutor);
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(),"clicked "+Database.tutors.get(position).name,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent (getContext(), Profile.class);
                        i.putExtra("id",position);
                        startActivity(i);
                    }
                });

                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.ratingBar = (RatingBar) view.findViewById(R.id.radingBar);
                holder.city = (TextView) view.findViewById(R.id.city);
                holder.image = (ImageView) view.findViewById(R.id.tutor_image);

                holder.subjects = (TextView) view.findViewById(R.id.tutors_subjects_textView);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();

            }

            Tutor current = globalTutors.get(position);
            holder.name.setText(current.getName());
            holder.ratingBar.setRating(current.reputation.floatValue());
            holder.city.setText(current.getCity() + ", " + current.getCountry());
            String subjects = "Teaches: ";
            for (String string : current.getSubjects().values()) {
                subjects += string + ", ";
                if (subjects.length() > 50) {
                    subjects += "...";
                    break;
                }
            }
            holder.subjects.setText(subjects);
            Picasso.with(getActivity().getApplicationContext()).load(current.getImage()).into(holder.image);
            holder.image.setOutlineProvider(new OvalOutlineProvider());

            return view;
        }

        private class ViewHolder {
            public TextView name;
            public RatingBar ratingBar;
            public TextView city;
            public ImageView image;
            public TextView subjects;

        }

    }

    static class OvalOutlineProvider extends ViewOutlineProvider {

        @Override

        public void getOutline(View view, Outline outline) {

            outline.setOval(0, 0, view.getWidth(), view.getHeight());

        }

    }

}
