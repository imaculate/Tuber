package com.androidcamp.wakrodga.tuber;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by demouser on 8/4/16.
 */
public class TutorFormFragment extends Fragment{

    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Button btnSubmit;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnButton();
    }
    public void addListenerOnButton() {

        spinner1 = (Spinner) getActivity().findViewById(R.id.countries);
        spinner2 = (Spinner) getActivity().findViewById(R.id.cities);
        spinner3 = (Spinner) getActivity().findViewById(R.id.subjects);
        spinner4 = (Spinner) getActivity().findViewById(R.id.languages);
        checkBox1 = (CheckBox) getActivity().findViewById(R.id.frontal);
        checkBox2 = (CheckBox) getActivity().findViewById(R.id.online);


        btnSubmit = (Button) getActivity().findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutor_form, container, false);
    }
}
