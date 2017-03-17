package com.example.administrator.myyinxiang.basedb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myyinxiang.BaseApplication;
import com.example.administrator.myyinxiang.model.MeassageModel;

import java.util.ArrayList;
import java.util.List;


public class DBManager {
    static private DBManager dbMgr = new DBManager();
    private DBHelper dbHelper;

    private DBManager() {
        dbHelper = DBHelper.getInstance(BaseApplication.getAppContext());
    }

    public static  DBManager getInstance() {
        if (dbMgr == null) {
            synchronized(DBManager.class){
                if (dbMgr==null){
                    dbMgr = new DBManager();
                }
            }

        }
        return dbMgr;
    }

    /**
     * 添加消息
     * @param meassageModelList
     */
    public void saveChatMsgList(List<MeassageModel> meassageModelList) {
        if (meassageModelList != null) {
            synchronized (DBManager.class) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db.isOpen()) {
                    db.delete(DBHelper.TABLE_MSG_CHAT, null, null);
                    for (MeassageModel msg : meassageModelList) {
                        ContentValues values = new ContentValues();
                        values.put(ChatMessgeDao.COLUMN_NAME_ID, msg.getId());
                        if (msg.getUserId() != null) {
                            values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
                            values.put(ChatMessgeDao.COLUMN_NAME_TO_USER_ID, msg.getToUserId());
                            values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
                            values.put(ChatMessgeDao.COLUMN_NAME_TO_IP, msg.getToIp());
                            values.put(ChatMessgeDao.COLUMN_NAME_TO_PORT, msg.getToPort());
                            values.put(ChatMessgeDao.COLUMN_NAME_IS_SELF, msg.isSelf());
                            values.put(ChatMessgeDao.COLUMN_NAME_CURRENTTIME, msg.getCurrentTime());
                            values.put(ChatMessgeDao.COLUMN_NAME_HEAD_URL, msg.getHeadUrl());
                            values.put(ChatMessgeDao.COLUMN_NAME_MSG_TYPE, msg.getMsgType());
                            values.put(ChatMessgeDao.COLUMN_NAME_MSG_CONTENT, msg.getMsgContent());
                            values.put(ChatMessgeDao.COLUMN_NAME_FILE_SECRET, msg.getFileSecret());
                            values.put(ChatMessgeDao.COLUMN_NAME_LOCAL_URL, msg.getLocalUrl());
                            values.put(ChatMessgeDao.COLUMN_NAME_TIME_LONG, msg.getTimeLong());
                            values.put(ChatMessgeDao.COLUMN_NAME_IS_READ, msg.isRead());
                            values.put(ChatMessgeDao.COLUMN_NAME_IS_REACH, msg.isReach());
                            values.put(ChatMessgeDao.COLUMN_NAME_TYPE, msg.getType());
                            db.replace(DBHelper.TABLE_MSG_CHAT, null, values);
                        }

                    }
                }
            }
        }

    }

//    /**
//     * get contact list
//     *
//     * @return
//     */
//    synchronized public Map<String, EaseUser> getContactList() {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Map<String, EaseUser> users = new Hashtable<String, EaseUser>();
//        if (db.isOpen()) {
//            Cursor cursor = db.rawQuery("select * from " + UserDao.TABLE_NAME /* + " desc" */, null);
//            while (cursor.moveToNext()) {
//                String username = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_ID));
//                String nick = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_NICK));
//                String avatar = cursor.getString(cursor.getColumnIndex(UserDao.COLUMN_NAME_AVATAR));
//                EaseUser user = new EaseUser(username);
//                user.setNick(nick);
//                user.setAvatar(avatar);
//                if (username.equals(Constant.NEW_FRIENDS_USERNAME) || username.equals(Constant.GROUP_USERNAME)
//                        || username.equals(Constant.CHAT_ROOM) || username.equals(Constant.CHAT_ROBOT)) {
//                    user.setInitialLetter("");
//                } else {
//                    EaseCommonUtils.setUserInitialLetter(user);
//                }
//                users.put(username, user);
//            }
//            cursor.close();
//        }
//        return users;
//    }






