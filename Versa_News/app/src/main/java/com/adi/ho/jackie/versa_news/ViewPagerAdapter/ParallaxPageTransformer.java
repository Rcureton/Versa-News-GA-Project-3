package com.adi.ho.jackie.versa_news.ViewPagerAdapter;

import android.media.Image;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.adi.ho.jackie.versa_news.R;

/**
 * Created by JHADI on 3/9/16.
 */
public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {

        int pageWidth = view.getWidth();


        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(1);

        } else if (position <= 1) { // [-1,1]
            ImageView popularImage1 = (ImageView) view.findViewById(R.id.latest_news_1_image);
            ImageView popularImage2 = (ImageView) view.findViewById(R.id.latest_news_2_image);
            ImageView popularImage3 = (ImageView) view.findViewById(R.id.latest_news_3_image);
            ImageView popularImage4 = (ImageView) view.findViewById(R.id.latest_news_4_image);
            ImageView popularImage5 = (ImageView) view.findViewById(R.id.latest_news_5_image);
            ImageView popularImage6 = (ImageView) view.findViewById(R.id.latest_news_6_image);
            ImageView popularImage7 = (ImageView) view.findViewById(R.id.latest_news_7_image);
            ImageView popularImage8 = (ImageView) view.findViewById(R.id.latest_news_8_image);

            popularImage1.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed
            popularImage2.setTranslationX(-position * (pageWidth / 2));
            popularImage3.setTranslationX(-position * (pageWidth / 2));
            popularImage4.setTranslationX(-position * (pageWidth / 2));
            popularImage5.setTranslationX(-position * (pageWidth / 2));
            popularImage6.setTranslationX(-position * (pageWidth / 2));
            popularImage7.setTranslationX(-position * (pageWidth / 2));
            popularImage8.setTranslationX(-position * (pageWidth / 2));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(1);
        }


    }
}
