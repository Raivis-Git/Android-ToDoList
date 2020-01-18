package com.example.tutorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd;
    Button btnView;
    Button btnSearch;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.addButton);
        btnView = (Button) findViewById(R.id.viewContentButton);
        btnSearch = (Button) findViewById(R.id.searchContentButton);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String newEntry = editText.getText().toString();
               if(editText.length() != 0){
                   AddData(newEntry);
                   editText.setText("");
               }else{
                   Toast.makeText(MainActivity.this,"You must put something in the text field!", Toast.LENGTH_LONG).show();
               }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, ViewListContents.class);
              startActivity(intent);

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                    intent.putExtra("Text", editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
    public void AddData (String newEntry){
        boolean insertData = myDB.addData(newEntry);

        if(insertData){
            Toast.makeText(MainActivity.this,"Successfully Entered Data!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"Something Went Wrong!", Toast.LENGTH_LONG).show();
        }
    }
}
