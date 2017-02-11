package com.example.dusan.topmovies.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.presenter.IPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailFragment extends Fragment {

    private IPresenter presenter;
    private int movieID;
    private Movie mMovie;

    @BindView(R.id.title_detail)
    TextView title;
    @BindView(R.id.original_title_detail)
    TextView originalTitle;
    @BindView(R.id.score_detail)
    TextView score;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.vote_count)
    TextView voteCount;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.relase_date_detail)
    TextView relaseDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public void initPresenter(IPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMovie = presenter.getMovieDetails(movieID);
        title.setText(mMovie.getTitle());
        originalTitle.setText(mMovie.getOriginalTitle());
        score.setText(String.valueOf(mMovie.getAverageRating()));
        Picasso.with(getActivity().getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500" + mMovie.getPosterPath()).into(poster);
        summary.setText(mMovie.getOverview());
        voteCount.setText(String.valueOf(mMovie.getVoteCount()));
        language.setText(mMovie.getOriginalLanguage());
        relaseDate.setText(mMovie.getRelaseDate());

        Log.d("MovieDetailFragment", mMovie.getTitle());
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void setMovieID(int id)
    {
        this.movieID = id;
    }
}
