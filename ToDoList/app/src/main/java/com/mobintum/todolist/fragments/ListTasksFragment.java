package com.mobintum.todolist.fragments;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.mobintum.todolist.R;
import com.mobintum.todolist.adapters.TaskAdapter;
import com.mobintum.todolist.models.Task;

import java.util.ArrayList;


public class ListTasksFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayList<Task> tasks;
    private TaskAdapter adapter;
    private ListView listTasks;
    private float historicX = Float.NaN, historicY = Float.NaN;
    private static final int DELTA = 50;
    private enum Direction {LEFT, RIGHT;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = Task.getTask(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tasks, container, false);
        listTasks = (ListView) view.findViewById(R.id.listTasks);
        adapter = new TaskAdapter(getActivity(),R.layout.item_list_task,tasks);
        listTasks.setAdapter(adapter);
        listTasks.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "CLIC", Toast.LENGTH_SHORT).show();
                Log.e("TAG","CLIC");
            }
        });

        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        ImageButton btnFab = (ImageButton) view.findViewById(R.id.fab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().addToBackStack(null)
                        .replace(R.id.container, new AddTaskFragment())
                        .commit();

            }
        });

        listTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           final View view, final int position, long id) {
                Toast.makeText(getActivity(),"LONG CLIC", Toast.LENGTH_SHORT).show();
                removeRow(view, position);
                return true;
            }
        });


        return view;
    }


    private void removeRow(final View row, final int position) {
        final int initialHeight = row.getHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                int newHeight = (int) (initialHeight * (1 - interpolatedTime));
                if (newHeight > 0) {
                    row.getLayoutParams().height = newHeight;
                    row.requestLayout();
                }
            }
        };
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                row.getLayoutParams().height = initialHeight;
                row.requestLayout();
                tasks.remove(position);
                ((TaskAdapter) listTasks.getAdapter()).notifyDataSetChanged();
            }
        });
        animation.setDuration(300);
        row.startAnimation(animation);
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

    @Override
    public void onResume() {
        super.onResume();
        tasks = Task.getTask(getActivity());
        ((TaskAdapter) listTasks.getAdapter()).notifyDataSetChanged();
    }
}
