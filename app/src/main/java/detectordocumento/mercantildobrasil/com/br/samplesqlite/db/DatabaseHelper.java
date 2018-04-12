package detectordocumento.mercantildobrasil.com.br.samplesqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import detectordocumento.mercantildobrasil.com.br.samplesqlite.model.User;

/**
 * Created by andre.coelho on 12/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "user_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        onCreate(db);
    }

    public long insertUser(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(User.COLUMN_NAME, name);

        long id = db.insert(User.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public User getUser(long id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                cursor.getString(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));

        cursor.close();

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex(User.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));

                users.add(user);
            } while (cursor.moveToNext());
        }

        db.close();

        return users;
    }
}
