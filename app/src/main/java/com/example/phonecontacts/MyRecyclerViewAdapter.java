package com.example.phonecontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList contactData, phoneData;
//    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList cData, ArrayList phData) {

        this.context = context;
        this.contactData = cData;
        this.phoneData = phData;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("MyRecyclerViewAdapter: onCreateViewHolder");

        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        System.out.println("MyRecyclerViewAdapter: onBindViewHolder");

        holder.myTextView1.setText(String.valueOf(contactData.get(position)));
        holder.myTextView2.setText(String.valueOf(phoneData.get(position)));
    }

    // total number of rows
    @Override
    public int getItemCount() {

        System.out.println("MyRecyclerViewAdapter: getItemCount");
        return contactData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView1, myTextView2;

        ViewHolder(View itemView) {
            super(itemView);
            System.out.println("MyRecyclerViewAdapter: ViewHolder");

            myTextView1 = itemView.findViewById(R.id.tvContactName);
            myTextView2 = itemView.findViewById(R.id.tvPhoneNumber);
//            itemView.setOnClickListener(this);
        }

        /*@Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }*/
    }

    /*// convenience method for getting data at click position
    String getItem(int id) {
        return contactData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }*/
}
