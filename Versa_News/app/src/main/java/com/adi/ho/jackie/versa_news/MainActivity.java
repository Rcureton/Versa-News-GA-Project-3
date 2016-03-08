package com.adi.ho.jackie.versa_news;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    ArrayList<ViceItemsClass> mArrayList;
    ArrayAdapter<ViceItemsClass> mAdapter;
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
//        mArrayList = new ArrayList<ViceItemsClass>();
        mAdapter = new ArrayAdapter<ViceItemsClass>(MainActivity.this, android.R.layout.simple_list_item_1, new ArrayList<ViceItemsClass>());
        mListView.setAdapter(mAdapter);

        // TODO: Move to strings resources folder
        String getMostPopularURL = "http://vice.com/api/getmostpopular/";

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask();
        getDataAsyncTask.execute(getMostPopularURL);
    }

    private class GetDataAsyncTask extends AsyncTask<String, Void, ViceSearchResultsClass> {
        @Override
        protected ViceSearchResultsClass doInBackground(String... myURL) {

            String data = "";
            try {
                URL url = new URL(myURL[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                Log.e("data:", data);
                data = getInputData(inputStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

            for (int i = 0; i < results.getData().getItems().size(); i++) {
                String title = results.getData().getItems().get(i).getTitle();
                String author = results.getData().getItems().get(i).getAuthor();
                Log.d("ASYNCTASK", "Title: " + title + ", by " + author);
            }
            return results;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ViceSearchResultsClass result) {
            super.onPostExecute(result);
            ViceDataClass item = result.getData();
            mAdapter.clear();
            mAdapter.addAll(item.getItems());
            Log.d("ASYNCTASK", "list size: "+item.getItems().size());

        }

        public String getInputData(InputStream stream) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String read;

            while ((read = br.readLine()) != null) {
                sb.append(read);
            }

            br.close();
            return sb.toString();
        }

    }
}
