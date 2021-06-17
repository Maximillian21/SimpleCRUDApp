package com.maxym.crudapplicationmvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maxym.crudapplicationmvp.R;
import com.maxym.crudapplicationmvp.common.DBItem;
import com.maxym.crudapplicationmvp.common.RecordAdapter;
import com.maxym.crudapplicationmvp.presenter.BasePresenter;

import java.util.ArrayList;

public class UsersView extends AppCompatActivity implements View.OnClickListener {

    private RecordAdapter recordAdapter;
    private BasePresenter presenter;

    Button btnAdd;
    Button btnClearAll;

    EditText etName;
    EditText etEmail;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter.loadRecords();
    }

    void initViews() {
        btnAdd = findViewById(R.id.btnAdd);
        btnClearAll = findViewById(R.id.btnClearAll);
        etName = findViewById(R.id.editTextTextName);
        etEmail = findViewById(R.id.editTextEmail);

        presenter = new BasePresenter();
        presenter.attachViews(this);

        recyclerView = findViewById(R.id.rv_persons);
        recordAdapter = new RecordAdapter(presenter);
        LinearLayoutManager linLayManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linLayManager);
        recyclerView.setAdapter(recordAdapter);

        btnAdd.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
    }

    public DBItem getData() {
        DBItem item = new DBItem();
        item.setName(etName.getText().toString());
        item.setEmail(etEmail.getText().toString());
        etEmail.setText("");
        etName.setText("");
        return item;
    }

    public void showUsers(ArrayList<DBItem> items) {
        recordAdapter.setData(items);
    }

    @Override
    public void onClick(View v) {
        Log.d("Btt", "Button clicked");
        switch (v.getId()) {
            case R.id.btnAdd:
                presenter.addDataToDB();
                break;
            case R.id.btnClearAll:
                presenter.clearAllDB();
                break;
        }
        presenter.loadRecords();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachViews();
    }
}