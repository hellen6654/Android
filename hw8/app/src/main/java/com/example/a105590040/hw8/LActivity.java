package com.example.a105590040.hw8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LActivity extends AppCompatActivity {
    private  ListView mll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);
        Bundle bundle=getIntent().getExtras();
        mll = (ListView)findViewById(R.id.ll);
        ArrayList<String> s = bundle.getStringArrayList("AS");

        ListAdapter adapter =new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,s);
        mll.setAdapter(adapter);
    }
}
