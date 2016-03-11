package com.adi.ho.jackie.versa_news.Fragments;


import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Search Results
 */
public class SearchFragment extends DialogFragment {


    List<ViceItemsClass> newsList;
    RecyclerView newsRecycler;
    private int page = 0;
    public String query;
    private List<ViceItemsClass> filteredNewsList;
    private ProgressBar progressBar;
    private FrameLayout searchContainer;

    public SearchFragment() {

        // Required empty public constructor
        newsList = new ArrayList<>();
        filteredNewsList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.search_progress_bar);
        newsRecycler = (RecyclerView) view.findViewById(R.id.fashion_recycle_list);
        Bundle bundle = getArguments();
        if (bundle != null){
            query = bundle.getString("QUERY").toLowerCase();
        } else {
            query = getTag();
        }
        //query = getArguments().getString("QUERY").toLowerCase();

        new LoadNewsList().execute(String.valueOf(page));

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        newsRecycler.setLayoutManager(linearLayoutManager);
        newsRecycler.addItemDecoration(new VerticalSpaceItemDecoration(30));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }


    private class LoadNewsList extends AsyncTask<String, Void, List<ViceItemsClass>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<ViceItemsClass> doInBackground(String... params) {
            String data = "";
            try {
                URL url = new URL("http://vice.com/api/getlatest/category/news/" + params[0]);
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
            newsList.addAll(viceItemsClasses);
            if (newsList.size() <= 200) {
                page = page + 1;
                new LoadNewsList().execute(String.valueOf(page));
            } else {
                for (ViceItemsClass viceArticle : newsList) {
                    List<String> tags = new ArrayList<String>();
                    tags.addAll(Arrays.asList(viceArticle.getTags()));
                    replace(tags);
                    for (String tag : tags) {
                        if (tag.contains(query)) {
                            filteredNewsList.add(viceArticle);
                            break;
                        }
                    }

            }
                if (filteredNewsList.isEmpty()){
                    getDialog().dismiss();
                }
            ArticleRecyclerAdapter adapter = new ArticleRecyclerAdapter(getActivity(), filteredNewsList);
            newsRecycler.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
        }

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

    public static void replace(List<String> strings) {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase());
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }
}
