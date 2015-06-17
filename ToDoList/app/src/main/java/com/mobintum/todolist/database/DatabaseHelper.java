package com.mobintum.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rick on 17/06/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ToDoList.db";
    private static final Integer VER_1 = 1;
    private static final Integer DATABASE_VERSION = VER_1;
    private Context context;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Status (" +
                "    statusId integer NOT NULL  PRIMARY KEY," +
                "    status varchar(250) NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Task (" +
                "    taskId integer NOT NULL  PRIMARY KEY AUTOINCREMENT," +
                "    title varchar(250) NOT NULL," +
                "    priority integer NOT NULL," +
                "    description text," +
                "    fk_statusId integer NOT NULL," +
                "    termLimit datetime NOT NULL," +
                "    createdAt datetime NOT NULL," +
                "    updatedAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (fk_statusId) REFERENCES Status (statusId)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != DATABASE_VERSION){
            db.execSQL("DROP TABLE IF EXISTS Status");
            db.execSQL("DROP TABLE IF EXISTS Task");
        }
    }
}
