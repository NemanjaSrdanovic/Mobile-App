package com.example.reisefhrer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements Filterable {
    private ArrayList<GradItem> mList;
    private ArrayList<GradItem> mListFull;

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.cityName);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), Grad.class);
            intent.putExtra("key", mTextView.getText().toString());
            v.getContext().startActivity(intent);
        }
    }

    public RecycleAdapter(ArrayList<GradItem> tmpList) {
        this.mList = tmpList;
        mListFull = new ArrayList<>(tmpList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grad_item, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GradItem currItem = mList.get(i);

        viewHolder.mImageView.setImageResource(currItem.getmImageResource());
        viewHolder.mTextView.setText(currItem.getmText1());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<GradItem> filteredList = new ArrayList<>();

            if (constraint == null  || constraint.length() == 0) {
                filteredList.addAll(mListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GradItem item : mListFull){
                    if (item.getmText1().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
}
