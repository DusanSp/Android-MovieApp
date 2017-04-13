package com.example.dusan.topmovies.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.presenter.IPresenterSearch;
import com.example.dusan.topmovies.view.adapters.SearchAdapter;
import java.util.Collections;
import java.util.List;


public class SearchFragment extends Fragment implements IListView {

  @BindView(R.id.list)
  RecyclerView mSearchResultView;
  @BindView(R.id.query_edit_text)
  EditText editText;

  private SearchAdapter mSearchAdapter;
  private LinearLayoutManager mLayoutManager;
  private IPresenterSearch presenter;
  private ProgressDialog mProgressDialog;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  public void initPresenter(IPresenterSearch presenter) {
    this.presenter = presenter;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search, container, false);
    ButterKnife.bind(this, view);

    mSearchAdapter = new SearchAdapter();
    mSearchAdapter.setFragment(this);

    mLayoutManager = new LinearLayoutManager(getActivity());
    mSearchResultView.setLayoutManager(mLayoutManager);

    mSearchResultView.setAdapter(mSearchAdapter);

    return view;
  }

  @Override
  public void onItemClick(Movie movie) {

  }

  @Override
  public void onStart() {
    super.onStart();
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.loadData(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override
  public void showData(List list) {
    if (!list.isEmpty()) {
      mSearchAdapter.dataSetChange(list);
    } else {
      mSearchAdapter.dataSetChange(Collections.<Movie>emptyList());
      Log.d("MovieListFragment", "Movie list is empty");
    }
  }

  @Override
  public void showLoadingIndicator() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setMessage("It's loading...");
      mProgressDialog.setTitle("Loading");
    }

    mProgressDialog.show();
  }

  @Override
  public void hideLoadingIndicator() {
    mProgressDialog.hide();
  }

  @Override
  public void onDestroyView() {
    mProgressDialog.dismiss();
    presenter.disposeResource();
    super.onDestroyView();
  }

  @OnClick(R.id.search_button)
  public void Search(View view) {
    presenter.loadData(editText.getText().toString());
  }
}
