package com.example.thingspeak.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thingspeak.R;
import com.example.thingspeak.viewmodel.ThingSpeakViewModel;

public class MainActivity extends AppCompatActivity {

    private Button refreshButton;
    private FeedsAdapter adapter;
    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwitchCompat createFeedSwitch;
    private ThingSpeakViewModel viewModel;

    private void createFeed(int toggleState) {

        viewModel.createFeed(getString(R.string.api_key_2), toggleState).observe(this, isSuccess -> {

            if (isSuccess) {

                showToast("Feed created successfully!");
                updateTheFeeds();

            } else {

                showToast("Something went wrong. Try again later!");

            }

        });
    }

    private void initializeViews() {

        adapter = new FeedsAdapter();
        createFeedSwitch = findViewById(R.id.createFeedSwitch);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        RecyclerView feedsRecyclerView = findViewById(R.id.feedsRecyclerView);
        feedsRecyclerView.setAdapter(adapter);
        feedsRecyclerView.setHasFixedSize(true);
        feedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshButton = findViewById(R.id.refreshButton);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        viewModel = new ViewModelProvider(this).get(ThingSpeakViewModel.class);
        viewModel.init();
    }

    private void showToast(String message) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }

    private void updateTheFeeds() {

        viewModel.getFeeds(getString(R.string.api_key), 2).observe(this, feedsList -> {

            adapter.updateTheAdapter(feedsList);
            createFeedSwitch.setVisibility(View.VISIBLE);
            loadingProgressBar.setVisibility(View.GONE);
            refreshButton.setVisibility(View.VISIBLE);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        createFeedSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {

                createFeed(1);

            } else {

                createFeed(0);

            }

        });
        refreshButton.setOnClickListener(v -> updateTheFeeds());
        swipeRefreshLayout.setOnRefreshListener(() -> {

            swipeRefreshLayout.setRefreshing(false);
            updateTheFeeds();
        });
        updateTheFeeds();
    }
}