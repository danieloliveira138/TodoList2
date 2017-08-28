package com.curso.todolist.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.curso.todolist.Model.Task;

import java.util.ArrayList;
import java.util.List;

import static com.curso.todolist.Persistence.DatabasePersistence.COLUMN_DATE;
import static com.curso.todolist.Persistence.DatabasePersistence.COLUMN_DONE;
import static com.curso.todolist.Persistence.DatabasePersistence.COLUMN_ID;
import static com.curso.todolist.Persistence.DatabasePersistence.COLUMN_RESUME;
import static com.curso.todolist.Persistence.DatabasePersistence.COLUMN_TITLE;

public class TaskRepository {
    private DatabasePersistence databasePersistence;

    public TaskRepository(Context ctx) {
        databasePersistence = new DatabasePersistence(ctx);
    }

    private long insert(Task task) {
        SQLiteDatabase database = databasePersistence.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, task.getTitle());
        contentValues.put(COLUMN_RESUME, task.getResume());
        contentValues.put(COLUMN_DATE, task.getDate());
        contentValues.put(COLUMN_DONE, task.getDone());
        long id = database.insert(DatabasePersistence.DATA_TABLE, null, contentValues);

        if (id != -1) {
            task.setId(id);
        }
        database.close();
        return id;
    }

    private int update(Task task) {
        SQLiteDatabase database = databasePersistence.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, task.getId());
        contentValues.put(COLUMN_TITLE, task.getTitle());
        contentValues.put(COLUMN_RESUME, task.getResume());
        contentValues.put(COLUMN_DATE, task.getDate());
        contentValues.put(COLUMN_DONE, task.getDone());

        int affectedLines = database.update(
                DatabasePersistence.DATA_TABLE,
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
        SQLiteDatabase database = databasePersistence.getWritableDatabase();
        int affectedLines = database.delete(
                DatabasePersistence.DATA_TABLE,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        database.close();
        return affectedLines;
    }

    public List<Task> searchTask(String filter) {
        SQLiteDatabase database = databasePersistence.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabasePersistence.DATA_TABLE;
        String[] argument = null;
        if (filter != null) {
            sql += " WHERE " + COLUMN_TITLE + " LIKE ? ";
            argument = new String[]{filter};
        }
        sql += " ORDER BY " + COLUMN_ID;
        Cursor cursor = database.rawQuery(sql, argument);
        cursor.moveToFirst();
        int indexId = cursor.getColumnIndex(COLUMN_ID);
        int indexTitle = cursor.getColumnIndex(COLUMN_TITLE);
        int indexResume = cursor.getColumnIndex(COLUMN_RESUME);
        int indexDate = cursor.getColumnIndex(COLUMN_DATE);
        int indexCheck = cursor.getColumnIndex(COLUMN_DONE);
        List<Task> tasks = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(indexId);
            String title = cursor.getString(indexTitle);
            String resume = cursor.getString(indexResume);
            String date = cursor.getString(indexDate);
            int done = cursor.getInt(indexCheck);
            Task task = new Task(id, ++i, title, resume, date, done);
            tasks.add(task);
        }
        cursor.close();
        database.close();
        return tasks;
    }
}