package net.awrp.mvvmdemo.persistence.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MVVMDemoSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mvvmdemo.db";
    private static final int DB_VERSION = 1;

    // Identifiers for database creation
    public static final String TABLE_NAME = "data";
    public static final String PRIMARY_KEY = "id";
    public static final String DATA = "data";

    // Initialising data for a new database
    private static final String INITIAL_DATA = "'Hello'";

    // SQL statements for setting up database
    private static final String CREATE_TABLE
        = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
        +  PRIMARY_KEY + " integer PRIMARY KEY,"
        +  DATA + " text NOT NULL"
        + " );";

    private static final String INITIALISE_DATA
        = "INSERT INTO " + TABLE_NAME + "(" + DATA + ") "
        + "VALUES(" + INITIAL_DATA + ")";

    public MVVMDemoSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM data", null);
        if (cursor.moveToFirst()) {
            long count = cursor.getLong(cursor.getColumnIndex("COUNT(*)"));
            if (count == 0) {
                db.execSQL(INITIALISE_DATA);
            }
        }
        else {
            db.execSQL(INITIALISE_DATA);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Read existing data
        String[] columns = new String[] {DATA};
        String selection =  PRIMARY_KEY + " = 1";
        Cursor cursor = db.query(TABLE_NAME, columns, selection, null, null, null, null, null);
        String data;
        if (cursor.moveToFirst()) {
            data = cursor.getString(cursor.getColumnIndex(DATA));
        }
        else {
            data = INITIAL_DATA;
        }

        // Recreate database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        // Update the new database with the old data
        String WRITE_DATA
            = "UPDATE " + TABLE_NAME + " "
            + "SET " + data + " "
            + "WHERE " + PRIMARY_KEY + " = 1";
        db.execSQL(WRITE_DATA);
    }
}
