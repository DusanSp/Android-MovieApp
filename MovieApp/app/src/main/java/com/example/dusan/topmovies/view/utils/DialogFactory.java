package com.example.dusan.topmovies.view.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Dusan on 04.Jun.17.
 */

public class DialogFactory {

  public static ProgressDialog createSimpleProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setMessage(message);
    return progressDialog;
  }
}
