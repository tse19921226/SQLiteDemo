package com.example.tse_chen.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edit_Name,edit_Age,edit_ID;
    private Button btn_Save,btn_Load;
    private TextView text_Name,text_Age;
    private MyDBHelper myDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_Name = (EditText) findViewById(R.id.edit_Name);
        edit_Age = (EditText) findViewById(R.id.edit_Age);
        edit_ID = (EditText) findViewById(R.id.edit_id);
        btn_Save = (Button) findViewById(R.id.btn_Save);
        btn_Load = (Button) findViewById(R.id.btn_Load);
        text_Name = (TextView) findViewById(R.id.text_Name);
        text_Age = (TextView) findViewById(R.id.text_Age);
        myDBHelper = new MyDBHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_Save.setOnClickListener(onClick);
        btn_Load.setOnClickListener(onClick);
    }

    public void add(String Name, Integer Age){
        SQLiteDatabase database = myDBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name",Name);
        cv.put("Age",Age);
        //database.update(Table_Name,cv,Key_ID,null);
        database.insert("Test",null,cv);

    }


    public Cursor select()
    {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query("Test", null, null, null, null, null, null);
        return cursor;
    }


    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_Save:
                    String getName = edit_Name.getText().toString();
                    Integer getAge = Integer.valueOf(edit_Age.getText().toString());
                    myDBHelper.insert(getName,getAge);
                    break;
                case R.id.btn_Load:
                    Integer getID = Integer.valueOf(edit_ID.getText().toString());
                    Cursor c = myDBHelper.select();
                    c.moveToPosition(getID);
                    String Name = c.getString(c.getColumnIndex("Name"));
                    String Age = c.getString(c.getColumnIndex("Age"));
                    text_Name.setText(Name);
                    text_Age.setText(Age);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDBHelper.close();
    }

}
