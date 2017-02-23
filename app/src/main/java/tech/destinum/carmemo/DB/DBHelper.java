package tech.destinum.carmemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "carmemo";
    private final static int DB_VERSION = 1;

    public final static String TABLE_USERS = "users";
    public final static String USERS_COLUMN_ID = "_id";
    public final static String USERS_COLUMN_NAME = "name";
    public final static String USERS_COLUMN_DESCRIPTION = "description";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
