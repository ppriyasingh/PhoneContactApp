package com.example.phonecontacts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

public class phoneContactAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public phoneContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(phoneContact object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
