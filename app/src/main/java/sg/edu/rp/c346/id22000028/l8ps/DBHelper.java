package sg.edu.rp.c346.id22000028.l8ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Start version with 1
    // increment by 1 whenever db schema changes.
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "Songs.db";

    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_TITLE = "title";

    private static final String COLUMN_SINGERS = "singers";

    private static final String COLUMN_YEAR = "year";

    private static final String COLUMN_STARS = "stars";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);
    }
    public void insertTask(String title, String singer, int year, String star){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_SINGERS, singer);
        values.put(COLUMN_SINGERS, year);
        values.put(COLUMN_SINGERS, star);
        // Insert the row into the TABLE_SONG
        db.insert(TABLE_SONG,null,values);
        // Close the database connection
        db.close();
    }
    public ArrayList<Songs> getSongsContent() {
        ArrayList<Songs> song= new ArrayList<Songs>();
        SQLiteDatabase db= this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor= db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id= cursor.getInt(0);
                String title= cursor.getString(1);
                String singer= cursor.getString(2);
                int year= cursor.getInt(3);
                int star= cursor.getInt(4);


                Songs newSong= new Songs(id, title, singer, year, star);
                Songs.add(newSong);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return song;
    }

    public ArrayList<Songs> getSongs() {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id= cursor.getInt(0);
                String title= cursor.getString(1);
                String singer= cursor.getString(2);
                int year= cursor.getInt(3);
                int star= cursor.getInt(4);

                Songs newSong= new Songs(id, title, singer, year, star);
                Songs.add(newSong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}




