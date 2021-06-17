package com.maxym.crudapplicationmvp.presenter;

import android.content.ContentValues;

import com.maxym.crudapplicationmvp.UserContract;
import com.maxym.crudapplicationmvp.common.Constants;
import com.maxym.crudapplicationmvp.common.DBItem;
import com.maxym.crudapplicationmvp.database.DBHelper;
import com.maxym.crudapplicationmvp.model.UsersModel;
import com.maxym.crudapplicationmvp.view.UsersView;

import java.util.ArrayList;

public class BasePresenter implements UserContract {
    protected UsersView view;
    protected UsersModel model;
    DBHelper dbHelper;


    @Override
    public void attachViews(UsersView view) {
        this.view = view;
        dbHelper = new DBHelper(view);
        model = new UsersModel(dbHelper);
    }

    @Override
    public void detachViews() {
        model = null;
        view = null;
    }

    @Override
    public void addDataToDB() {
        DBItem item = view.getData();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, item.getName());
        contentValues.put(Constants.EMAIL, item.getEmail());
        model.addUser(contentValues);
    }

    public void delById(int id) {
        model.delUserByID(id);
        loadRecords();
    }

    @Override
    public void clearAllDB() {
        model.clearAllUsers();
    }



    @Override
    public void loadRecords() {
        view.showUsers(model.loadUsers());
    }
}
