package com.example.a06_sqlite.placeholder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Handler extends SQLiteOpenHelper implements DB_Interface {

    // If you change the database schema, you must increment the database version.
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "CarModels.db";  // <RS> Car Models DB
    private static final String TABLE_NAME = "characters2";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String _ID = "_ID";
    private static final String _COL_1 = "name_col";
    private static final String _COL_2 = "lvl_col";
    private static final String _COL_3 = "race_col";
    private static final String _COL_4 = "class_col";
    private static final String _COL_5 = "health_col";
    private static final String _COL_6 = "armour_col";
    private static final String _COL_7 = "str_col";
    private static final String _COL_8 = "dex_col";
    private static final String _COL_9 = "con_col";
    private static final String _COL_10 = "int_col";
    private static final String _COL_11 = "wis_col";
    private static final String _COL_12 = "cha_col";
    private static final String _LAST_ACCESSED = "last_accessed";
    private static final String _ACCESS_COUNT = "access_count";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until java.lang.String one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DB_Handler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.v("DB_Handler", "Constructor");

        // Add some sample items.
        if (this.count() > 1) {
            Log.v("DB_Handler", "already rows in DB");

        } else {
            Log.v("DB_Handler", "no rows in DB...add some");
            Character a = new Character(1, 0, "Karn Axecleave", 3, "Half-Orc", "Barbarian", 28, 16, 17, 12, 14, 8, 10, 11);
            this.save(a);
            Character b = new Character(2, 0, "Sylvana Oakheart", 4, "Elf", "Druid", 22, 14, 12, 14, 11, 12, 16, 10);
            this.save(b);
            Character c = new Character(3, 0, "Constance Coalbreath", 2, "Kobold", "Sorceror", 12, 13, 8, 12, 10, 12, 10, 18);
            this.save(c);
            Character d = new Character(4, 0, "Fillbally Furface", 5, "Gnoll", "Fighter", 35, 18, 18, 12, 14, 8, 8, 10);
            this.save(d);
            Character e = new Character(5, 0, "Aislinn Frostsong", 4, "Human", "Bard", 20, 14, 12, 14, 12, 10, 12, 16);
            this.save(e);
            Character f = new Character(6, 0, "Klang Oilslick", 3, "Gnome", "Rogue", 18, 18, 12, 18, 12, 10, 12, 8);
            this.save(f);
        }
    }

    @Override
    public int count() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        Log.v("DB_Handler", "getCount=" + cnt);
        return cnt;
    }

    @Override
    public int save(Character character) {
        //String command = "INSERT INTO CarModels(str_col,num_col) VALUES('" + carModel.getModelName() + "', " + carModel.getModelNumber() + ")";

        Log.v("DB_Handler", "add=>  " + character.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(_ACCESS_COUNT, character.getAccessCount());
        values.put(_COL_1, character.getName());
        values.put(_COL_2, character.getLevel());
        values.put(_COL_3, character.getRace());
        values.put(_COL_4, character.getCharClass());
        values.put(_COL_5, character.getHealth());
        values.put(_COL_6, character.getArmour());
        values.put(_COL_7, character.getStrength());
        values.put(_COL_8, character.getDexterity());
        values.put(_COL_9, character.getConstitution());
        values.put(_COL_10, character.getIntelligence());
        values.put(_COL_11, character.getWisdom());
        values.put(_COL_12, character.getCharisma());

        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();

        // debug output to see what we're doing
        dump();

        return 0;
    }

    @Override
    public int update(Character character) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<PlaceholderContent.PlaceholderItem> findAll() {

        List<PlaceholderContent.PlaceholderItem> temp = new ArrayList<PlaceholderContent.PlaceholderItem>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build and add it to list
        Character character = null;
        PlaceholderContent.PlaceholderItem item;
        if (cursor.moveToFirst()) {
            do {
                // This code puts a carModel object into the PlaceHolder for the fragment
                // if you had more columns in the DB, you'd format  them in the non-details
                // list here
                character = new Character(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),
                        cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8),
                        cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13));
                item = new PlaceholderContent.PlaceholderItem(
                        Long.toString(character.getId()),
                        character.getName(),
                        character.toString());
                temp.add(item);
            } while (cursor.moveToNext());
        }

        Log.v("DB_Handler", "findAll=> " + temp.toString());

        // return all
        return temp;
    }


    public Map<String, PlaceholderContent.PlaceholderItem> findAllDetails() {
        Map<String, PlaceholderContent.PlaceholderItem> temp = new HashMap<String, PlaceholderContent.PlaceholderItem>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);  //rawQuery returns cursor for results

        // 3. go over each row, build and add it to list
        Character character = null;
        PlaceholderContent.PlaceholderItem item = null;
        if (cursor.moveToFirst()) {          // moveToFirst returns false if no rows
            do {
                // This code puts a carModel object into the PlaceHolder for the fragment
                // if you had more columns in the DB, you'd format  them in the details
                // here
                character = new Character(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3),
                        cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8),
                        cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13));
                item = new PlaceholderContent.PlaceholderItem(
                        Long.toString(character.getId()),
                        character.getName(),
                        character.toString());
                temp.put(Long.toString(character.getId()), item);
            } while (cursor.moveToNext());
        }

        Log.v("DB_Handler", "findAllDetails " + temp.toString());

        return temp;
    }


    @Override
    public String getNameById(Long id) {
        return null;
    }

    // Dump the DB as a test
    private void dump() {
    }  // oops, never got around to this.

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String db_com = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                _ACCESS_COUNT + NUM_TYPE + COMMA_SEP +
                _COL_1 + TEXT_TYPE + COMMA_SEP +
                _COL_2 + TEXT_TYPE + COMMA_SEP +
                _COL_3 + TEXT_TYPE + COMMA_SEP +
                _COL_4 + NUM_TYPE + COMMA_SEP +
                _COL_5 + NUM_TYPE + COMMA_SEP +
                _COL_6 + NUM_TYPE + COMMA_SEP +
                _COL_7 + NUM_TYPE + COMMA_SEP +
                _COL_8 + NUM_TYPE + COMMA_SEP +
                _COL_9 + NUM_TYPE + COMMA_SEP +
                _COL_10 + NUM_TYPE + COMMA_SEP +
                _COL_11 + NUM_TYPE + COMMA_SEP +
                _COL_12 + NUM_TYPE + " )";
        Log.v("handleDB", "onCreate DB =" + db_com);
        sqLiteDatabase.execSQL(db_com);

        Log.v("DB_Handler", "onCreate DB called");
    }


    public void addDateCol() {
        SQLiteDatabase db = this.getWritableDatabase();
        String addQuery = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + _LAST_ACCESSED + " DATE";
        String addDefaults = "UPDATE " + TABLE_NAME + " SET " + _LAST_ACCESSED + " = datetime(\"2020-01-01 00:00:00.000\")";
        db.execSQL(addQuery);
        db.execSQL(addDefaults);
    }

    public boolean hasDates() {
        boolean hasDates = true;
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        cursor = db.rawQuery(query, null);
        if (cursor.getColumnIndex(_LAST_ACCESSED) == -1 ) {
            hasDates = false;
        }
        return hasDates;
    }
    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param sqLiteDatabase The database.
     * @param oldVersion     The old database version.
     * @param newVersion     The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.v("handleDB", "onUpgrade: Old=" + oldVersion + " New=" + newVersion);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

        Log.v("DB_Handler", "onUpgrade DB called");
    }

    public void updateCounter(Integer id) {
        Log.v("DBHandler", "Character " + id + " was accessed.");
        SQLiteDatabase db = this.getWritableDatabase();
        String setQuery = "UPDATE " + TABLE_NAME + " SET " + _ACCESS_COUNT + " = " + _ACCESS_COUNT + " + 1" +
                " WHERE " + _ID + " = " + id;
        Log.v("DB-Debug", setQuery);
        db.execSQL(setQuery);
    }

    public String getCounter(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String getQuery = "SELECT " + _ACCESS_COUNT + " FROM " + TABLE_NAME + " WHERE " + _ID + " = " + id;
        Cursor cursor = db.rawQuery(getQuery, null);
        String result = null;
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(_ACCESS_COUNT));
        } else {
            result = "ERROR: Cursor empty";
        }
        Log.v("DB-Debug", getQuery);

        return result;
    }

    public void updateDate(Integer id) {
        Log.v("DBHandler", "Character" + id + "last accessed date updated");
        SQLiteDatabase db = this.getWritableDatabase();
        String setQuery = "UPDATE " + TABLE_NAME + " SET " + _LAST_ACCESSED + " = datetime('now') WHERE " + _ID + " = " + id;
        Log.v("DB-Debug", setQuery);
        db.execSQL(setQuery);
    }

    public String getDates(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String getQuery = "SELECT " + _LAST_ACCESSED + " FROM " + TABLE_NAME + " WHERE " + _ID + " = " + id;
        Cursor cursor = db.rawQuery(getQuery, null);
        String result = null;
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(_LAST_ACCESSED));
        } else {
            result = "ERROR: Cursor empty";
        }
        Log.v("DB-Debug", getQuery);

        return result;
    }
}
