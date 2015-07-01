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

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rick on 18/06/15.
 */
public class DateTimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    Button btnSetTime, btnSetDate;
    DatePicker dateDialog;
    TimePicker timeDialog;
    int year;
    int month;
    int day;
    int hour;
    int min;
    int minYear;
    int minMonth;
    int minDay;
    Date date;
    Button btnOk, btnCancel;
    boolean setTime = false;
    Calendar c;
    OnDialogFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = Calendar.getInstance();

        minYear = c.get(Calendar.YEAR);
        minMonth = c.get(Calendar.MONTH);
        minDay = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        min = c.get(Calendar.MINUTE);

        year = minYear;
        month = minMonth;
        day = minDay;

        date = c.getTime();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_datetime, container, false);
        btnSetDate = (Button) view.findViewById(R.id.btnSetDate);
        btnSetTime = (Button) view.findViewById(R.id.btnSetTime);
        btnOk = (Button) view.findViewById(R.id.btnOk);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnSetDate.setOnClickListener(this);
        btnSetTime.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        dateDialog = (DatePicker) view.findViewById(R.id.dateDialog);
        timeDialog = (TimePicker) view.findViewById(R.id.timeDialog);


        dateDialog.init(minYear, minMonth, minDay, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (year < minYear)
                    view.updateDate(minYear, minMonth, minDay);

                if (monthOfYear < minMonth && year == minYear)
                    view.updateDate(minYear, minMonth, minDay);

                if (dayOfMonth < minDay && year == minYear && monthOfYear == minMonth)
                    view.updateDate(minYear, minMonth, minDay);

                date = getDateFromDatePicker(dateDialog);
                setTime = true;



            }
        });

        timeDialog.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                c.set(year,month,day,hourOfDay,minute);
                min = minute;
                hour = hourOfDay;
                date = c.getTime();

            }
        });

        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {



    }

    public  Date getDateFromDatePicker(DatePicker datePicker){
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 23, 59, 59);

        return calendar.getTime();
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

            case R.id.btnOk:
                mListener = (OnDialogFragmentInteractionListener) getTargetFragment();
                mListener.onFinishedDialogFragment(date);
                this.dismiss();
                break;

            case R.id.btnCancel:
                this.dismiss();
                break;

        }
    }


    public interface OnDialogFragmentInteractionListener{

        void onFinishedDialogFragment(Date date);
    }
}
