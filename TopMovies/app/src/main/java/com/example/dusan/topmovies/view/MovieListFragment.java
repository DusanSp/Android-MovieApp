package com.example.dusan.topmovies.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.IPresenter;
import com.example.dusan.topmovies.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MovieViewAdapter mMovieViewAdapter;
    private IPresenter presenter;


    public void initPresenter(IPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, view);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMovieViewAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.getMoviesData();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onItemClick(int position)
    {
        presenter.onItemListClicked(position);
    }

    public void updateView(List movieList) {
        if(movieList != null)
        {
            mMovieViewAdapter = new MovieViewAdapter(movieList);
            mMovieViewAdapter.setFragment(this);
        }
        mRecyclerView.swapAdapter(mMovieViewAdapter, true);
        mRecyclerView.invalidate();
    }
}