//    public void setDisabledIds(List<String> ids) {
//        setList(UserDao.COLUMN_NAME_DISABLED_IDS, ids);
//    }
//
//    public List<String> getDisabledIds() {
//        return getList(UserDao.COLUMN_NAME_DISABLED_IDS);
//    }
//
//    synchronized private void setList(String column, List<String> strList) {
//        StringBuilder strBuilder = new StringBuilder();
//
//        for (String hxid : strList) {
//            strBuilder.append(hxid).append("$");
//        }
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        if (db.isOpen()) {
//            ContentValues values = new ContentValues();
//            values.put(column, strBuilder.toString());
//
//            db.update(UserDao.PREF_TABLE_NAME, values, null, null);
//        }
//    }

//
//synchronized private List<String> getList(String column) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select " + column + " from " + UserDao.PREF_TABLE_NAME, null);
//        if (!cursor.moveToFirst()) {
//            cursor.close();
//            return null;
//        }
//
//        String strVal = cursor.getString(0);
//        if (strVal == null || strVal.equals("")) {
//            return null;
//        }
//
//        cursor.close();
//
//        String[] array = strVal.split("$");
//
//        if (array != null && array.length > 0) {
//            List<String> list = new ArrayList<String>();
//            for (String str : array) {
//                list.add(str);
//            }
//
//            return list;
//        }
//
//        return null;
//    }


    /**
     * get messges
     *
     * @return
     */
     public List<MeassageModel> getMessagesList(String from, String to){
      synchronized (DBManager.class){
          SQLiteDatabase db = dbHelper.getReadableDatabase();
          List<MeassageModel> msgs = new ArrayList<MeassageModel>();
          if (db.isOpen()) {
              Cursor cursor = db.rawQuery("select * from "+DBHelper.TABLE_MSG_CHAT+" where "+ChatMessgeDao.COLUMN_NAME_USER_ID+"=?"+"and "+ChatMessgeDao.COLUMN_NAME_TO_USER_ID+"=?"+"order by "+ChatMessgeDao.COLUMN_NAME_CURRENTTIME+" desc", new String[]{from.toString(),to.toString()});
              while (cursor.moveToNext()) {
                  MeassageModel msg = new MeassageModel();
                  String id = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_ID));
                  String userId = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_USER_ID));
                  String toUserId = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_TO_USER_ID));
                  String toIp = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_TO_IP));
                  int toPort = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_TO_PORT));
                  int isSelf = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_IS_SELF));
                  String currentTime = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_CURRENTTIME));
                  String headUrl = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_HEAD_URL));
                  int msgType = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_MSG_TYPE));
                  String msgContent = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_MSG_CONTENT));
                  String fileSecret = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_FILE_SECRET));
                  String localUrl = cursor.getString(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_LOCAL_URL));
                  int timeLong = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_TIME_LONG));
                  int isRead = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_IS_READ));
                  int isReach = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_IS_REACH));
                  int type = cursor.getInt(cursor.getColumnIndex(ChatMessgeDao.COLUMN_NAME_TYPE));
                  msg.setId(id);
                  msg.setUserId(userId);
                  msg.setToUserId(toUserId);
                  msg.setToIp(toIp);
                  msg.setToPort(toPort);
                  msg.setSelf(isSelf==1?true:false);
                  msg.setCurrentTime(currentTime);
                  msg.setHeadUrl(headUrl);
                  msg.setMsgType(msgType);
                  msg.setMsgContent(msgContent);
                  msg.setFileSecret(fileSecret);
                  msg.setLocalUrl(localUrl);
                  msg.setTimeLong(timeLong);
                  msg.setRead(isRead==1?true:false);
                  msg.setReach(isReach==1?true:false);
                  msg.setType(type);
                  msgs.add(msg);
              }
              cursor.close();
          }
          return msgs;
      }
      }


    /**
     * save aChatMsg
     *
     * @param msg
     * @return return cursor of the message
     */
    public Integer saveChatMsg(MeassageModel msg) {
    synchronized (DBManager.class){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id = -1;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
            values.put(ChatMessgeDao.COLUMN_NAME_TO_USER_ID, msg.getToUserId());
            values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
            values.put(ChatMessgeDao.COLUMN_NAME_TO_IP, msg.getToIp());
            values.put(ChatMessgeDao.COLUMN_NAME_TO_PORT, msg.getToPort());
            values.put(ChatMessgeDao.COLUMN_NAME_IS_SELF, msg.isSelf());
            values.put(ChatMessgeDao.COLUMN_NAME_CURRENTTIME, msg.getCurrentTime());
            values.put(ChatMessgeDao.COLUMN_NAME_HEAD_URL, msg.getHeadUrl());
            values.put(ChatMessgeDao.COLUMN_NAME_MSG_TYPE, msg.getMsgType());
            values.put(ChatMessgeDao.COLUMN_NAME_MSG_CONTENT, msg.getMsgContent());
            values.put(ChatMessgeDao.COLUMN_NAME_FILE_SECRET, msg.getFileSecret());
            values.put(ChatMessgeDao.COLUMN_NAME_LOCAL_URL, msg.getLocalUrl());
            values.put(ChatMessgeDao.COLUMN_NAME_TIME_LONG, msg.getTimeLong());
            values.put(ChatMessgeDao.COLUMN_NAME_IS_READ, msg.isRead());
            values.put(ChatMessgeDao.COLUMN_NAME_IS_REACH, msg.isReach());
            values.put(ChatMessgeDao.COLUMN_NAME_TYPE, msg.getType());
            db.insert(DBHelper.TABLE_MSG_CHAT, null, values);

            Cursor cursor = db.rawQuery("select last_insert_rowid() from " + DBHelper.TABLE_MSG_CHAT, null);
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }

            cursor.close();
        }
        return id;
    }
    }




    /**
     * 更新
     * @param msg
     */
    public void updateChatMsg(MeassageModel msg) {

        synchronized(DBManager.class){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db.isOpen()) {
                ContentValues values = new ContentValues();
                values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
                values.put(ChatMessgeDao.COLUMN_NAME_TO_USER_ID, msg.getToUserId());
                values.put(ChatMessgeDao.COLUMN_NAME_USER_ID, msg.getUserId());
                values.put(ChatMessgeDao.COLUMN_NAME_TO_IP, msg.getToIp());
                values.put(ChatMessgeDao.COLUMN_NAME_TO_PORT, msg.getToPort());
                values.put(ChatMessgeDao.COLUMN_NAME_IS_SELF, msg.isSelf());
                values.put(ChatMessgeDao.COLUMN_NAME_CURRENTTIME, msg.getCurrentTime());
                values.put(ChatMessgeDao.COLUMN_NAME_HEAD_URL, msg.getHeadUrl());
                values.put(ChatMessgeDao.COLUMN_NAME_MSG_TYPE, msg.getMsgType());
                values.put(ChatMessgeDao.COLUMN_NAME_MSG_CONTENT, msg.getMsgContent());
                values.put(ChatMessgeDao.COLUMN_NAME_FILE_SECRET, msg.getFileSecret());
                values.put(ChatMessgeDao.COLUMN_NAME_LOCAL_URL, msg.getLocalUrl());
                values.put(ChatMessgeDao.COLUMN_NAME_TIME_LONG, msg.getTimeLong());
                values.put(ChatMessgeDao.COLUMN_NAME_IS_READ, msg.isRead());
                values.put(ChatMessgeDao.COLUMN_NAME_IS_REACH, msg.isReach());
                values.put(ChatMessgeDao.COLUMN_NAME_TYPE, msg.getType());
                db.update(DBHelper.TABLE_MSG_CHAT, values, ChatMessgeDao.COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(msg.getId())});
            }
        }

    }
    /**
     * delete ChatMsg
     *
     * @param from
     */
     public void deleteChatMsg(String from) {
         synchronized(DBManager.class){
             SQLiteDatabase db = dbHelper.getWritableDatabase();
             if (db.isOpen()) {
                 db.delete(DBHelper.TABLE_MSG_CHAT, ChatMessgeDao.COLUMN_NAME_ID + " = ?", new String[]{from});
             }
         }

    }

     public void closeDB() {
         synchronized (DBManager.class){
             if (dbHelper != null) {
                 dbHelper.closeDB();
             }
             dbMgr = null;
         }

    }



}
