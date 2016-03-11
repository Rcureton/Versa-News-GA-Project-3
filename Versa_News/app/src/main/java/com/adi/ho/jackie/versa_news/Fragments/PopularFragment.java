package com.adi.ho.jackie.versa_news.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.GestureDetector;
import android.view.LayoutInflater;
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
import com.easyandroidanimations.library.FlipVerticalAnimation;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHADI on 3/10/16.
 */
public class PopularFragment extends Fragment {

    public ChangeToolbarColor colorListener;

    public static interface ChangeToolbarColor{
        public void changeColor(Palette.Swatch light, Palette.Swatch dark);
    }

    private static final int SWIPE_MIN_DISTANCE = 160;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public ImageView popularImage;
    public TextView popularHeadline;
    public TextView popularPreview;
    public TextView popularId;
    private int up;

    private ArrayList<ViceItemsClass> popularList;
    int listPosition = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            colorListener = (ChangeToolbarColor)activity;
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        popularImage = (ImageView) view.findViewById(R.id.popular_fragment_image);
        popularHeadline = (TextView)view.findViewById(R.id.popular_fragment_headline);
        popularPreview = (TextView)view.findViewById(R.id.popular_fragment_preview);
        popularId = (TextView)view.findViewById(R.id.popular_fragment_id);
        up = -1;
        DownloadPopularArticlesAsyncTask downloadATask = new DownloadPopularArticlesAsyncTask();
        String popularUrl = getString(R.string.get_most_popular);
        downloadATask.execute(popularUrl);


        final GestureDetector gdt = new GestureDetector(new GestureListener());
        popularImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                Picasso.with(getContext()).load(popularList.get(listPosition).getImage()).into(popularImage);
                popularHeadline.setText(popularList.get(listPosition).getTitle());
                popularPreview.setText(popularList.get(listPosition).getPreview());
                popularId.setText(popularList.get(listPosition).getId());
                Picasso.with(getContext()).load(popularList.get(listPosition).getImage()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {

                        Palette palette = Palette.from(bitmap).generate();

                        Palette.Swatch mutedLightSwatch = palette.getLightMutedSwatch();
                        Palette.Swatch mutedDarkSwatch = palette.getDarkMutedSwatch();

                        colorListener.changeColor(mutedLightSwatch, mutedDarkSwatch);
                    }

                    @Override
                    public void onBitmapFailed(Drawable drawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable drawable) {

                    }
                });

                return true;
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Picasso.with(getContext()).load(popularList.get(0).getImage()).fit().into(popularImage);
        popularHeadline.setOnClickListener(clickListener);
        popularPreview.setOnClickListener(clickListener);


    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Right to left
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Left to right
            }

            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                if (listPosition == popularList.size()-1){
                    listPosition = 0;
                } else {
                    listPosition = listPosition+1;
                }
                up = 0;
                return true; // Bottom to top
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                if (listPosition == 0){
                    listPosition = popularList.size() - 1;
                } else {
                    listPosition = listPosition-1;
                }
                up=1;
                return true; // Top to bottom
            }
            return false;
        }
    }
    private class DownloadPopularArticlesAsyncTask extends AsyncTask<String,Void,ArrayList<ViceItemsClass>> {


        @Override
        protected ArrayList<ViceItemsClass> doInBackground(String... myURL) {
            String data = "";
            try {
                URL url = new URL(myURL[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                data = getInputData(inputStream);
            } catch (Throwable e) {
                e.printStackTrace();
            }

            // Convert the JSON data to Gson data
            Gson gson = new Gson();
            ViceSearchResultsClass results = gson.fromJson(data, ViceSearchResultsClass.class);

            /*
            To get the article ID, call: results.getData().getItems().get(0).getId();
            To get the category, call: results.getData().getItems().get(0).getCategory();
             */
            ViceDataClass item = results.getData();
            ArrayList articleArrayList = item.getItems();
            return articleArrayList;
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

        @Override
        protected void onPostExecute(ArrayList<ViceItemsClass> viceItemsClasses) {

        viceItemsClasses.size();
            popularList=viceItemsClasses;

        }

    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String id = popularId.getText().toString();
            Intent intent = new Intent(getActivity(), ArticleActivity.class);
            intent.putExtra(MainActivity.ARTICLEID,id);
            startActivity(intent);
        }
    };
}
