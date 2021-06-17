package com.maxym.crudapplicationmvp;

import com.maxym.crudapplicationmvp.common.DBItem;
import com.maxym.crudapplicationmvp.model.UsersModel;
import com.maxym.crudapplicationmvp.view.UsersView;

import java.util.ArrayList;

public interface UserContract {
    void attachViews(UsersView view);
    void detachViews();
    void loadRecords();
    void addDataToDB();
    void clearAllDB();
}
