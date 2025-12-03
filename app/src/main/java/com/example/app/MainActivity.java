package com.example.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] values;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        values = new String[] { "Android", "iPhone", "WindowsMobile"};
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.listViewValue, values);
        listView.setAdapter(adapter);
    }
}