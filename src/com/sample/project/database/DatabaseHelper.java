package com.sample.project.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "project";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "MESSAGES";
	public static final String COLUMN_ID = "MESSAGE_ID";
	public static final String COLUMN_TYPE = "MESSAGE_TYPE";
	public static final String COLUMN_DATA = "MESSAGE_DATA";
	public static final String COLUMN_TIME = "MESSAGE_TIMESTAMP";

	private String json = "{\"chats\": [{\"timestamp\": \"default\", \"msg_id\": \"1\", \"msg_data\": \"Hello, how are you ?\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"2\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"3\", \"msg_data\": \"How is weather ?\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"4\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"5\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}, {\"timestamp\": \"default\", \"msg_id\": \"6\", \"msg_data\": \"Tomorrow is sunday\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"7\", \"msg_data\": \"To define one such view, you need to specify it an Android Context. This is usually the Activity where the tabs will be displayed. Supposing that you initialize your tabs in an Activity, simply pass the Activity instance as a Context\", \"msg_type\": \"0\"}, {\"timestamp\": \"default\", \"msg_id\": \"8\", \"msg_data\": \"http://media.mediatemple.netdna-cdn.com/wp-content/uploads/2013/01/3.jpg\", \"msg_type\": \"1\"}]}";

	private static final String DATABASE_CREATE = "CREATE TABLE if not exists "
			+ TABLE_NAME + " (" + COLUMN_ID + " VARCHAR(20)  ," + COLUMN_DATA
			+ " TEXT ," + COLUMN_TYPE + " VARCHAR(2), " + COLUMN_TIME
			+ " TEXT);";

	private SQLiteDatabase database;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TAG", "onCreate called");
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 
        // Create tables again
        onCreate(db);}

	public void populateDB() throws JSONException {
		this.database = getWritableDatabase();
		JSONObject jsonOb = new JSONObject(json);
		JSONArray chats = jsonOb.getJSONArray("chats");
		for (int i = 0; i < chats.length(); i = i + 1) {
			JSONObject ob = chats.getJSONObject(i);
			ContentValues cv = new ContentValues();
			cv.put(COLUMN_DATA, ob.getString("msg_data"));
			cv.put(COLUMN_ID, ob.getString("msg_id"));
			cv.put(COLUMN_TIME, ob.getString("timestamp"));
			cv.put(COLUMN_TYPE, ob.getString("msg_type"));
			Log.d("TAG",cv.toString());
			database.insert(TABLE_NAME, null, cv);
		}
		database.close();
	}

	public Cursor getData() {
		Log.d("TAG", "message");
		this.database = getWritableDatabase();
		Cursor cursor = null;
		String rawQuery = "SELECT * FROM " + TABLE_NAME;
		cursor = database.rawQuery(rawQuery, null);
		Log.d("TAG", "" + cursor.getCount());
		return cursor;
	}
}
