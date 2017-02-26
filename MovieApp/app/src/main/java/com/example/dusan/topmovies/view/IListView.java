package com.example.dusan.topmovies.view;

import java.util.List;



public interface IListView {

    void onItemClick(int position);
    void showData(List list);
}
