package com.example.dusan.topmovies.presenter;

import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.TvShow;
import com.example.dusan.topmovies.view.IListView;

import java.util.List;


public class TvShowsPresenter implements IPresenter {

    private DataManager mDataManager;
    private IListView mListView;


    public TvShowsPresenter(IListView listView) {
        mDataManager = new DataManager(this);
        mListView = listView;
    }

    @Override
    public void loadData() {
        mDataManager.fetchTvShowsOnTheAirData();
    }

    @Override
    public void notifyDataChange(List list) {
        mListView.showData(list);
    }

    @Override
    public TvShow getDetailData(int position) {
        return mDataManager.getTvShow(position);
    }
}
