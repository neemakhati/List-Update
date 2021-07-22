package com.example.addassign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView1;
    EditText inputText1;
    Button btnAdd, btnUpdate;

    ArrayList<String> books = new ArrayList<String>();
    ArrayAdapter myAdapter;

    Integer indexVal;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listview1);
        btnAdd = (Button) findViewById(R.id.button1);
        btnUpdate = (Button) findViewById(R.id.button2);
        inputText1 = (EditText) findViewById(R.id.editText);

        //setup ListView
        books.add("Hamlet");
        books.add("Golden Girl");

        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        listView1.setAdapter(myAdapter);

        //add Items
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal = inputText1.getText().toString();
                books.add(stringVal);
                myAdapter.notifyDataSetChanged();
                inputText1.setText("");
            }
        });

        //select Item
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString() + "has been selected";
                indexVal = position;
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

        //update Item
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringVal = inputText1.getText().toString();
                books.set(indexVal,stringVal);
                myAdapter.notifyDataSetChanged();
            }
        });

        //delete Item
        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item=parent.getItemAtPosition(position).toString()+"has been deleted";
                Toast.makeText(MainActivity.this,item,Toast.LENGTH_SHORT).show();
                books.remove(position);
                myAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}
