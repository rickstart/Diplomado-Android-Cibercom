package com.mobintum.todolist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobintum.todolist.R;
import com.mobintum.todolist.models.Task;
import com.mobintum.todolist.util.DateUtil;

import java.util.ArrayList;

/**
 * Created by Rick on 01/07/15.
 */
public class TaskAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private LayoutInflater inflater;
    private ArrayList<Task> tasks;


    public TaskAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.resource = resource;
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.layoutPriority = (FrameLayout) convertView.findViewById(R.id.layoutPriority);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtCreateAt = (TextView) convertView.findViewById(R.id.txtCreateAt);
            holder.btnInfo = (ImageButton) convertView.findViewById(R.id.btnInfo);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        Task task = tasks.get(position);

        holder.txtTitle.setText(task.getTitle());
        holder.txtCreateAt.setText(DateUtil.DATE_FORMAT.format(task.getCreateAt()));

        switch (task.getPriority()){

            case 1:
                holder.layoutPriority.setBackgroundColor(context.getResources().getColor(R.color.color_low));
                break;

            case 2:
                holder.layoutPriority.setBackgroundColor(context.getResources().getColor(R.color.color_medium));
                break;

            case 3:
                holder.layoutPriority.setBackgroundColor(context.getResources().getColor(R.color.color_high));
                break;
        }





        return convertView;
    }

    class ViewHolder{
        FrameLayout layoutPriority;
        TextView txtTitle;
        TextView txtCreateAt;
        ImageButton btnInfo;
    }
}
