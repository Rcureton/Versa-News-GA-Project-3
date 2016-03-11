package com.adi.ho.jackie.versa_news.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.ArticleActivity;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceDataClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceItemsClass;
import com.adi.ho.jackie.versa_news.GSONClasses.ViceSearchResultsClass;
import com.adi.ho.jackie.versa_news.MainActivity;
import com.adi.ho.jackie.versa_news.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(true);
    }

    ArrayList<ViceItemsClass> listOfPopularArticles;
    public ImageView popularImage1;
    public ImageView popularImage2;
    public ImageView popularImage3;
    public ImageView popularImage4;
    public ImageView popularImage5;
    public ImageView popularImage6;
    public ImageView popularImage7;
    public ImageView popularImage8;
    public TextView latestHeadline1;
    public TextView latestHeadline2;
    public TextView latestHeadline3;
    public TextView latestHeadline4;
    public TextView latestHeadline5;
    public TextView latestHeadline6;
    public TextView latestHeadline7;
    public TextView latestHeadline8;
    public TextView latestId1;
    public TextView latestId2;
    public TextView latestId3;
    public TextView latestId4;
    public TextView latestId5;
    public TextView latestId6;
    public TextView latestId7;
    public TextView latestId8;

    private ArrayList<String> idArray;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        // create ContextThemeWrapper from the original Activity Context with the custom theme

        // clone the inflater using the ContextThemeWrapper


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*
         * Retrieve popular articles from activity
         */
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls = getArguments().getStringArrayList("POPULARURL");
        ArrayList<String> headlineArray = getArguments().getStringArrayList("POPULARHEADLINE");
        idArray = getArguments().getStringArrayList("POPULARID");

        //Toolbar toolbar = (Toolbar)view.findViewById(R.id.main_toolbar);
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

        latestHeadline1 = (TextView)view.findViewById(R.id.latest_news_1_text);
        latestHeadline2 = (TextView)view.findViewById(R.id.latest_news_2_text);
        latestHeadline3 = (TextView)view.findViewById(R.id.latest_news_3_text);
        latestHeadline4 = (TextView)view.findViewById(R.id.latest_news_4_text);
        latestHeadline5 = (TextView)view.findViewById(R.id.latest_news_5_text);
        latestHeadline6 = (TextView)view.findViewById(R.id.latest_news_6_text);
        latestHeadline7 = (TextView)view.findViewById(R.id.latest_news_7_text);
        latestHeadline8 = (TextView)view.findViewById(R.id.latest_news_8_text);


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

        latestHeadline1.setText(headlineArray.get(0));
        latestHeadline2.setText(headlineArray.get(1));
        latestHeadline3.setText(headlineArray.get(2));
        latestHeadline4.setText(headlineArray.get(3));
        latestHeadline5.setText(headlineArray.get(4));
        latestHeadline6.setText(headlineArray.get(5));
        latestHeadline7.setText(headlineArray.get(6));
        latestHeadline8.setText(headlineArray.get(7));


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        latestHeadline1.setOnClickListener(articleClickListener1);
        latestHeadline2.setOnClickListener(articleClickListener2);
        latestHeadline3.setOnClickListener(articleClickListener3);
        latestHeadline4.setOnClickListener(articleClickListener4);
        latestHeadline5.setOnClickListener(articleClickListener5);
        latestHeadline6.setOnClickListener(articleClickListener6);
        latestHeadline7.setOnClickListener(articleClickListener7);
        latestHeadline8.setOnClickListener(articleClickListener8);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    View.OnClickListener articleClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(0));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(1));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(2));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(3));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener5 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(4));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener6 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(5));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener7 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(6));
            startActivity(intent);
        }
    };
    View.OnClickListener articleClickListener8 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,idArray.get(7));
            startActivity(intent);
        }
    };

}
