package com.curso.todolist.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabasePersistence extends SQLiteOpenHelper {

    public static final String DATA_TABLE = "tb_tasks";
    public static final String COLUMN_ID = "cd_id";
    public static final String COLUMN_TITLE = "nm_title";
    public static final String COLUMN_RESUME = "nm_resume";
    public static final String COLUMN_DATE = "dt_create";
    public static final String COLUMN_DONE = "bol_done";
    private static final String DATA_BASE = "dbTask";
    private static final int DATA_BASE_VERSION = 15;
    private static final String LOG_MESSAGE_VERSION = "Versão do banco: ";

//    public DatabasePersistence(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DATA_BASE, null, DATA_BASE_VERSION);
//    }

    public DatabasePersistence(Context context) {
        super(context, DATA_BASE, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.e(LOG_MESSAGE_VERSION, String.format("Versão criação: %d. Versão upgrade: %d", oldVersion, newVersion), null);
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + DATA_TABLE + " ( " +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT NOT NULL, " +
                        COLUMN_RESUME + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_DONE + " INT " + " );"
        );
    }
}
