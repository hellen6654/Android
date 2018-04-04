package edu.ntut.user.myhw4_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*private Spinner mSpnSex;
    private RadioGroup mRadGrp;
    private RadioButton mRadBtnAgeRange1;
    private RadioButton mRadBtnAgeRange2;
    private RadioButton mRadBtnAgeRange3;*/
    //private TextView mTxtNumFamily;
    private RadioGroup mRadGrpSex;
    private RadioButton mRadBtnMale;
    private RadioButton mRadBtnFemale;
    private Spinner mSpnMaleAge;
    private Spinner mSpnFemaleAge;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxtHobby;
    private CheckBox mCekBoxMusic;
    private CheckBox mCekBoxSing;
    private CheckBox mCekBoxDance;
    private CheckBox mCekBoxTraval;
    private CheckBox mCekBoxReading;
    private CheckBox mCekBoxWriting;
    private CheckBox mCekBoxCliming;
    private CheckBox mCekBoxSwim;
    private CheckBox mCekBoxEating;
    private CheckBox mCekBoxDrawing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mSpnSex = (Spinner) findViewById(R.id.spnSex);
        mRadGrp = (RadioGroup) findViewById(R.id.radGrpAge);
        mRadBtnAgeRange1 = (RadioButton) findViewById(R.id.radBtnAgeRange1);
        mRadBtnAgeRange2 = (RadioButton) findViewById(R.id.radBtnAgeRange2);
        mRadBtnAgeRange3 = (RadioButton) findViewById(R.id.radBtnAgeRange3);*/
        //mTxtNumFamily = (TextView) findViewById(R.id.txtNumFamily);
        mRadGrpSex=(RadioGroup)findViewById(R.id.radGrpSex);
        mRadBtnMale  =(RadioButton) findViewById(R.id.radBtnMale);
        mRadBtnFemale=(RadioButton) findViewById(R.id.radBtnFemale);
        mSpnMaleAge  =(Spinner) findViewById(R.id.spnMale);
        mSpnFemaleAge=(Spinner) findViewById(R.id.spnFemale);
        mTxtHobby =(TextView)findViewById(R.id.txtHobby);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mCekBoxMusic = (CheckBox) findViewById(R.id.cekBoxMusic);
        mCekBoxSing = (CheckBox) findViewById(R.id.cekBoxSing);
        mCekBoxDance = (CheckBox) findViewById(R.id.cekBoxDance);
        mCekBoxTraval = (CheckBox) findViewById(R.id.cekBoxTravel);
        mCekBoxReading = (CheckBox) findViewById(R.id.cekBoxReading);
        mCekBoxWriting = (CheckBox) findViewById(R.id.cekBoxWriting);
        mCekBoxCliming= (CheckBox) findViewById(R.id.cekBoxCliming);
        mCekBoxSwim = (CheckBox) findViewById(R.id.cekBoxSwim);
        mCekBoxEating = (CheckBox) findViewById(R.id.cekBoxEating);
        mCekBoxDrawing = (CheckBox) findViewById(R.id.cekBoxDrawing);

        mBtnOK.setOnClickListener(btnOKOnClick);
        mRadGrpSex.setOnCheckedChangeListener(radGrpSexOnCheckedChange);
    }
    private RadioGroup.OnCheckedChangeListener radGrpSexOnCheckedChange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (mRadBtnMale.isChecked())
            {
                mSpnMaleAge.setEnabled(true);
                mSpnFemaleAge.setEnabled(false);
            }
            else
            {
                mSpnFemaleAge.setEnabled(true);
                mSpnMaleAge.setEnabled(false);
            }
        }
    };
    private View.OnClickListener btnOKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MarriageSuggestion ms = new MarriageSuggestion();

            String strHobbies="興趣：";
            int iAgeRange = 0;

            if (mRadBtnMale.isChecked())
            {

                switch (mSpnMaleAge.getSelectedItemPosition()) {
                    case 1:
                        iAgeRange = 1;
                        break;
                    case 2:
                        iAgeRange = 2;
                        break;
                    case 3:
                        iAgeRange = 3;
                        break;
                }
            }
            else if(mRadBtnFemale.isChecked()) {

                switch (mSpnFemaleAge.getSelectedItemPosition()) {
                    case 1:
                        iAgeRange = 1;
                        break;
                    case 2:
                        iAgeRange = 2;
                        break;
                    case 3:
                        iAgeRange = 3;
                        break;
                }
            }
            if (mCekBoxMusic.isChecked()) strHobbies +=" 音樂";
            if (mCekBoxSing.isChecked())  strHobbies +=" 唱歌";
            if (mCekBoxDance.isChecked()) strHobbies +=" 跳舞";
            if (mCekBoxTraval.isChecked()) strHobbies+=" 旅行";
            if (mCekBoxReading.isChecked()) strHobbies+=" 閱讀";
            if (mCekBoxWriting.isChecked()) strHobbies+=" 寫作";
            if (mCekBoxCliming.isChecked()) strHobbies+=" 爬山";
            if (mCekBoxSwim.isChecked()) strHobbies+=" 游泳";
            if (mCekBoxEating.isChecked()) strHobbies+=" 美食";
            if (mCekBoxDrawing.isChecked()) strHobbies+=" 繪畫";
            mTxtSug.setText(ms.getSuggestion(iAgeRange));
            mTxtHobby.setText(strHobbies);
        }
    };

}
