package com.example.administrator.myyinxiang.basedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.myyinxiang.BaseApplication;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "chat_wei.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_MSG_CHAT = "msg_chat_table";
    private static DBHelper instance;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableChatMsg(db);
    }

    private static final String CHAT_MESSAGE_TABLE_CREATE =
            "create table if not exists " + TABLE_MSG_CHAT
                    + " ( "
                    + ChatMessgeDao.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ChatMessgeDao.COLUMN_NAME_USER_ID + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_TO_USER_ID + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_TO_IP + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_TO_PORT + " INTEGER, "
                    + ChatMessgeDao.COLUMN_NAME_IS_SELF + " INTEGER, "
                    + ChatMessgeDao.COLUMN_NAME_CURRENTTIME + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_HEAD_URL + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_MSG_TYPE + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_MSG_CONTENT + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_FILE_SECRET + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_LOCAL_URL + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_TIME_LONG + " TEXT, "
                    + ChatMessgeDao.COLUMN_NAME_IS_READ + " INTEGER, "
                    + ChatMessgeDao.COLUMN_NAME_IS_REACH + " INTEGER, "
                    + ChatMessgeDao.COLUMN_NAME_TYPE + " INTEGER); ";

    private void createTableChatMsg(SQLiteDatabase db) {
        db.execSQL(CHAT_MESSAGE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static DBHelper getInstance(Context appContext) {
        if (instance == null) {
            instance = new DBHelper(BaseApplication.getAppContext());
        }
        return instance;
    }
    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
