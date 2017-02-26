package com.example.dusan.topmovies.presenter;


import java.util.List;

public interface IPresenter {

    void loadData();
    void notifyDataChange(List list);
    Object getDetailData(int position);
}
