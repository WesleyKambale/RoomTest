package ug.global.roomtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ug.global.roomtest.objects.User;

public class AppDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "user_database";
    public static final String USERS_TABLE = "users";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";

    public AppDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        You can run this method for all the tables you want to create in your
//        Database eg users, products, staff, etc
        db.execSQL("CREATE TABLE " + USERS_TABLE + " (" +
                "" + USER_ID + " INT PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "" + USER_EMAIL + " VARCHAR(29)," +
                "" + USER_NAME + " VARCHAR(12)" +
                ");");
    }

    public boolean createUser(String name, String email) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_NAME, name);
        long columnId = writableDatabase.insert(USERS_TABLE, null
                , contentValues);
        return columnId != -1;
    }

    public ArrayList<User> getAllUsers() {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + USERS_TABLE,
                null
        );
        ArrayList<User> users = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String user_id = cursor.getString(cursor.getColumnIndex(USER_ID));
                String user_name = cursor.getString(cursor.getColumnIndex(USER_NAME));
                String user_email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
                users.add(new User(user_name, user_email, Integer.parseInt(user_id)));
            }
        }
        return users;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onCreate(db);

//        db.execSQL("ALTER TABLE users ADD ");
    }
}
