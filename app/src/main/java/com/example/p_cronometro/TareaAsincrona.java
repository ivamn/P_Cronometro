package com.example.p_cronometro;

import android.os.AsyncTask;

import java.time.LocalTime;
import java.util.Date;

public class TareaAsincrona extends AsyncTask<Integer, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... integers) {
        LocalTime time = LocalTime.of(0,0,0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
