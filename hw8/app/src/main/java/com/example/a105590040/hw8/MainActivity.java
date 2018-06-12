package com.example.a105590040.hw8;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner mspnItem;
    private TextView mtxtDate;
    private EditText meditSpend;
    private Button mbtninput;
    private Button mbtnRecord;
    private DatePicker mdatePicker;
    private ArrayList<String> information=new ArrayList<String>();
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mspnItem = (Spinner)findViewById(R.id.spnItem);
        mtxtDate = (TextView)findViewById(R.id.txtDate);
        meditSpend=(EditText)findViewById(R.id.editSpend);
        mbtninput=(Button)findViewById(R.id.btnInput);
        mbtnRecord=(Button)findViewById(R.id.btnRecord);
        mdatePicker=(DatePicker)findViewById(R.id.datePicker);
        mdatePicker.setOnDateChangedListener(dateChangedListener);
        mbtninput.setOnClickListener(btninputOnClick);
        mbtnRecord.setOnClickListener(btnRecordOnclick);
    }
    private Button.OnClickListener btninputOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),meditSpend.getText().toString(),Toast.LENGTH_SHORT).show();
            String buf= mtxtDate.getText().toString() + mspnItem.getSelectedItem().toString()
                    +meditSpend.getText().toString();
            information.add(buf);
        }
    };
    private Button.OnClickListener btnRecordOnclick = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {

            Intent it = new Intent();
            it.setClass(MainActivity.this,LActivity.class);
            Bundle b = new Bundle();
            b.putStringArrayList("AS",information);
            it.putExtras(b);
            startActivity(it);
        }
    };

    private DatePicker.OnDateChangedListener dateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mtxtDate.setText(Integer.toString(year)+'/'
                    +Integer.toString(monthOfYear+1)+'/'
                    +Integer.toString(dayOfMonth));
        }
    };
}
