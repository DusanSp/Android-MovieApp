package com.example.dusan.topmovies.presenter;


public interface IPresenter<IListView> {

  void loadData();

  void disposeResource();
}
