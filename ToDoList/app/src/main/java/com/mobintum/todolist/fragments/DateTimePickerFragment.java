package com.mobintum.todolist.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.mobintum.todolist.R;

/**
 * Created by Rick on 18/06/15.
 */
public class DateTimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Button btnSetTime, btnSetDate;
    DatePicker dateDialog;
    TimePicker timeDialog;
/*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_datetime, container, false);
        btnSetDate = (Button) view.findViewById(R.id.btnSetDate);
        btnSetTime = (Button) view.findViewById(R.id.btnSetTime);

        btnSetDate.setOnClickListener(this);
        btnSetTime.setOnClickListener(this);

        dateDialog = (DatePicker) view.findViewById(R.id.dateDialog);
        timeDialog = (TimePicker) view.findViewById(R.id.timeDialog);

        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSetDate:
                timeDialog.setVisibility(View.GONE);
                dateDialog.setVisibility(View.VISIBLE);

                break;

            case R.id.btnSetTime:
                dateDialog.setVisibility(View.GONE);
                timeDialog.setVisibility(View.VISIBLE);
                break;
        }
    }
}
