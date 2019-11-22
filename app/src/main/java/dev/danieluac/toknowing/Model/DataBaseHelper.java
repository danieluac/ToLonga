package dev.danieluac.toknowing.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "ToKnowing";
    protected final SQLiteDatabase Connection = this.getWritableDatabase();
    public DataBaseHelper( Context context) {
        super(context, DATABASE, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
