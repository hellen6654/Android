package com.example.a105590040.hw7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private EditText medtCountSet;

    private EditText medtCountPlayerWin;

    private EditText medtCountComWin;

    private EditText medtCountDraw;
    private Button mbtnGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        medtCountComWin=(EditText)findViewById(R.id.edtCountComWin);
        medtCountSet=(EditText)findViewById(R.id.edtCountSet);
        medtCountPlayerWin=(EditText)findViewById(R.id.edtCountPlayerWin);
        medtCountDraw=(EditText)findViewById(R.id.edtCountDraw);
        mbtnGoBack=(Button) findViewById(R.id.btnGoBack);

        mbtnGoBack.setOnClickListener(GoBackOnClick);
        showResult();
    }
    private View.OnClickListener GoBackOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent it =new Intent();
//            it.setClass(ResultActivity.this,MainActivity.class);
//            startActivity(it);
            finish();
        }
    };
    private void showResult(){
        Bundle bundle=getIntent().getExtras();
        int icomwin=bundle.getInt("COMWIN");
        int iplayerwin=bundle.getInt("PLAYERWIN");
        int idraw=bundle.getInt("DRAW");
        int itotal=bundle.getInt("TOTAL");

        medtCountComWin.setText(Integer.toString(icomwin));
        medtCountDraw.setText(Integer.toString(idraw));
        medtCountPlayerWin.setText(Integer.toString(iplayerwin));
        medtCountSet.setText(Integer.toString(itotal));
    }
}
