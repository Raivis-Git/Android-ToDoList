package com.example.tutorlistview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;
    ArrayList<String> theList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        final ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        final Bundle bundle = getIntent().getExtras();
        Cursor data;
        if(bundle != null) {
            String text = getIntent().getStringExtra("Text");
                data = myDB.getSearch(text);
        } else {
            data = myDB.getListContents();
        }

        if(data.getCount() == 0){
            Toast.makeText(ViewListContents.this,"The Database is empty...", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewListContents.this, EditContents.class);
                intent.putExtra("Text", theList.get(position));
                startActivity(intent);
            }
        });
    }

}
