package com.adi.ho.jackie.versa_news.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<ViceItemsClass> listOfPopularArticles;
    private ImageView popularImage1;
    private ImageView popularImage2;
    private ImageView popularImage3;
    private ImageView popularImage4;
    private ImageView popularImage5;
    private ImageView popularImage6;
    private ImageView popularImage7;
    private ImageView popularImage8;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create ContextThemeWrapper from the original Activity Context with the custom theme

        // clone the inflater using the ContextThemeWrapper


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*
         * Retrieve popular articles from activity
         */
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls = getArguments().getStringArrayList("POPULARURL");

        /*
         *
         */
        popularImage1 = (ImageView)view.findViewById(R.id.latest_news_1_image);
        popularImage2 = (ImageView)view.findViewById(R.id.latest_news_2_image);
        popularImage3 = (ImageView)view.findViewById(R.id.latest_news_3_image);
        popularImage4 = (ImageView)view.findViewById(R.id.latest_news_4_image);
        popularImage5 = (ImageView)view.findViewById(R.id.latest_news_5_image);
        popularImage6 = (ImageView)view.findViewById(R.id.latest_news_6_image);
        popularImage7 = (ImageView)view.findViewById(R.id.latest_news_7_image);
        popularImage8 = (ImageView)view.findViewById(R.id.latest_news_8_image);

        /*
         *
         */


        /*
         *
         */

        Picasso.with(getContext()).load(imageUrls.get(0)).fit().into(popularImage1);
        Picasso.with(getContext()).load(imageUrls.get(1)).fit().into(popularImage2);
        Picasso.with(getContext()).load(imageUrls.get(2)).fit().into(popularImage3);
        Picasso.with(getContext()).load(imageUrls.get(3)).fit().into(popularImage4);
        Picasso.with(getContext()).load(imageUrls.get(4)).fit().into(popularImage5);
        Picasso.with(getContext()).load(imageUrls.get(5)).fit().into(popularImage6);
        Picasso.with(getContext()).load(imageUrls.get(6)).fit().into(popularImage7);
        Picasso.with(getContext()).load(imageUrls.get(7)).fit().into(popularImage8);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }
}
