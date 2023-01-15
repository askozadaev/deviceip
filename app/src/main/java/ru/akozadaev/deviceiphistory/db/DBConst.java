package ru.akozadaev.deviceiphistory.db;


public final class DBConst {
    public static final String TABLE_NAME = "history_addresses";
    public static final String _ID = "_id";
    public static final String COLUMN_NAME_ADDRESS = "address";
    public static final String DB_NAME = "device_ip.db";
    public static final int DB_VERSION = 1;

    public static final String CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;
    public static final String ORDER_ADDRESSES = _ID + " DESC";
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME_ADDRESS + " TEXT)";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
