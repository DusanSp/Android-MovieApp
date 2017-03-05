package com.example.dusan.topmovies.presenter;


import java.util.List;

public interface IPresenter<IListView> {

    void loadData();
    void notifyDataChange(List list);
    Object getDetailData(int position);
    void showLoading();
    void hideLoading();
}
