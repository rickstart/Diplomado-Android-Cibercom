package com.mobintum.todolist.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mobintum.todolist.R;
import com.mobintum.todolist.database.AndroidDatabaseManager;
import com.mobintum.todolist.fragments.AddTaskFragment;
import com.mobintum.todolist.fragments.ListTasksFragment;
import com.mobintum.todolist.models.Task;


public class MainActivity extends ActionBarActivity
    implements AddTaskFragment.OnFragmentInteractionListener,
                ListTasksFragment.OnFragmentInteractionListener{

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        setContentView(R.layout.activity_main);

        fm.beginTransaction().replace(R.id.container, new ListTasksFragment()).commit();





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem itemAdd = menu.findItem(R.id.menu_add);
        itemAdd.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                fm.beginTransaction().addToBackStack(null)
                        .replace(R.id.container, new AddTaskFragment())
                        .commit();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()){

            case R.menu.menu_add_task:

                break;

            case R.id.action_settings:
                Intent intent = new Intent(this, AndroidDatabaseManager.class);
                startActivity(intent);
                break;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateTask() {

    }

    @Override
    public void onTaskSelected(Task Task) {

    }
}
