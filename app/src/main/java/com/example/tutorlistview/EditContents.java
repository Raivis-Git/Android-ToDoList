package com.example.tutorlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditContents extends AppCompatActivity {

    DatabaseHelper myDB;
    Button editBtn;
    Button deleteBtn;
    TextView actualText;
    TextView editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcontents_layout);

        editBtn = (Button) findViewById(R.id.editButton);
        deleteBtn = (Button) findViewById(R.id.deleteButton);
        actualText = (TextView) findViewById(R.id.actualText);
        editText = (TextView) findViewById(R.id.editText);
        myDB = new DatabaseHelper(this);

        final Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String text = getIntent().getStringExtra("Text");
            actualText.setText(text);
            editText.setText(text);
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.editRow(actualText.getText().toString(), editText.getText().toString());
                Toast.makeText(EditContents.this,
                        "Selected Item was edited " +
                                "From " + actualText.getText().toString() +
                                "To " + editText.getText().toString() + "!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditContents.this, MainActivity.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteRow(actualText.getText().toString());
                Toast.makeText(EditContents.this,
                        "Selected Item was Deleted!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditContents.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
