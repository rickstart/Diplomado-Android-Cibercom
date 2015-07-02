package com.mobintum.todolist.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mobintum.todolist.R;
import com.mobintum.todolist.models.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddTaskFragment extends Fragment implements DateTimePickerFragment.OnDialogFragmentInteractionListener{


    private OnFragmentInteractionListener mListener;
    private Switch switchTerm;
    private TextView txtDateTime;
    private boolean checked = false;
    private LinearLayout linearDateTime;
    private Spinner spinnerPriority;
    private EditText etTitle, etDescription;
    private SimpleDateFormat dateFormat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        ImageButton btnDateTime = (ImageButton) view.findViewById(R.id.btnDateTime);
        switchTerm = (Switch) view.findViewById(R.id.switchTerm);
        txtDateTime = (TextView) view.findViewById(R.id.txtDateTime);
        linearDateTime = (LinearLayout) view.findViewById(R.id.linearDateTime);
        spinnerPriority = (Spinner) view.findViewById(R.id.spinnerPriority);
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etDescription = (EditText) view.findViewById(R.id.etDescription);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.item_priority_spinner,getActivity().getResources().getStringArray(R.array.priority_array));
        spinnerPriority.setAdapter(adapter);


        switchTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
                if( isChecked ){

                    linearDateTime.setVisibility(View.VISIBLE);
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
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
                DialogFragment dialogFragment = new DateTimePickerFragment();
                dialogFragment.setTargetFragment(AddTaskFragment.this, 0);
                dialogFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_add_task,menu);

        MenuItem menuSave = menu.findItem(R.id.menu_save);
        menuSave.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(validateInput(etTitle) && validateInput(etDescription)) {
                    if( spinnerPriority.getSelectedItemPosition() == 0 )
                        Toast.makeText(getActivity(), "Select Priority", Toast.LENGTH_SHORT).show();
                    else{

                        Date date =  null;
                        if(switchTerm.isChecked()) {
                            try {
                                date = dateFormat.parse(txtDateTime.getText().toString());
                            } catch (Exception e) {

                            }
                        }

                        Task task = new Task(etTitle.getText().toString(),spinnerPriority.getSelectedItemPosition(),
                                etDescription.getText().toString(),1, date );

                        Task.insert(getActivity(),task);
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }
                return false;
            }
        });

        MenuItem menuDiscard = menu.findItem(R.id.menu_discard);
        menuDiscard.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getActivity().getSupportFragmentManager().popBackStack();
                return false;
            }
        });
    }

    public boolean validateInput(EditText editText){
        if(editText.getText().toString().trim().length() == 0) {
            editText.setError("Empty");
            return false;
        }else {
            return true;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFinishedDialogFragment(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txtDateTime.setText(dateFormat.format(date));
    }


    public interface OnFragmentInteractionListener {

        public void onCreateTask();
    }

}
