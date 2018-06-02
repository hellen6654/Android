package com.example.a105590040.hw10;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchContact extends Fragment {

    private ListView listviewContactList;

    private HighlightAdapter<String> highlightAdapter;

    public SearchContact() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_contact, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        listviewContactList = view.findViewById(R.id.listviewContactList);
        listviewContactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listviewContactList.clearChoices();

        highlightAdapter = new HighlightAdapter<>(this.getContext(), android.R.layout.simple_list_item_1);
        listviewContactList.setAdapter(highlightAdapter);

        Cursor cursor = MainActivity.dbContact.rawQuery("select * from " + MainActivity.DB_TABLE,null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String data = "Name: " + cursor.getString(1) +
                        ", PhoneNumber: " + cursor.getString(2) +
                        ", PhoneType: " + cursor.getString(3);
                highlightAdapter.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
        highlightAdapter.notifyDataSetChanged();
    }

    public void addDataToList(String data) {
        highlightAdapter.add(data);
        highlightAdapter.notifyDataSetChanged();
        listviewContactList.clearChoices();
        listviewContactList.requestLayout();
    }


    public void setListHighlight() {
        highlightAdapter.setHighlightList();
    }


    public void setListHighlight(ArrayList<String> list) {
        ArrayList<Integer> indexList = new ArrayList<>();
        int length = highlightAdapter.getCount();

        for (int i = 0; i < length; i++) {
            if (list.contains(highlightAdapter.getItem(i))) {
                indexList.add(i);
            }
        }

        highlightAdapter.setHighlightList(indexList);
    }
}

class HighlightAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<Integer> indexList;

    public HighlightAdapter(Context context, int resource) {
        super(context, resource);
        indexList = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View renderer = super.getView(position, convertView, parent);
        if (indexList.contains(position)) {
            renderer.setBackgroundResource(R.color.selected);
        }
        else {
            renderer.setBackgroundResource(R.color.normal);
        }
        return renderer;
    }

    public void setHighlightList() { indexList.clear(); }

    public void setHighlightList(ArrayList<Integer> newIndexList) {
        indexList = newIndexList;
    }
}
