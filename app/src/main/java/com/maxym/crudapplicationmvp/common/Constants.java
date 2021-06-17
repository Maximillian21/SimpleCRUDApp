package com.maxym.crudapplicationmvp.common;

public class Constants {
    public static final String TABLE_NAME = "users";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," +
            EMAIL + " TEXT)";
    public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
