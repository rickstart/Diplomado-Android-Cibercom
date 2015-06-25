package com.mobintum.todolist.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.mobintum.todolist.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddTaskFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private Switch switchTerm;
    private TextView txtDateTime;
    private boolean checked = false;
    private LinearLayout linearDateTime;
    private Spinner spinnerPriority;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        ImageButton btnDateTime = (ImageButton) view.findViewById(R.id.btnDateTime);
        switchTerm = (Switch) view.findViewById(R.id.switchTerm);
        txtDateTime = (TextView) view.findViewById(R.id.txtDateTime);
        linearDateTime = (LinearLayout) view.findViewById(R.id.linearDateTime);
        spinnerPriority = (Spinner) view.findViewById(R.id.spinnerPriority);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_priority_spinner,getActivity().getResources().getStringArray(R.array.priority_array));
        spinnerPriority.setAdapter(adapter);


        switchTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
                if( isChecked ){

                    linearDateTime.setVisibility(View.VISIBLE);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

                    txtDateTime.setText(dateFormat.format(Calendar.getInstance().getTime()));
                }else{
                    txtDateTime.setText("");
                    linearDateTime.setVisibility(View.GONE);
                }
            }
        });
        btnDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DateTimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });


        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onCreateTask();
    }

}
