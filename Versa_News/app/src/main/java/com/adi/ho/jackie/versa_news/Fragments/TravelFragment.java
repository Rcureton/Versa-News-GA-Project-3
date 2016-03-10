package com.adi.ho.jackie.versa_news.Fragments;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.R;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.ArticleRecyclerAdapter;
import com.adi.ho.jackie.versa_news.RecyclerViewStuff.VerticalSpaceItemDecoration;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment {

    List<ViceItemsClass> travelList;
    RecyclerView travelRecycler;

    public TravelFragment() {
        // Required empty public constructor
        this.travelList = travelList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_fashion, container, false);
        travelRecycler = (RecyclerView) view.findViewById(R.id.fashion_recycle_list);

        new LoadNewsList().execute();

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        travelRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        travelRecycler.setLayoutManager(linearLayoutManager);
        travelRecycler.addItemDecoration(new VerticalSpaceItemDecoration(30));

    }

    private class LoadNewsList extends AsyncTask<Void,Void,List<ViceItemsClass>> {


        @Override
        protected List<ViceItemsClass> doInBackground(Void... params) {
            String data = "";
            try {
                URL url = new URL("http://vice.com/api/getlatest/category/travel");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                data = getInputData(inputStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

            ViceDataClass item = results.getData();

            return item.getItems();

        }

        @Override
        protected void onPostExecute(List<ViceItemsClass> viceItemsClasses) {
            ArticleRecyclerAdapter adapter = new ArticleRecyclerAdapter(getContext(), viceItemsClasses);
            travelRecycler.setAdapter(adapter);

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

