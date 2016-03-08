package com.adi.ho.jackie.versa_news.LayoutFiles;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.adi.ho.jackie.versa_news.Fragments.HomeFragment;
import com.adi.ho.jackie.versa_news.Fragments.TechFragment;
import com.adi.ho.jackie.versa_news.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VerticalPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int mParent;
    private int mChilds;
    private JSONArray mColors;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;

    public VerticalPagerAdapter(Context c, int parent, int childs, FragmentManager fragmentManager) {
        // super(fragmentManager);
        mContext = c;
        mParent = parent;
        mChilds = childs;
        this.fragmentManager = fragmentManager;
        loadJSONFromAsset(c);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mChilds;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment frag = null;


        LinearLayout linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linear.setLayoutParams(lp);


        if (mParent == 0) {
            switch (position) {
                case 0:
                    frag = new HomeFragment();
                    linear.setId(R.id.baselayoutId);
                    fragmentTransaction.replace(linear.getId(), new HomeFragment()).commit();
                    break;
                case 1:
                    frag = new TechFragment();
                    linear.setId(R.id.techlayoutId);
                    fragmentTransaction.replace(R.id.techlayoutId, new TechFragment()).commit();
                    break;
                case 2:
                    linear.setId(R.id.businesslayoutId);
                    fragmentTransaction.replace(linear.getId(), new TechFragment()).commit();
                    break;

            }
        } else if (mParent == 1){
            switch (position) {
                case 0:
                    frag = new HomeFragment();
                    linear.setId(R.id.baselayoutId);
                    fragmentTransaction.replace(linear.getId(), new HomeFragment()).commit();
                    break;
                case 1:
                    frag = new TechFragment();
                    linear.setId(R.id.techlayoutId);
                    fragmentTransaction.replace(R.id.techlayoutId, new TechFragment()).commit();
                    break;
                case 2:
                    linear.setId(R.id.businesslayoutId);
                    fragmentTransaction.replace(linear.getId(), new TechFragment()).commit();
                    break;
            }
        }

//
        container.addView(linear);

        if (mParent == 0) {

        }
        // fragmentTransaction.replace(container.getId(), new HomeFragment()).commit();
        // fragmentTransaction.add(new HomeFragment(), "home").commit();
        return linear;
    }


    public void setColors(int position, View layout) {
        try {
            String colorString = "#" + mColors.getJSONArray(mParent % 10).getString(position % 10);
            layout.setBackgroundColor(Color.parseColor(colorString));
        } catch (JSONException ex) {
            Log.e("XXX", "Fail to load color [" + mParent + "][" + position + "]");
        }

    }

    public void loadJSONFromAsset(Context ctx) {
        try {
            InputStream is = ctx.getAssets().open("colors.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String stringJson = new String(buffer, "UTF-8");
            mColors = new JSONArray(stringJson);
        } catch (IOException ex) {
            Log.e("XXX", "Fail to load color JSON file");
            ex.printStackTrace();
        } catch (JSONException ex) {
            Log.e("XXX", "Fail to parse colors JSON");
            ex.printStackTrace();
        }
    }
}