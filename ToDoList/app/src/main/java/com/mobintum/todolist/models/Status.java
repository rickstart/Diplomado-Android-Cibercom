package com.mobintum.todolist.models;

import android.content.ContentValues;
import android.content.Context;

import com.mobintum.todolist.database.DatabaseAdapter;

/**
 * Created by Rick on 17/06/15.
 */
public class Status {

    public static final String TABLE_NAME = "Status";
    public static final String STATUS_ID = "statusId";
    public static final String STATUS = "status";

    private int statusId;
    private String status;

    public Status(int statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static long insert(Context context, Status status){

        ContentValues cv = new ContentValues();
        cv.put(STATUS_ID, status.getStatusId());
        cv.put(STATUS, status.getStatus());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME,null,cv);
    }
}
