package com.ishaanohri.corify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> headingArrayList = new ArrayList<>();
    private ArrayList<String> descriptionArrayList = new ArrayList<>();
    private ArrayList<String> timeSourceArrayList = new ArrayList<>();
    private ArrayList<String> imageArrayList = new ArrayList<>();
    private ArrayList<String> linkArrayList = new ArrayList<>();
    private Context context;

    public NewsRecyclerViewAdapter(ArrayList<String> headingArrayList, ArrayList<String> descriptionArrayList, ArrayList<String> timeSourceArrayList, ArrayList<String> imageArrayList, ArrayList<String> linkArrayList, Context context) {
        this.headingArrayList = headingArrayList;
        this.descriptionArrayList = descriptionArrayList;
        this.timeSourceArrayList = timeSourceArrayList;
        this.imageArrayList = imageArrayList;
        this.linkArrayList = linkArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.heading.setText(headingArrayList.get(position));
        holder.timeSource.setText(timeSourceArrayList.get(position));
        holder.description.setText(descriptionArrayList.get(position));
//
//        Glide.with(context)
//                .load(imageArrayList.get(position))
//                .into(holder.imageView);

        holder.newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("link",linkArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return headingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView heading, timeSource, description;
        ImageView imageView;
        ConstraintLayout newsItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsItem = itemView.findViewById(R.id.newsItem);
            heading = itemView.findViewById(R.id.heading);
            timeSource = itemView.findViewById(R.id.timeSource);
            description = itemView.findViewById(R.id.description);
            //imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
