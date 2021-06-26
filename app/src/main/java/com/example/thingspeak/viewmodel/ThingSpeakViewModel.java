package com.example.thingspeak.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.thingspeak.model.Feeds;
import com.example.thingspeak.model.FeedsResponse;
import com.example.thingspeak.repository.ThingSpeakRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ThingSpeakViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private ThingSpeakRepository repository;

    public LiveData<List<Feeds>> getFeeds(String api_key, int noOfFeeds) {

        MutableLiveData<List<Feeds>> feedsListData = new MutableLiveData<>();
        disposable.add(
                repository.getFeeds(api_key, noOfFeeds)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<FeedsResponse>() {
                            @Override
                            public void onSuccess(@NotNull FeedsResponse feedsResponse) {

                                feedsListData.postValue(feedsResponse.getFeedsList());
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {

                                Log.e("error in fetching feeds", e.getMessage());
                            }
                        })
        );
        return feedsListData;
    }

    public LiveData<Boolean> createFeed(String api_key, int field1) {

        MutableLiveData<Boolean> feedPostData = new MutableLiveData<>();
        disposable.add(
                repository.createFeed(api_key, field1)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {

                                feedPostData.postValue(true);
                            }

                            @Override
                            public void onError(@NotNull Throwable e) {

                                feedPostData.postValue(false);
                                Log.e("error in creating feed", e.getMessage());
                            }
                        })
        );
        return feedPostData;
    }

    public void init() {

        repository = new ThingSpeakRepository();
        repository.init();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposable.clear();
    }
}
