package com.maxym.crudapplicationmvp.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;

import com.maxym.crudapplicationmvp.common.Constants;
import com.maxym.crudapplicationmvp.common.DBItem;
import com.maxym.crudapplicationmvp.database.DBHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UsersModel {
    private final DBHelper dbHelper;

    public UsersModel(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<DBItem> loadUsers() {
        LoadUsersTask loadTask = new LoadUsersTask();
        loadTask.execute();
        try {
            return loadTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser (ContentValues contentValues) {
        AddUsersTask addTask = new AddUsersTask();
        addTask.execute(contentValues);
    }

    public void clearAllUsers() {
        ClearAllTask clearAllTask = new ClearAllTask();
        clearAllTask.execute();
    }

    public void delUserByID(int id) {
        DelByIDTask delByIDTask = new DelByIDTask();
        delByIDTask.execute(id);
    }

    class LoadUsersTask extends AsyncTask<Void, Void, ArrayList<DBItem>> {

        @Override
        protected ArrayList<DBItem> doInBackground(Void... voids) {
            ArrayList<DBItem> recordsList = new ArrayList<>();
            Cursor cursor = dbHelper.getReadableDatabase().query(Constants.TABLE_NAME, null,
                    null, null, null, null, null);
            while(cursor.moveToNext()) {
                DBItem item = new DBItem();
                item.setName(cursor.getString(cursor.getColumnIndex(Constants.NAME)));
                item.setEmail(cursor.getString(cursor.getColumnIndex(Constants.EMAIL)));
                recordsList.add(item);
            }
            cursor.close();
            return recordsList;
        }
    }

    class AddUsersTask extends AsyncTask<ContentValues, Void, Void> {

        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            ContentValues cvItem = contentValues[0];
            dbHelper.getWritableDatabase().insert(Constants.TABLE_NAME, null, cvItem);
            return null;
        }
    }

    class ClearAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            dbHelper.getWritableDatabase().execSQL("DELETE FROM " + Constants.TABLE_NAME);
            return null;
        }
    }

    class DelByIDTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            dbHelper.getWritableDatabase().execSQL("DELETE FROM " + Constants.TABLE_NAME +
                    " WHERE " + Constants._ID + " = " + integers[0]);
            return null;
        }
    }
}
