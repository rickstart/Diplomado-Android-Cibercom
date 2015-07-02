package com.mobintum.todolist.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobintum.todolist.R;
import com.mobintum.todolist.adapters.TaskAdapter;
import com.mobintum.todolist.models.Task;

import java.util.ArrayList;


public class ListTasksFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayList<Task> tasks;
    private TaskAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = Task.getTask(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tasks, container, false);
        ListView listTasks = (ListView) view.findViewById(R.id.listTasks);
        adapter = new TaskAdapter(getActivity(),R.layout.item_list_task,tasks);
        listTasks.setAdapter(adapter);


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

        public void onTaskSelected(Task Task);
    }

}
