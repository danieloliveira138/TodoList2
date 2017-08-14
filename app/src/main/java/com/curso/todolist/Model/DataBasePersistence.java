package com.curso.todolist.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBasePersistence extends SQLiteOpenHelper {

    public static final String DATA_TABLE = " tb_tasklist ";
    public static final String COLUMN_ID = "tb_id";
    public static final String COLUMN_TITLE = "nm_title";
    public static final String COLUMN_RESUME = "nm_resume";
    private static final String DATA_BASE = "dbTask";
    private static final int DATA_BASE_VERSION = 1;

    public DataBasePersistence(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATA_BASE, null, DATA_BASE_VERSION);
    }

    public DataBasePersistence(Context context) {
        super(context, DATA_BASE, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("" +
                "CREATE TABLE IF NOT EXISTS" + DATA_TABLE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_RESUME + " TEXT " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
