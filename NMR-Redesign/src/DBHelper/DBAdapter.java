package DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "Qitle";
	public static final String KEY_NOTES = "notes";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_DATE = "date";
	public static final String KEY_TIME = "time";
	public static final String KEY_TYPE = "type";

	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "bonagenda";
	private static final String DATABASE_TABLE_NOTES = "notes";
	private static final String DATABASE_TABLE_EVENTS = "events";
	private static final int DATABASE_VERSION = 1;

	// query to create note table if doesnt exist
	// columns: _id, title, notes
	private static final String DATABASE_CREATE_NOTES = "create table if not exists notes (_id integer primary key autoincrement, "
			+ "title VARCHAR not null, notes VARCHAR );";

	//query to create events table if doesnt exist
	//columns: _id, title, description, date, time, type
	private static final String DATABASE_CREATE_EVENTS = "create table if not exists events (_id integer primary key autoincrement, "
			+ "title VARCHAR not null, description VARCHAR, date VARCHAR, "
			+ "time VARCHAR, type VARCHAR)";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE_NOTES);
				db.execSQL(DATABASE_CREATE_EVENTS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS notes");
			db.execSQL("DROP TABLE IF EXISTS events");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a note into the database---
	public long insertNote(String title, String notes) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_NOTES, notes);
		return db.insert(DATABASE_TABLE_NOTES, null, initialValues);
	}

	// ---deletes a particular note---
	public boolean deleteNote(long rowId) {
		return db.delete(DATABASE_TABLE_NOTES, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ---retrieves all the notes---
	public Cursor getAllNotes() {
		return db.query(DATABASE_TABLE_NOTES, new String[] { KEY_ROWID,
				KEY_TITLE, KEY_NOTES }, null, null, null, null, null);
	}

	// ---retrieves a particular note---
	public Cursor getNote(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_NOTES, new String[] {
				KEY_ROWID, KEY_TITLE, KEY_NOTES }, KEY_ROWID + "=" + rowId,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---updates a note---
	public boolean updateNote(long rowId, String title, String notes) {
		ContentValues args = new ContentValues();
		args.put(KEY_TITLE, title);
		args.put(KEY_NOTES, notes);
		return db.update(DATABASE_TABLE_NOTES, args, KEY_ROWID + "=" + rowId,
				null) > 0;
	}

	public CharSequence getTitle(Cursor c) {
		return c.getString(1);
	}

	// ---inserts an event into database---
	public long insertEvent(String title, String date, String description,
			String time, String type) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_TYPE, type);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_DESCRIPTION, description);
		initialValues.put(KEY_TIME, time);
		return db.insert(DATABASE_TABLE_EVENTS, null, initialValues);
	}

	// --deletes a particular event---
	public boolean deleteEvent(long rowId) {
		return db.delete(DATABASE_TABLE_EVENTS, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// --retrieves all events---
	public Cursor getAllEvents() {
		return db.query(DATABASE_TABLE_EVENTS, new String[] { KEY_ROWID,
				KEY_TITLE, KEY_NOTES, KEY_DESCRIPTION, KEY_TIME, KEY_TYPE },
				null, null, null, null, null);
	}
	
	//--retrieves days with events--
		public Cursor getDaysEvents() {
			return db.query(DATABASE_TABLE_EVENTS, new String[] { KEY_DATE },
					null, null, null, null, null);
		}
	
	// --retrieves all events---
		public Cursor getDayEvents(String date) {
			return db.query(DATABASE_TABLE_EVENTS, new String[] { KEY_ROWID,
					KEY_DATE, KEY_TITLE, KEY_TIME,  KEY_TYPE, KEY_DESCRIPTION},
					KEY_DATE + "=" + "\"" +  date.toString() + "\"", null, null, null, null);
		}
	

	// ---retrieves a particular event---
	public Cursor getEvent(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_EVENTS, new String[] {
				KEY_ROWID, KEY_DATE, KEY_TITLE, KEY_TIME, KEY_TYPE, KEY_DESCRIPTION}, KEY_ROWID + "=" + rowId,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	// ---updates an event---
	public boolean updateEvent(long rowId, String date, String title, String time, String type, String description) {
		ContentValues args = new ContentValues();
		args.put(KEY_TITLE, title);
		args.put(KEY_TYPE, type);
		args.put(KEY_DATE, date);
		args.put(KEY_DESCRIPTION, description);
		args.put(KEY_TIME, time);
		return db.update(DATABASE_TABLE_EVENTS, args, KEY_ROWID + "=" + rowId,
				null) > 0;
	}
}
