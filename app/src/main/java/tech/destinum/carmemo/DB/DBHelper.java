package tech.destinum.carmemo.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import tech.destinum.carmemo.pojo.User;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "carmemo";
    private final static int DB_VERSION = 2;

    public final static String TABLE_USERS = "users";
    public final static String USERS_COLUMN_ID = "_id";
    public final static String USERS_COLUMN_NAME = "name";
    public final static String USERS_COLUMN_CELLPHONE = "cellphone";
    public final static String USERS_COLUMN_FIJO = "fijo";
    public final static String USERS_COLUMN_EMAIL = "email";
    public final static String USERS_COLUMN_SOAT = "soat";
    public final static String USERS_COLUMN_RTM = "rtm";
    public final static String USERS_COLUMN_STR = "str";
    public final static String USERS_COLUMN_SRC = "src";
    public final static String USERS_COLUMN_TO = "to";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = String.format("CREATE TABLE " + TABLE_USERS + "("
        + USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + USERS_COLUMN_NAME + " TEXT, "
        + USERS_COLUMN_CELLPHONE + " INTEGER, "
        + USERS_COLUMN_FIJO + " INTEGER, "
        + USERS_COLUMN_EMAIL + " TEXT, "
        + USERS_COLUMN_SOAT + " DATE, "
        + USERS_COLUMN_RTM + " DATE, "
        + USERS_COLUMN_STR + " DATE, "
        + USERS_COLUMN_SRC + " DATE, "
        + USERS_COLUMN_TO + " DATE)", TABLE_USERS, USERS_COLUMN_ID, USERS_COLUMN_NAME, USERS_COLUMN_CELLPHONE, USERS_COLUMN_FIJO,
                USERS_COLUMN_EMAIL, USERS_COLUMN_SOAT, USERS_COLUMN_RTM, USERS_COLUMN_STR, USERS_COLUMN_SRC, USERS_COLUMN_TO);

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // If you need to add a column
//        if (newVersion > oldVersion) {
//            db.execSQL("ALTER TABLE foo ADD COLUMN new_column INTEGER DEFAULT 0");
//        }
    }

    public void createNewUser(String name, int cell, int fijo, String email, String soat, String rtm, String str, String src, String to){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_NAME, name);
        values.put(USERS_COLUMN_CELLPHONE, cell);
        values.put(USERS_COLUMN_FIJO, fijo);
        values.put(USERS_COLUMN_EMAIL, email);
        values.put(USERS_COLUMN_SOAT, soat);
        values.put(USERS_COLUMN_RTM, rtm);
        values.put(USERS_COLUMN_STR, str);
        values.put(USERS_COLUMN_SRC, src);
        values.put(USERS_COLUMN_TO, to);
        db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void updateUser(long id, String name, int cell, int fijo, String email, String soat, String rtm, String str, String src, String to){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_COLUMN_ID, id);
        values.put(USERS_COLUMN_NAME, name);
        values.put(USERS_COLUMN_CELLPHONE, cell);
        values.put(USERS_COLUMN_FIJO, fijo);
        values.put(USERS_COLUMN_EMAIL, email);
        values.put(USERS_COLUMN_SOAT, soat);
        values.put(USERS_COLUMN_RTM, rtm);
        values.put(USERS_COLUMN_STR, str);
        values.put(USERS_COLUMN_SRC, src);
        values.put(USERS_COLUMN_TO, to);
        db.update(TABLE_USERS, values, USERS_COLUMN_ID + " = ?", new String[]{id + ""});
        db.close();

    }

    public void deleteUser(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, USERS_COLUMN_ID + " = ?", new String[]{id + ""});
        db.close();
    }

    public ArrayList<User> getUser(){
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int cell = cursor.getInt(cursor.getColumnIndex("cell"));
            int fijo = cursor.getInt(cursor.getColumnIndex("fijo"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String soat = cursor.getString(cursor.getColumnIndex("soat"));
            String rtm = cursor.getString(cursor.getColumnIndex("rtm"));
            String str = cursor.getString(cursor.getColumnIndex("str"));
            String src = cursor.getString(cursor.getColumnIndex("src"));
            String to = cursor.getString(cursor.getColumnIndex("to"));
            list.add(new User(name, cell, fijo, email, soat, rtm, str, src, to, id));
        }
        cursor.close();
        db.close();
        return list;
    }
}
