package com.example.thingspeak.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thingspeak.R;
import com.example.thingspeak.model.Feeds;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder> {

    private List<Feeds> feedsList = new ArrayList<>();

    public FeedsAdapter() {}

    public void updateTheAdapter(List<Feeds> feedsList) {

        this.feedsList = feedsList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feed_item, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull FeedsViewHolder holder, int position) {

        holder.bind(feedsList.get(position));
    }

    @Override
    public int getItemCount() {

        return feedsList.size();
    }

    public static class FeedsViewHolder extends RecyclerView.ViewHolder {

        private final TextView createdAtTextView, feedIdTextView, field1ValueTextView;

        public FeedsViewHolder(@NotNull View itemView) {
            super(itemView);

            createdAtTextView = itemView.findViewById(R.id.createdAtTextView);
            feedIdTextView = itemView.findViewById(R.id.feedIdTextView);
            field1ValueTextView = itemView.findViewById(R.id.field1ValueTextView);
        }

        public void bind(Feeds feeds) {

            String createdAt = "Created At: " + feeds.getCreatedAt();
            String field1 = "Field 1: " + feeds.getField1();
            String id = "Id: " + feeds.getEntryId();
            createdAtTextView.setText(createdAt);
            feedIdTextView.setText(id);
            field1ValueTextView.setText(field1);
        }
    }
}
