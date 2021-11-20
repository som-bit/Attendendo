package com.example.attendendo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DairyAdapter extends RecyclerView.Adapter<DairyAdapter.ViewHolder> {


    private List<Entry> mEntries;

    public DairyAdapter(List<Entry> mEntries){
        this.mEntries = mEntries;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);
        View entryView = inflater.inflate(R.layout.entry,parent,false);
        ViewHolder holder = new ViewHolder(entryView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry dairyEntry = mEntries.get(position);
        TextView date = holder.date;
        date.setText(dairyEntry.getDate());
        TextView title = holder.title;
        title.setText(dairyEntry.getTitle());
        title.setPaintFlags(title.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        TextView textView = holder.textView;
        textView.setText(dairyEntry.getText());
    }

    @Override
    public int getItemCount() {

        return mEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView title;
        public TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.dairyData);
            title = (TextView) itemView.findViewById(R.id.dairyTitle);
            textView = (TextView) itemView.findViewById(R.id.dairyEntry);
        }
        public TextView getTextView()
        {
            return textView;
        }
    }
}
