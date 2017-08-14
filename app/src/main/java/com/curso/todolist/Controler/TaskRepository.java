package com.curso.todolist.Controler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.curso.todolist.Model.DataBasePersistence;

import java.util.ArrayList;
import java.util.List;

import static com.curso.todolist.Model.DataBasePersistence.COLUMN_ID;
import static com.curso.todolist.Model.DataBasePersistence.COLUMN_RESUME;
import static com.curso.todolist.Model.DataBasePersistence.COLUMN_TITLE;


public class TaskRepository {
    private DataBasePersistence dataBasePersistence;

    public TaskRepository(Context ctx) {
        dataBasePersistence = new DataBasePersistence(ctx);
    }

    private long insert(Task task) {
        SQLiteDatabase database = dataBasePersistence.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, task.getTitle());
        contentValues.put(COLUMN_RESUME, task.getResume());
        long id = database.insert(DataBasePersistence.DATA_TABLE, null, contentValues);

        if (id != -1) {
            task.setId((int) id);
        }

        database.close();
        return id;
    }

    private int update(Task task) {
        SQLiteDatabase database = dataBasePersistence.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, task.getId());
        contentValues.put(COLUMN_TITLE, task.getTitle());
        contentValues.put(COLUMN_RESUME, task.getResume());

        int affectedLines = database.update(
                DataBasePersistence.DATA_TABLE,
                contentValues,
                COLUMN_ID + " ?",
                new String[]{String.valueOf(task.getId())}
        );
        database.close();
        return affectedLines;
    }

    public void Save(Task task) {
        if (task.getId() == 0) {
            insert(task);
        } else {
            update(task);
        }
    }

    public int delete(Task task) {
        SQLiteDatabase database = dataBasePersistence.getWritableDatabase();
        int affectedLines = database.delete(
                DataBasePersistence.DATA_TABLE,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        database.close();
        return affectedLines;
    }

    public List<Task> searchTask(String filter) {
        SQLiteDatabase database = dataBasePersistence.getReadableDatabase();
        String sql = "SELECT * FROM " + DataBasePersistence.DATA_TABLE;
        String[] argument = null;
        if (filter != null) {
            sql += " WHERE " + COLUMN_TITLE + " LIKE ? ";
            argument = new String[]{filter};
        }
        sql += " ORDER BY " + COLUMN_TITLE;
        Cursor cursor = database.rawQuery(sql, argument);
        cursor.moveToFirst();
        int indexId = cursor.getColumnIndex(COLUMN_ID);
        Log.i("cursor " + COLUMN_ID, "" + indexId, null);
        int indexTitle = cursor.getColumnIndex(COLUMN_TITLE);
        Log.i("cursor " + COLUMN_TITLE, "" + indexTitle, null);
        int indexResume = cursor.getColumnIndex(COLUMN_RESUME);
        Log.i("cursor " + COLUMN_RESUME, ":: " + indexResume, null);
        List<Task> tasks = new ArrayList<Task>();
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(indexId);
            String title = cursor.getString(indexTitle);
            String resume = cursor.getString(indexResume);
            Task task = new Task(id, ++i, title, resume);
            tasks.add(task);
        }
        cursor.close();
        database.close();
        return tasks;
    }
}
