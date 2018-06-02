package com.example.a105590040.hw10;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Database settings
    public static final String DB_FILE = "contact.db", DB_TABLE = "contact";
    public static SQLiteDatabase dbContact;

    // Fragments
    private AddNewContact addNewContact;
    private SearchContact searchContact;

    // Pagers
    private SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(
            getSupportFragmentManager());
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout;
        tabLayout = findViewById(R.id.tblTabLine);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Close keyboard when user click TabLayout
                InputMethodManager imm = ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE));
                imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        addNewContact = new AddNewContact();
        searchContact = new SearchContact();


        DBOpenHelper DBOpenHelper = new DBOpenHelper(getApplicationContext(), DB_FILE, null, 1);
        dbContact = DBOpenHelper.getWritableDatabase();

        Cursor cursor = dbContact.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + DB_TABLE + "'"
                , null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// DB Table not exist, therefore to construct a new one.
                dbContact.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "phoneNumber TEXT," +
                        "phoneType TEXT);");

            cursor.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.itemSearch).getActionView();
        searchView.setOnQueryTextListener(searchView_OnQuery);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemAddContact) {
            ContentValues data = addNewContact.getContentValues();
            dbContact.insert(DB_TABLE, null, data);
            searchContact.addDataToList(
                    "Name: " + data.getAsString("name") +
                            ", PhoneNumber: " + data.getAsString("phoneNumber") +
                            ", PhoneType: " + data.getAsString("phoneType"));

            Toast.makeText(this, "資料已成功加入至資料庫中！", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbContact.close();
    }

    // On user searching data
    private final SearchView.OnQueryTextListener searchView_OnQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Cursor cursor = null;
            if (!query.equals("")) {
                cursor = dbContact.query(true,
                        DB_TABLE,
                        new String[]{"name", "phoneNumber", "phoneType"},
                        "name=" + "\"" + query + "\"",
                        null, null, null, null, null);
            }

            if (cursor == null)
                return false;

            // If couldn't find data, then show the message
            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "找不到資料！", Toast.LENGTH_LONG).show();
                searchContact.setListHighlight();
            }
            else {
                ArrayList<String> dataList = new ArrayList<>();
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    dataList.add(
                            "Name: " + cursor.getString(0) +
                                    ", PhoneNumber: " + cursor.getString(1) +
                                    ", PhoneType: " + cursor.getString(2));
                    cursor.moveToNext();
                }
                searchContact.setListHighlight(dataList);
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) { return false; }
    };

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = addNewContact;
                    break;
                case 1:
                    fragment = searchContact;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Add New Contact";
                case 1:
                    return "Search Contact";
                default:
                    return null;
            }
        }
    }
}

