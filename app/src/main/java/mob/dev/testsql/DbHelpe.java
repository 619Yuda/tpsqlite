package mob.dev.testsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by oussa on 07/12/2017.
 */

public class DbHelpe extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "List.db";
    public static abstract class Note implements BaseColumns {
        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_DATE = "date";
    }
    private static final String TEXT_TYPE = " TEXT";    private static final String DATE_TYPE = " INT";    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Note.TABLE_NAME + " (" +Note._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +Note.COLUMN_NAME_TEXT+ TEXT_TYPE + COMMA_SEP + Note.COLUMN_NAME_DATE+ DATE_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =  "DROP TABLE IF EXISTS " + Note.TABLE_NAME;

    public DbHelpe(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);    }
    @Override
    public void onCreate(SQLiteDatabase db) {        db.execSQL(SQL_CREATE_ENTRIES);    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        db.execSQL(SQL_DELETE_ENTRIES);        onCreate(db);    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {        onUpgrade(db, oldVersion, newVersion);    }


    public Cursor getList() {
        String[] projection = {
                Note._ID,
                Note.COLUMN_NAME_TEXT,
                Note.COLUMN_NAME_DATE};
        return this.getReadableDatabase().query(Note.TABLE_NAME , projection, null, null, null, null, Note.COLUMN_NAME_DATE+" DESC");    }

    public long insertRow(String text){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NAME_TEXT, text);
        values.put(Note.COLUMN_NAME_DATE, System.currentTimeMillis());

        return db.insert(Note.TABLE_NAME,null,values);
    }

    public boolean deleteTitle(Long id)
    {        SQLiteDatabase db = getWritableDatabase();
        return db.delete(Note.TABLE_NAME, Note._ID + "=" + id, null) > 0;
    }

}

