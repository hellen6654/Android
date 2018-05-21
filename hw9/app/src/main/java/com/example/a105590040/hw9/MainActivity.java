package com.example.a105590040.hw9;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
    private static final int MENU_MUSIC = Menu.FIRST,
                             MENU_PLAY = Menu.FIRST+1,
                             MENU_STOP_PLAYING_MUSIC = Menu.FIRST+2,
                             MENU_ABOUT = Menu.FIRST+3,
                             MENU_EXIT = Menu.FIRST+4;
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
        registerForContextMenu(findViewById(R.id.ass));
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item)||onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0,MENU_MUSIC,0,"bg_music")
                    .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0,MENU_PLAY,0,"play");
        subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,"stop");
        menu.add(0,MENU_ABOUT,1,"about")
                .setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_EXIT,2,"exit")
                .setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case MENU_PLAY:
                Intent it = new Intent(MainActivity.this,MediaService.class);
                startService(it);
                return true;
            case MENU_STOP_PLAYING_MUSIC:
                it = new Intent(MainActivity.this,MediaService.class);
                stopService(it);
                return true;
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("about")
                        .setMessage("menu_demonstration_programming")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return true;
            case MENU_EXIT:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
