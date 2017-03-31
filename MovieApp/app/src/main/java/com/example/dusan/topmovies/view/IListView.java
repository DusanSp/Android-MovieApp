package com.example.dusan.topmovies.view;

import com.example.dusan.topmovies.model.Movie;
import java.util.List;


public interface IListView {

  void onItemClick(Movie movie);

  void showData(List list);

  void showLoadingIndicator();

  void hideLoadingIndicator();
}
